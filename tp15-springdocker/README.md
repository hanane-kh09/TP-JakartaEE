Windows PowerShell
Copyright (C) Microsoft Corporation. Tous droits réservés.

Testez le nouveau système multiplateforme PowerShell https://aka.ms/pscore6

[IntelliJ IDEA] Your PSReadLine module version (2.0.0) is outdated, which may cause the problem with black lines across the terminal screen:
https://learn.microsoft.com/windows/terminal/troubleshooting#black-lines-in-powershell-51-6x-70
Install the latest version by running: 'Install-Module PSReadLine -MinimumVersion 2.0.3 -Scope CurrentUser -Force'                                                
After the installation, open a new terminal tab.

PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker build -t ens/springdocker:1.0 .                                                               
[+] Building 2.4s (9/9) FINISHED                            docker:desktop-linux
 => [internal] load build definition from Dockerfile                        0.0s
 => => transferring dockerfile: 193B                                        0.0s 
 => [internal] load metadata for docker.io/library/eclipse-temurin:21-jdk-  1.7s 
 => [auth] library/eclipse-temurin:pull token for registry-1.docker.io      0.0s
 => [internal] load .dockerignore                                           0.0s
 => => transferring context: 2B                                             0.0s 
 => [1/3] FROM docker.io/library/eclipse-temurin:21-jdk-jammy@sha256:801b7  0.1s 
 => => resolve docker.io/library/eclipse-temurin:21-jdk-jammy@sha256:801b7  0.1s 
 => [internal] load build context                                           0.0s 
 => => transferring context: 88B                                            0.0s 
 => CACHED [2/3] WORKDIR /app                                               0.0s 
 => CACHED [3/3] COPY target/springdocker-0.0.1-SNAPSHOT.jar app.jar        0.0s 
 => exporting to image                                                      0.3s 
 => => exporting layers                                                     0.0s 
 => => exporting manifest sha256:bd6b12a123df39e52e8028d4b1f91e13d4a9723b9  0.0s 
 => => exporting config sha256:ae0cfab27353c9be307ae75a68bd31e1b437e8baba9  0.0s 
 => => exporting attestation manifest sha256:d11716a1d4242457ec38099e1f336  0.1s 
 => => exporting manifest list sha256:844807ba6e327104e32299bde0fec6eaa0d9  0.0s 
 => => naming to docker.io/ens/springdocker:1.0                             0.0s 
 => => unpacking to docker.io/ens/springdocker:1.0                          0.0s 
PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker images       
                                                             i Info →   U  In Use
IMAGE                  ID             DISK USAGE   CONTENT SIZE   EXTRA
ens/springdocker:1.0   844807ba6e32        759MB          251MB    U   
mysql:8.0              7dcddc01f13b        1.1GB          249MB    U   
PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker-compose up -d

time="2026-06-24T23:35:41+01:00" level=warning msg="C:\\Users\\ytr\\IdeaProjects\\tp15-springdocker\\springdocker\\docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion"
[+] up 2/2
 ✔ Container mysql-db   Running                                              0.0s
 ✔ Container spring-app Started                                              1.0s
PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker ps
CONTAINER ID   IMAGE                  COMMAND                  CREATED         STATUS         PORTS                                         NAMES
50b282a46e03   ens/springdocker:1.0   "java -jar app.jar"      8 seconds ago   Up 7 seconds   0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp   spring-app
98410cd8ada1   mysql:8.0              "docker-entrypoint.s…"   5 hours ago     Up 5 hours     0.0.0.0:3306->3306/tcp, [::]:3306->3306/tcp   mysql-db
PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker-compose logs -f spring-app                                                                    
time="2026-06-24T23:36:23+01:00" level=warning msg="C:\\Users\\ytr\\IdeaProjects\\tp15-springdocker\\springdocker\\docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion"
spring-app  | 
spring-app  |   .   ____          _            __ _ _
spring-app  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
spring-app  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
spring-app  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
spring-app  |   '  |____| .__|_| |_|_| |_\__, | / / / /
spring-app  |  =========|_|==============|___/=/_/_/_/
spring-app  |  :: Spring Boot ::                (v3.2.5)
spring-app  | 
spring-app  | 2026-06-24T22:35:44.631Z  INFO 1 --- [           main] m.e.s.SpringdockerApplication            : Starting SpringdockerApplication v0.0.1-SNAPSHOT using Java 21.0.11 with PID 1 (/app/app.jar started by root in /app)
spring-app  | 2026-06-24T22:35:44.634Z  INFO 1 --- [           main] m.e.s.SpringdockerApplication            : No active profile set, falling back to 1 default profile: "default"
spring-app  | 2026-06-24T22:35:45.815Z  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
spring-app  | 2026-06-24T22:35:45.903Z  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 66 ms. Found 1 JPA repository interface.
spring-app  | 2026-06-24T22:35:46.715Z  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
spring-app  | 2026-06-24T22:35:46.736Z  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
spring-app  | 2026-06-24T22:35:46.737Z  INFO 1 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
spring-app  | 2026-06-24T22:35:46.784Z  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
spring-app  | 2026-06-24T22:35:46.786Z  INFO 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2028 ms
spring-app  | 2026-06-24T22:35:47.038Z  INFO 1 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
spring-app  | 2026-06-24T22:35:47.141Z  INFO 1 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.4.4.Final
spring-app  | 2026-06-24T22:35:47.200Z  INFO 1 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
spring-app  | 2026-06-24T22:35:47.686Z  INFO 1 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
spring-app  | 2026-06-24T22:35:47.752Z  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
spring-app  | 2026-06-24T22:35:48.463Z  INFO 1 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@36b53f08
spring-app  | 2026-06-24T22:35:48.467Z  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
spring-app  | 2026-06-24T22:35:49.810Z  INFO 1 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
spring-app  | 2026-06-24T22:35:49.880Z  INFO 1 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
spring-app  | 2026-06-24T22:35:50.345Z  WARN 1 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
spring-app  | 2026-06-24T22:35:50.881Z  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
spring-app  | 2026-06-24T22:35:50.901Z  INFO 1 --- [           main] m.e.s.SpringdockerApplication            : Started SpringdockerApplication in 6.962 seconds (process running for 7.845)
Windows PowerShell
Copyright (C) Microsoft Corporation. Tous droits réservés.

Testez le nouveau système multiplateforme PowerShell https://aka.ms/pscore6

[IntelliJ IDEA] Your PSReadLine module version (2.0.0) is outdated, which may cause the problem with black lines across the terminal screen:
https://learn.microsoft.com/windows/terminal/troubleshooting#black-lines-in-powershell-51-6x-70
Install the latest version by running: 'Install-Module PSReadLine -MinimumVersion 2.0.3 -Scope CurrentUser -Force'                                                
After the installation, open a new terminal tab.

PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker build -t ens/springdocker:1.0 .                                                               
[+] Building 2.4s (9/9) FINISHED                            docker:desktop-linux
 => [internal] load build definition from Dockerfile                        0.0s
 => => transferring dockerfile: 193B                                        0.0s 
 => [internal] load metadata for docker.io/library/eclipse-temurin:21-jdk-  1.7s 
 => [auth] library/eclipse-temurin:pull token for registry-1.docker.io      0.0s
 => [internal] load .dockerignore                                           0.0s
 => => transferring context: 2B                                             0.0s 
 => [1/3] FROM docker.io/library/eclipse-temurin:21-jdk-jammy@sha256:801b7  0.1s 
 => => resolve docker.io/library/eclipse-temurin:21-jdk-jammy@sha256:801b7  0.1s 
 => [internal] load build context                                           0.0s 
 => => transferring context: 88B                                            0.0s 
 => CACHED [2/3] WORKDIR /app                                               0.0s 
 => CACHED [3/3] COPY target/springdocker-0.0.1-SNAPSHOT.jar app.jar        0.0s 
 => exporting to image                                                      0.3s 
 => => exporting layers                                                     0.0s 
 => => exporting manifest sha256:bd6b12a123df39e52e8028d4b1f91e13d4a9723b9  0.0s 
 => => exporting config sha256:ae0cfab27353c9be307ae75a68bd31e1b437e8baba9  0.0s 
 => => exporting attestation manifest sha256:d11716a1d4242457ec38099e1f336  0.1s 
 => => exporting manifest list sha256:844807ba6e327104e32299bde0fec6eaa0d9  0.0s 
 => => naming to docker.io/ens/springdocker:1.0                             0.0s 
 => => unpacking to docker.io/ens/springdocker:1.0                          0.0s 
PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker images       
                                                             i Info →   U  In Use
IMAGE                  ID             DISK USAGE   CONTENT SIZE   EXTRA
ens/springdocker:1.0   844807ba6e32        759MB          251MB    U   
mysql:8.0              7dcddc01f13b        1.1GB          249MB    U   
PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker-compose up -d

