# démo-micro-front-mono

`RCM-MicroShop 플랫폼은 마이크로 서비스 플랫픔으로, 각 서비스 별 API 를 제공하며, JWT 토큰을 기반으로 한 인증을 지원합니다. 이 샘플 코드는 API를 사용하지 않고 직접 플랫폼 코드와 통합하는 SpringBoot 프로젝트입니다.`

**code passe-partout qui peut être utilisé lors de l'exécution de RCM-MicroShop en mode monolithique**

-   Pour l'authentification, utilisez l'authentification par défaut de SpringSecurity au lieu de JWT.
-   Appelez directement les méthodes de service au lieu de l'API RESTful. (Vous pouvez également utiliser l'API RESTful en définissant platform.config.webmvc.front=true.)

`RCM-MicroShop 플랫폼의 코드는 https://github.com/rchemist/release-micro 에 공개되어 있습니다.`

## Services en développement

### 1. Créez un projet

Téléchargez ou forkez ce projet de code passe-partout et créez un nouveau projet.

_À ce stade, le contenu original du fichier pom.xml existant et du fichier MonolithicFrontApplication.java doit être conservé. (Des ajouts sont possibles)_

<br>

### 2. Ajoutez le code nécessaire

-   Ajouter une dépendance à pom.xml<br>Pour ajouter des dépendances qui n'ont pas été ajoutées au projet existant, ajoutez-les via les paramètres de dépendance dans pom.xml.
-   Les bibliothèques les plus utiles lors du développement de services Web, telles que lombok, hibernate et common-utils, ont été ajoutées, alors vérifiez d'abord les détails des dépendances dans pom.xml, puis ajoutez ceux nécessaires.
-   Services en développement<br>Vous pouvez commencer le développement exactement de la même manière que vous développeriez n’importe quel autre projet. Cependant, avant le développement`실행하기`Assurez-vous de lire et de comprendre le contenu.

<br>

### 3. Point de terminaison du contrôleur déjà réservé

#### Point de terminaison du contrôleur intégré

Le contrôleur intégré peut être activé via les paramètres suivants. (Vrai par défaut)

```yaml
platform:
  config:
    webmvc:
      front: true   # 기본값이 true
```

Si ce paramètre est activé, l'URL du point de terminaison ci-dessous est déjà utilisée au sein de la plateforme et ne peut pas être utilisée dans un développement ultérieur.

**(Important) Si vous développez un point de terminaison avec cette URL, le service ne s'exécutera pas.**

Vous pouvez vérifier le code source réel en ouvrant le projet dans l'EDI et en explorant chaque contrôleur ci-dessous.

-   /actif/\*\*
    -   io.rchemist.asset.controller.AssetManageController
    -   io.rchemist.asset.controller.AssetViewController
-   /client/\*\*
    -   io.rchemist.customer.controller.CustomerController
    -   io.chemist.custom.controller.Customer AuthenticationController
-   /cms/\*\*
    -   io.rchemist.cms.group.controller.GroupController
    -   io.rchemist.cms.board.controller.BoardController
    -   io.rchemist.cms.page.controller.PageController
    -   io.rchemist.cms.page.controller.PageFieldController
-   /commerce/\*\*
    -   Elle n'est pas prise en charge par les projets communautaires, mais l'utilisation de cette URL de point de terminaison est interdite.
-   /événement/\*\*
    -   Il n'est pas pris en charge en mode service monolithique, mais l'utilisation de l'URL du point de terminaison correspondant est interdite.
-   /locataire/\*\*
    -   io.rchemist.tenant.controller.TenantController
    -   io.rchemist.tenant.security.AdminUserAuthenticationController
    -   io.rchemist.tenant.security.AdminUserController
    -   io.rchemist.tenant.security.ApiKeyController
    -   io.rchemist.tenant.security.ApiSecurityController
    -   io.rchemist.tenant.security.SecurityController
    -   io.rchemist.tenant.translate.TranslateController

<br>

`회원 가입 / 로그인을 구현할 때 CustomerAuthenticationController 를 참고하시기 바랍니다.`

<br>

