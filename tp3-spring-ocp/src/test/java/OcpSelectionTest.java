import metier.IMetier;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import presentation.Presentation2;

import static org.junit.Assert.assertEquals;

/**
 * Tests de non-régression OCP.
 * Valide que MetierImpl reste inchangé et produit le bon résultat
 * selon le profil ou la propriété activée.
 */
public class OcpSelectionTest {

    // ── Tests par profil ──────────────────────────────────────────────────────

    @Test
    public void devProfile_choisitDao2_300() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");   // DaoImpl2 (150) => 300
        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(300.0, m.calcul(), 1e-6);

        ctx.close();
    }

    @Test
    public void prodProfile_choisitDao_200() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");  // DaoImpl (100) => 200
        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(200.0, m.calcul(), 1e-6);

        ctx.close();
    }

    @Test
    public void fileProfile_choisitDaoFile_360() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("file");  // DaoFile (180) => 360
        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(360.0, m.calcul(), 1e-6);

        ctx.close();
    }

    @Test
    public void apiProfile_choisitDaoApi_440() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("api");   // DaoApi (220) => 440
        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(440.0, m.calcul(), 1e-6);

        ctx.close();
    }

    // ── Test Variante C : sélection par propriété système ────────────────────
    //
    // PRÉREQUIS : retirer @Profile de tous les DaoImpl* ET désactiver DaoAliasConfig
    // pour que PropertyDrivenConfig puisse créer le bean "dao" sans conflit.
    //
    // Ce test est commenté par défaut car il nécessite une configuration
    // particulière (pas de @Profile sur les DaoImpl*).
    //
    // @Test
    // public void propertyTarget_daoApi_440() {
    //     System.setProperty("dao.target", "daoApi");
    //     AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    //     ctx.register(Presentation2.class);
    //     ctx.refresh();
    //     IMetier m = ctx.getBean(IMetier.class);
    //     assertEquals(440.0, m.calcul(), 1e-6);
    //     ctx.close();
    //     System.clearProperty("dao.target");
    // }
}