time="2026-06-24T23:35:41+01:00" level=warning msg="C:\\Users\\ytr\\IdeaProjects\\tp15-springdocker\\springdocker\\docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion"
[+] up 2/2
 ✔ Container mysql-db   Running                                              0.0s
 ✔ Container spring-app Started                                              1.0s
PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker ps
CONTAINER ID   IMAGE                  COMMAND                  CREATED         STATUS         PORTS                                         NAMES
50b282a46e03   ens/springdocker:1.0   "java -jar app.jar"      8 seconds ago   Up 7 seconds   0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp   spring-app
98410cd8ada1   mysql:8.0              "docker-entrypoint.s…"   5 hours ago     Up 5 hours     0.0.0.0:3306->3306/tcp, [::]:3306->3306/tcp   mysql-db
PS C:\Users\ytr\IdeaProjects\tp15-springdocker\springdocker> docker-compose logs -f spring-app                                                                    
time="2026-06-24T23:36:23+01:00" level=warning msg="C:\\Users\\ytr\\IdeaProjects\\tp15-springdocker\\springdocker\\docker-compose.yml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion"
spring-app  | 
spring-app  |   .   ____          _            __ _ _
spring-app  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
spring-app  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
spring-app  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
spring-app  |   '  |____| .__|_| |_|_| |_\__, | / / / /
spring-app  |  =========|_|==============|___/=/_/_/_/
spring-app  |  :: Spring Boot ::                (v3.2.5)
spring-app  | 
spring-app  | 2026-06-24T22:35:44.631Z  INFO 1 --- [           main] m.e.s.SpringdockerApplication            : Starting SpringdockerApplication v0.0.1-SNAPSHOT using Java 21.0.11 with PID 1 (/app/app.jar started by root in /app)
spring-app  | 2026-06-24T22:35:44.634Z  INFO 1 --- [           main] m.e.s.SpringdockerApplication            : No active profile set, falling back to 1 default profile: "default"
spring-app  | 2026-06-24T22:35:45.815Z  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
spring-app  | 2026-06-24T22:35:45.903Z  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 66 ms. Found 1 JPA repository interface.
spring-app  | 2026-06-24T22:35:46.715Z  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
spring-app  | 2026-06-24T22:35:46.736Z  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
spring-app  | 2026-06-24T22:35:46.737Z  INFO 1 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
spring-app  | 2026-06-24T22:35:46.784Z  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
spring-app  | 2026-06-24T22:35:46.786Z  INFO 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2028 ms
spring-app  | 2026-06-24T22:35:47.038Z  INFO 1 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
spring-app  | 2026-06-24T22:35:47.141Z  INFO 1 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.4.4.Final
spring-app  | 2026-06-24T22:35:47.200Z  INFO 1 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
spring-app  | 2026-06-24T22:35:47.686Z  INFO 1 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
spring-app  | 2026-06-24T22:35:47.752Z  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
spring-app  | 2026-06-24T22:35:48.463Z  INFO 1 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@36b53f08
spring-app  | 2026-06-24T22:35:48.467Z  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
spring-app  | 2026-06-24T22:35:49.810Z  INFO 1 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
spring-app  | 2026-06-24T22:35:49.880Z  INFO 1 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
spring-app  | 2026-06-24T22:35:50.345Z  WARN 1 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
spring-app  | 2026-06-24T22:35:50.881Z  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
spring-app  | 2026-06-24T22:35:50.901Z  INFO 1 --- [           main] m.e.s.SpringdockerApplication            : Started SpringdockerApplication in 6.962 seconds (process running for 7.845)










<img width="841" height="205" alt="tp15-4" src="https://github.com/user-attachments/assets/c7f37205-a534-49b1-b7a7-c0f651c82016" />
<img width="722" height="581" alt="tp15-3" src="https://github.com/user-attachments/assets/c6f8e6a6-9b89-4dda-a40d-1a61e8351ce0" />
<img width="707" height="822" alt="tp15-2" src="https://github.com/user-attachments/assets/82e5fe51-150a-4110-9886-2b65c8c31bfc" />
<img width="727" height="852" alt="tp15-1" src="https://github.com/user-attachments/assets/6a42c4ad-323e-491a-b64e-134b58267a91" />
<img width="716" height="677" alt="tp15" src="https://github.com/user-attachments/assets/07b54355-44fd-46a3-a8cd-25e6d5a4c449" />
<img width="731" height="592" alt="tp15-5" src="https://github.com/user-attachments/assets/311f95ec-8de5-4d0a-a091-38bc8b55a929" />