**si`platform.config.webmvc.front`Si la valeur est définie sur false, tous les contrôleurs frontaux fournis par la plateforme RCM MicroShop ne seront pas enregistrés en tant que beans et les points de terminaison ne seront pas ajoutés. Cependant, tous les autres beans tels que Service/Dao fonctionnent normalement, donc si vous souhaitez développer Front View indépendamment, vous pouvez définir cette valeur sur false.**

<br>

## courir

### 1. Préparez-vous

Afin de développer un service, les programmes ci-dessous doivent d'abord être installés.

-   maven 3.5+ (la version n'a pas vraiment d'importance)
-   jdk 11 (openjdk)

### 2. Environnement de développement de projet

La plateforme RCM Micro-Shop a été développée avec les technologies suivantes :

-   Démarrage de printemps 2.6.1
-   Données de printemps JPA 2.6.1
-   Hiberner 5.6.0.Final
-   Recherche Hibernate 6.1.1.Final
-   QueryDsl 5.0.0
-   Recherche élastique 7.15.2
-   MongoDB5
-   MySQL 5.8+

...

### 3. paramètres pom

Dans les paramètres du référentiel du pom racine du projet, vous devez enregistrer le référentiel comme suit.

(Il est déjà configuré dans le projet passe-partout, vous pouvez donc simplement l'utiliser tel quel)

<br>

#### 1) Paramètres du référentiel GitHub

```
<repositories>
<!-- ... -->
  <repository>
      <id>rchemist.snapshots</id>
      <url>https://github.com/rchemist/release-micro/raw/main/snapshots</url>
      <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
      </snapshots>
    </repository>
<!-- ... -->
</repositories>

```

<br>

#### 2) dépendance micro-front-mono

micro-front-mono est un projet qui regroupe chaque microservice de RCM-MicroShop en un seul pour une utilisation pratique en mode monolithique.

    <dependencies>
        <dependency>
          <groupId>io.rchemist</groupId>
          <artifactId>micro-front-mono</artifactId>
          <exclusions>
            <exclusion>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-openfeign</artifactId>
            </exclusion>
            <exclusion>
              <groupId>io.github.openfeign.form</groupId>
              <artifactId>feign-form</artifactId>
            </exclusion>
            <exclusion>
              <groupId>io.github.openfeign.form</groupId>
              <artifactId>feign-form-spring</artifactId>
            </exclusion>
            <exclusion>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </exclusion>
            <exclusion>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-config-client</artifactId>
            </exclusion>
          </exclusions>
        </dependency>
      </dependencies>

_Lors de l'ajout de micro-front-mono en tant que dépendance, vous devez ajouter les paramètres liés à Spring Cloud comme exclusion. Il s’agit de l’empêcher de fonctionner en mode microservice._

Il existe également micro-admin-mono, qui correspond à Admin au lieu de Front.

Vous pouvez l'utiliser de la même manière lors de la configuration du service Admin. Un exemple pour ceci est<https://github.com/rchemist/demo-micro-shop>Tu peux le vérifier ici.

<br>

#### 3) Paramètres de construction du projet

Ajoutez la syntaxe suivante au script de construction afin qu'un fichier jar à distribuer soit créé lors de la construction de Maven.

```xml
  <build>
    <finalName>${project.artifactId}-${project.version}</finalName>
    <pluginManagement>
      <plugins>
        <plugin>
          <artifactId>maven-dependency-plugin</artifactId>
          <version>${maven-dependency-plugin.version}</version>
        </plugin>
      </plugins>
    </pluginManagement>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
```

Ce paramètre permet aux projets créés avec SpringBoot d'être construits sous forme de gros pots et de s'exécuter indépendamment.

<br>

#### 4) micro-front-mono et dépendances RCM Micro-Shop

-   cloud-commun
    Gestion des ressources partagées des microservices.
-   micro-actif
    Gestion des données d'actifs<br>Téléchargement de fichiers Téléchargement et AssetView
-   micro-cms<br>tableau d'affichage<br>Communauté (Groupe)<br>Services liés au CMS tels que les pages
-   micro-client<br>Service aux membres du Front
-   micro-locataire<br>Services liés à l'administration<br>Membre administrateur<br>Services liés à l'outil d'administration tels que la sécurité, les champs personnalisés et les options personnalisées

### 4. Paramètres yml de l'application

#### 1) séquence de chargement yml

-   (1) application.yml
-   (2) application-override.yml de chaque service référencé
-   (3) application-{spring.profiles.active}-override.yml pour chaque service auquel il fait référence
-   (4) application-override.yml
-   (5) application-{spring.profiles.active}-override.yml

Les services de (2) à (3) sont chargés dans l'ordre suivant : commun, actif, cms, commerce, client, événement, locataire.

**Si vous ne voulez pas vous soucier de la priorité, vous pouvez la définir dans application-override.yml.**

<br>

#### 2) Paramètres yml de l'application

Définissez les informations de connexion à la base de données, à Redis, au serveur de recherche élastique, etc.

Lors de l'application des paramètres, veillez à faire attention à la priorité de chargement yml.

Il n'est pas nécessaire de définir les mêmes paramètres dans tous les yml.

Comme expliqué en 1) ci-dessus, vous ne devez le définir qu'une seule fois dans le yml haute priorité.

