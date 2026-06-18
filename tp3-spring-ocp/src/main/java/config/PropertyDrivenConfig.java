package config;

import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

    private final ApplicationContext ctx;

    @Value("${dao.target:dao}")
    private String target;

    public PropertyDrivenConfig(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Bean(name = "dao")
    @DependsOn("propertySourcesPlaceholderConfigurer")
    public IDao selectedDao() {
        // ctx.getBean(name) résout le bean par son @Component("nom")
        // Cette résolution est lazy : elle arrive après que tous les
        // autres beans IDao sont enregistrés, sans créer de cycle
        IDao bean = (IDao) ctx.getBean(target);
        if (bean == null) {
            throw new IllegalArgumentException(
                    "Implémentation inconnue: '" + target + "'. " +
                            "Valeurs: dao | dao2 | daoFile | daoApi"
            );
        }
        System.out.println("[CONFIG] dao.target=" + target +
                " => " + bean.getClass().getSimpleName());
        return bean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}