##### (1) Port de service

Le port de service par défaut est défini sur 8000.

Ce paramètre peut également être modifié dans l'application yml.

```yaml
server:
  port: 8000
https:
  port: 8010
```

##### (2) Paramètres des informations de connexion à la base de données

###### Activation d'une seule base de données en mode monolithique

```yaml
platform:
  config:
    database:
      data-per-services: false    #단일 DB 사용

spring:
  datasource:
    rcm-rdb:
      front:
        driver-class-name: com.mysql.cj.jdbc.Driver
        jdbc-url: #MYSQL 접속 URL & 파라미터
        username: #MYSQL 접속 아이디
        password: #MYSQL 비밀번호
        url: #MYSQL URL
        hibernate:
          cache:
            use_query_cache: true
            use_second_level_cache: true
            # Hibernate CACHE 추가 설정
            redisson:
              entity-asset:
                eviction:
                  max_entries: 100000
                expiration:
                  time_to_live: 86400000
              query-asset:
                eviction:
                  max_entries: 100000
                expiration:
                  time_to_live: 86400000
          dialect: org.hibernate.dialect.MySQLDialect
          hbm2ddl:
            auto: update
          show_sql: true
```

_Si vous souhaitez séparer la base de données par service, vous devez en outre configurer les paramètres de base de données ci-dessus pour chaque service dans application.yml pour chaque service dans /main/resources/service-properties/._

<br>

###### Utiliser MongoDB

En utilisant mongoDB, vous pouvez vous attendre à des performances améliorées par rapport à l'utilisation de RDB dans certains services tels que ASSET et BOARD. Pour utiliser mongoDB, définissez comme suit.

```yaml
platform:
  config:
    provider:
      mongo: true
```

Ensuite, définissez les informations de connexion mongoDB à l'aide des paramètres yml pour chaque service comme suit.

```yaml
spring:
  datasource:
    rcm-mongo: # for override spring.data.mongodb.* values each at services
      catalog:    # 서비스명: asset, cms, common, catalog, order, promotion, customer, event, tenant 로 micro-XXX 에서 micro- 가 제거된 값 
        connectionString: mongodb+srv://MONGO_CLIENT_ID:MONGO_CLIENT_PASSWORD@MONGODB_ADDRESS/rcm-catalog?retryWrites=true&w=majority&connecttimeoutms=10000&sockettimeoutms=15000&waitqueuetimeoutms=3000&maxlifetimems=60000&maxlifetimems=120000&heartbeatfrequencyms=20000&ssl=true
        database: rcm-catalog
        username: #MONGO_CLIENT_ID
        password: #MONGO_CLIENT_PASSWORD
        retryWrite: true
        authSource: admin
```

_Lors de la configuration de mongoDB, vous devez spécifier quelle collection utiliser pour chaque service._

<br>

###### Paramètres des informations de connexion REDIS

Le fournisseur par défaut du Spring Cache de la plateforme est défini sur jcache. Le fournisseur actuel de jcache est REDIS.

```yaml
spring:
  cache:
    type: jcache
  redis:
    host: #REDIS_ADDRESS
    port: 6379
    password: 
```

###### paramètres de rédisson

Définissez les informations de redisson pour utiliser le cache REDIS dans Hibernate.

Modifiez les informations de connexion REDIS de Redisson dans /main/resources/redisson-dev.yml.

```yaml
singleServerConfig:
  password: #REDIS PASSWORD
  address: #REDIS IP
```

<br>

###### Paramètres d'ElasticSearch

Si vous utilisez le moteur de recherche ES, vous pouvez améliorer les performances de recherche.

Pour utiliser ES, définissez comme suit.

Tout d’abord, définissez comme suit pour activer HibernateSearch.

`/main/resources/bootstrap.yml`

```yaml
platform:
  config:
    provider:
      search: hibernate
```

<br>

Et modifiez les paramètres de l'application comme suit.

```yaml
platform:
  config:
    provider:
      search: hibernate   #HibernateSearch 사용
      elastic: true

spring:
  data:
    elasticsearch:
      port: 9200
      host: #ELASTIC_SEARCH_ADDRESS
      username:
      password:
  elasticsearch:
    rest:
      port: 9200
      host: #ELASTIC_SEARCH_ADDRESS
      username: 
      password: 
  datasource:
    rcm-elastic: 
      common: # 서비스명: for override spring.data.elasticsearch.* values each at services
        port: 9200
        host: #ELASTIC_SEARCH_ADDRESS
        username:
        password: 
  jpa:
    properties:
      hibernate:
        search:
          backend:
            username:
            password: 
            uris: #http[s]://ELASTIC_SEARCH_ADDRESS:PORT
```

Pour plus de détails sur chaque option pouvant être définie, veuillez vous référer à la description de chaque élément dans le yml de l'application.

<br>

### 5. Exécutez l’application Front Monolithique

Exécutez MonolithicFrontApplication.

#### 1) Exécuter la transformation de classe

La plate-forme RCM utilise la technologie ClassTransform pour modifier la classe dans Runtime ClassLoader. ClassTransform doit être effectué lors de l'exécution d'une application SpringBoot.

    PlatformClassTransformHelper.initializeDefaultTransformedClasses();

Si application.run() est exécuté sans que ce code soit exécuté, l'application ne s'exécutera pas.

Pour plus de détails, veuillez vous référer à io.rchemist.front.MonolithicFrontApplication.java.

<br>

#### 2) Paramètres de l'environnement d'exécution du service

L'environnement d'exécution du service est classé comme suit, et chacun remplace les paramètres yml en plus des paramètres application.yml.

-   local : environnement de développement local<br>application-local.yml<br>application-local-override.yml
-   développement : serveur de développement<br>développement d'applications.yml<br>application-development-override.yml
-   mise en scène : serveur de préparation<br>application-staging.yml<br>application-staging-override.yml
-   production : serveur de production<br>application-production.yml<br>application-production-override.yml

<br>

Si aucun paramètre n'est défini avec l'option jvm, il s'exécute en mode local par défaut.

    String profile = System.getProperty("spring.profiles.active");
    if(StringUtils.isEmpty(profile)) {
      application.setAdditionalProfiles("local");
    }

<br>

Pour modifier l'environnement d'exécution, définissez l'environnement avec les options suivantes dans les options jvm ou bootstrap.yml.

    #운영 서버 환경 설정 예시
    spring.profiles.active=production 

### 6. Déploiement et exécution

#### 1) construire

Dans le répertoire racine du projet`mvn clean install`Courir .

Si vous souhaitez extraire uniquement le fichier jar pour le distribuer`mvn package`Vous pouvez également courir.

Si le message suivant est généré à la suite de l'exécution, celui-ci se termine normalement.

```shell
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

<br>

#### 2) courir

Avant d'exécuter, assurez-vous de vérifier si le pot est un gros pot.<br>(Le fichier doit faire au moins 1 à 200 Mo)

Fondamentalement, il vous suffit d'exécuter java -jar demo-micro-front-mono-0.0.1-SNAPSHOT.jar. Cependant, si vous faites cela, tous les chemins de classe continueront à être recherchés lorsque le fichier jar Spring Boot sera chargé, ce qui rendra le démarrage de SpringBoot très lent.

Pour éviter ce problème, vous pouvez utiliser la syntaxe suivante :

```shell
java -jar -Xverify:none -XX:TieredStopAtLevel=1 demo-micro-front-mono-0.0.1-SNAPSHOT.jar
```
