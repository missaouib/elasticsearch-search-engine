# démo-micro-admin-mono

Il s'agit d'un service de code passe-partout qui peut être utilisé lors de l'exécution du service ADMIN de RCM-MicroShop en mode monolithique.
Le code de la plateforme RCM-MicroShop est<https://github.com/rchemist/release-micro>C'est ouvert au public.

## Services en développement

### 1. Créez un projet

Téléchargez ou forkez ce projet de code passe-partout et créez un nouveau projet.

_À ce stade, le contenu original du fichier pom.xml existant et du fichier MonolithicAdminApplication.java doit être conservé. (Des ajouts sont possibles)_

<br>

### 2. Ajoutez le code nécessaire

-   Ajouter une dépendance à pom.xml<br>Pour ajouter des dépendances qui n'ont pas été ajoutées au projet existant, ajoutez-les via les paramètres de dépendance dans pom.xml.
-   Les bibliothèques les plus utiles lors du développement de services Web, telles que lombok, hibernate et common-utils, ont été ajoutées, alors vérifiez d'abord les détails des dépendances dans pom.xml, puis ajoutez ceux nécessaires.
-   Services en développement<br>Vous pouvez commencer le développement exactement de la même manière que vous développeriez n’importe quel autre projet. Cependant, avant le développement`실행하기`Assurez-vous de lire et de comprendre le contenu.
-   Pour référence, le site ADMIN intégré est développé avec Vaadin.

<br>

### 3. Paramètres de l'outil Administrateur

#### Paramètres de l'écran de connexion

-   La propriété de paramètre d'écran de connexion est un sous-élément de platform.config.admin.view.login.\*.

| article                                                       | propriété                           | Exemple de saisie                                                           | explication                                                                                                                                                                    |
| ------------------------------------------------------------- | ----------------------------------- | --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| titre de connexion                                            | titre                               | Console d'administration Rchemist                                           | Titre affiché sur le formulaire de connexion                                                                                                                                   |
| Message explicatif supplémentaire                             | description                         | Bienvenue dans la console d'administration RCM MicroShop !                  | Texte explicatif affiché en bas du titre                                                                                                                                       |
| Image du logo de connexion                                    | image logo                          | ./image/login_logo.png                                                      | Image du logo à afficher en haut du formulaire de connexion. Si vous ne saisissez rien, rien ne sera affiché et il doit être enregistré dans un rayon de 40 pixels de hauteur. |
| Titre du formulaire de connexion                              | titre de formulaire de connexion    | se connecter                                                                | Texte à afficher en haut du formulaire de saisie de connexion                                                                                                                  |
| Récupérer le titre du formulaire de mot de passe              | titreFormulaireDemande              | trouver le mot de passe                                                     | Texte à afficher en haut du formulaire de saisie de recherche de mot de passe                                                                                                  |
| Étiquette d'identification                                    | labelNom d'utilisateur              | identifiant                                                                 | Texte explicatif affiché en bas du titre                                                                                                                                       |
| étiquette de mot de passe                                     | étiquetteMot de passe               | mot de passe                                                                | Texte explicatif affiché en bas du titre                                                                                                                                       |
| Récupérer l'étiquette du formulaire de saisie du mot de passe | labelRequest                        | Pièce d'identité ou email                                                   | Texte explicatif affiché en bas du titre                                                                                                                                       |
| Titre de l'erreur de connexion                                | erreurConnexionSoumettreTitre       | Je ne peux pas me connecter.                                                | Quand la connexion échoue ! Titre de l'erreur à afficher avec                                                                                                                  |
| Message d'erreur de connexion                                 | erreurConnexionSoumettreDescription | Veuillez vérifier à nouveau votre nom d'utilisateur ou votre mot de passe.  | Détails de l'échec de connexion                                                                                                                                                |
| Titre de l'erreur de récupération du mot de passe             | erreurRequestSubmitTitle            | Vos informations d'utilisateur ne peuvent pas être vérifiées.               | Quand la récupération du mot de passe échoue ! Titre de l'erreur à afficher avec                                                                                               |
| Message d'erreur de récupération du mot de passe              | erreur RequestSubmit Description    | Veuillez vérifier à nouveau votre pièce d'identité ou votre adresse e-mail. | Explication détaillée en cas d'échec de la récupération du mot de passe                                                                                                        |
| Connexion SOUMETTRE                                           | boutonConnexionTexte                | se connecter                                                                | Formulaire de saisie de connexion Texte du bouton SOUMETTRE                                                                                                                    |
| Texte du lien de connexion                                    | boutonLienConnexionTexte            | Vers l'écran de connexion                                                   | Texte du lien lors du passage du formulaire de saisie de recherche de mot de passe au formulaire de saisie de connexion                                                        |
| Trouvez votre mot de passe ENVOYER                            | boutonRequêteTexte                  | trouver le mot de passe                                                     | Formulaire de saisie de recherche de mot de passe Texte du bouton SOUMETTRE                                                                                                    |
| Récupérer le texte du lien du mot de passe                    | boutonLienRequêteTexte              | Vous ne parvenez pas à vous connecter ?                                     | Texte du lien lors du passage du formulaire de saisie de connexion au formulaire de saisie de mot de passe                                                                     |
| thème                                                         | thème                               | rouge                                                                       | Sélectionnez un thème d'écran de connexion. BLEU, LIGHT_BLUE, VERT, LIGHT_GREEN, VIOLET, JAUNE, YELLOW_WHITE, HOT_PINK, ROUGE, NOIR                                            |

-   thème de connexion

![로그인\_테마](./asset/login_theme.png)

#### Changer l'image du logo du menu

-   plateforme.config.admin.\*

| article                                | propriété       | Exemple de saisie                 |
| -------------------------------------- | --------------- | --------------------------------- |
| URL de l'image du logo en haut du menu | view.logo-image | /images/logo.png                  |
| Image du logo en haut du menu ALT TEXT | view.logo-texte | Console d'administration Rchemist |

#### Paramètres d'affichage du tableau d'affichage du tableau de bord

-   platform.config.admin.dashboard.board.\*

| article                                                          | propriété       | Exemple de saisie           | explication                                                                                                                                                                                                 |
| ---------------------------------------------------------------- | --------------- | --------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Alias ​​du tableau d'affichage pour la demande de renseignements | alias           | avis, qna, gratuit, rapport | Si vous entrez l'alias d'un babillard enregistré, les dernières publications de ce babillard seront affichées sur le tableau de bord.                                                                       |
| Nombre de posts à afficher                                       | taille de ligne | 3                           | Le tableau de bord affiche un nombre défini de publications de chaque tableau d'affichage.                                                                                                                  |
| S'il faut afficher deux tableaux d'affichage par ligne           | vue partagé     | vrai                        | Lors de l'affichage des tableaux d'affichage sur l'écran du tableau de bord, choisissez d'afficher un tableau d'affichage par ligne (faux) ou de les diviser en deux tableaux d'affichage par ligne (vrai). |
| Temps de cache du tableau de bord (minutes)                      | rafraîchir      | 5                           | Les données du tableau d'affichage sont recherchées à chaque cycle de cache défini.                                                                                                                         |

#### Paramètres du menu des outils d'administration

-   plateforme.config.admin.menu.\*

| article             | propriété | Exemple de saisie                  | explication                                                                                                                                                                                                                                                                                          |
| ------------------- | --------- | ---------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Menu disponible     | explicite | TABLEAU DE BORD, CLIENT, LOCATAIRE | Au lieu du menu fourni par défaut dans l'outil d'administration, si vous définissez explicitement un menu à utiliser, seul ce menu sera affiché. La chaîne qui peut être saisie est la valeur SectionType définie dans la classe SectionType. S'il n'y a aucun paramètre, tout le menu sera affiché. |
| Menu non disponible | exclure   | CLIENT, LOCATAIRE                  | Vous pouvez supprimer un menu désigné comme propriété du menu des outils d'administration.                                                                                                                                                                                                           |

#### Paramètres de sécurité de connexion à l'outil d'administration (OTP)

Si vous activez l'option suivante, vous pouvez utiliser la fonction de connexion à 2 facteurs en utilisant la méthode d'authentification spécifiée lors de la connexion à l'outil d'administration.

-   plateforme.config.admin.notification.\*

| article                                                 | propriété                 | Exemple de saisie    | explication                                                                                                                                                                                                                    |
| ------------------------------------------------------- | ------------------------- | -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Comment trouver votre identifiant et votre mot de passe | trouver-id-mot de passe   | Email, SMS, Kakao... | Définissez le type de message pour envoyer les informations de connexion lors de la récupération de l'ID et du mot de passe de l'outil d'administration. Voir io.rchemist.common.jpa.domain.type.NotificationType.type.        |
| Connexion à 2 facteurs                                  | connexion à deux facteurs | SMS, CACAO...        | Définissez le type de message auquel le code sécurisé sera envoyé lors de l'utilisation de la fonction de connexion à 2 facteurs dans l'outil d'administration. Voir io.rchemist.common.jpa.domain.type.NotificationType.type. |

<br>

#### Paramètres de sécurité de connexion aux outils d'administration (MFA)

L'activation de l'option suivante vous permet d'utiliser l'authentification MFA lors de la connexion aux outils d'administration.

-   plateforme.config.admin.security.mfa.\*

| article                                    | propriété             | Exemple de saisie     | explication                                                                                               |
| ------------------------------------------ | --------------------- | --------------------- | --------------------------------------------------------------------------------------------------------- |
| Utiliser ou non l'AMF                      | forcer                | vrai                  | S'il faut utiliser MFA lors de la connexion aux outils d'administration ; la valeur par défaut est false. |
| Algorithme de code MFA                     | algorithme            | Il a vieilli à 12 ans | Algorithme de hachage pour le code d’émission MFA. La valeur par défaut est SHA512                        |
| Longueur du code MFA                       | longueur des chiffres | 12                    | Longueur du code d’émission MFA. La valeur par défaut est 6                                               |
| Période de validité du code MFA (secondes) | time-period           | 30                    | La période de validité (en secondes) du code d’émission MFA. La valeur par défaut est 15                  |

#### Paramètres GRID de l’outil d’administration

-   plateforme.config.admin.view.\*

| article                                                   | propriété                | Exemple de saisie | explication                                                                                                                                                                          |
| --------------------------------------------------------- | ------------------------ | ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Mode de filtre ListGrid                                   | filtre.type              | COUCHE            | Lors de l'affichage du filtre de recherche détaillé de la grille de liste, que ce soit pour l'afficher en Div au dessus de la liste (LAYER) ou en fenêtre modale (MODAL).            |
| Comment afficher la date dans listgrid                    | filtre.format-date       | DATEHEURE         | Lors de l'affichage de la date dans la liste de la grille de liste, s'il faut afficher uniquement la date (aaaa-MM-jj) (DATE) ou la date et l'heure (aaaa-MM-jj HH:mm:ss) (DATETIME) |
| Comment afficher les informations utilisateur de Listgrid | grille.champ utilisateur | identifiant       | Lors de l'affichage des informations utilisateur dans la liste de la grille de liste, s'il faut afficher l'ID de connexion (loginName) ou le nom d'utilisateur (userName).           |

#### Paramètres du menu Outils d'administration (LNB)

##### Afficher uniquement des menus spécifiques

Vous pouvez configurer pour afficher uniquement des menus spécifiques parmi les menus fournis dans l'outil d'administration. Dans ce cas, tous les autres menus non définis ne seront pas affichés.

-   plateforme.config.admin.menu.\*

| article                       | propriété | Exemple de saisie  | explication                                                                                          |
| ----------------------------- | --------- | ------------------ | ---------------------------------------------------------------------------------------------------- |
| Spécifier le menu d'affichage | explicite | CLIENT,KILOMÉTRAGE | Séparez les valeurs SectionType.type que vous souhaitez afficher avec et saisissez-les sans espaces. |

##### Ne pas afficher de menus spécifiques

Vous pouvez choisir de ne pas afficher certains menus parmi les menus fournis dans l'outil administrateur.

-   plateforme.config.admin.menu.\*

| article                       | propriété | Exemple de saisie  | explication                                                                                         |
| ----------------------------- | --------- | ------------------ | --------------------------------------------------------------------------------------------------- |
| Spécifier le menu d'exclusion | exclure   | CLIENT,KILOMÉTRAGE | Séparez les valeurs SectionType.type que vous souhaitez exclure avec et saisissez-les sans espaces. |

_Si vous définissez un menu défini sur explicite pour l'exclure, le menu ne sera pas visible._

#### Paramètres de la fonction de gestion des membres

Vous pouvez définir la fonction Outils d'administration > Gestion des membres.

-   plateforme.config.customer.create.admin.\*

| article                                           | propriété  | Exemple de saisie               | explication                                                                                                                                                                                                                                                                                                                                       |
| ------------------------------------------------- | ---------- | ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Créer un membre<br/>Utiliser ou non               | activé     | FAUX                            | S'il faut utiliser la fonction de création de membres dans l'outil d'administration (vrai) ou non (faux)<br/>La valeur par défaut est fausse                                                                                                                                                                                                      |
| Lors de la création d'un membre<br/>champ utilisé | des champs | identifiant<br/>,adresse e-mail | Spécifier explicitement les champs à utiliser lors de la création de membres dans l'outil d'administration<br/>Il n'y a pas de valeur par défaut et si cette valeur n'existe pas, la valeur par défaut de CustomerCreateForm est utilisée.<br/>Cependant, loginName et emailAddress sont ajoutés sans condition même s'ils ne sont pas spécifiés. |

-   platform.config.admin.view.customer.list.\*

| article                    | propriété    | Exemple de saisie | explication                                                                                                                |
| -------------------------- | ------------ | ----------------- | -------------------------------------------------------------------------------------------------------------------------- |
| Activer la fonction cachée | client caché | vrai              | S'il faut utiliser la fonction de membre masqué dans la gestion des membres de l'outil d'administration. (faux par défaut) |

#### etc.

-   plateforme.config.admin.\*

| article                                | propriété             | Exemple de saisie | explication                                                                                                                                                                            |
| -------------------------------------- | --------------------- | ----------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Si les métadonnées sont mises en cache | métadonnées.cacheable | vrai              | S'il faut mettre en cache les métadonnées de l'outil d'administration. Doit être défini sur true sur les systèmes d’exploitation. (vrai par défaut)                                    |
| nom du site                            | nom du site           | RCHIMISTE         | Nom du site utilisé dans l'application de l'outil d'administration. (aucun défaut). Utilisé lors de l'envoi d'un message ou de l'affichage du nom du site dans l'interface utilisateur |

### 4. Point de terminaison du contrôleur déjà réservé

L'URL du point de terminaison suivante est déjà utilisée au sein de la plateforme. L'URL ci-dessous ne peut pas être utilisée lors d'un développement ultérieur.

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
    -   Elle n'est pas prise en charge par les projets communautaires, mais l'URL du point de terminaison correspondant ne peut pas être utilisée.
-   /événement/\*\*
    -   Il n'est pas pris en charge en mode service monolithique, mais l'URL du point de terminaison correspondant ne peut pas être utilisée.
-   /locataire/\*\*
    -   io.rchemist.tenant.controller.TenantController
    -   io.rchemist.tenant.security.AdminUserAuthenticationController
    -   io.rchemist.tenant.security.AdminUserController
    -   io.rchemist.tenant.security.ApiKeyController
    -   io.rchemist.tenant.security.ApiSecurityController
    -   io.rchemist.tenant.security.SecurityController
    -   io.rchemist.tenant.translate.TranslateController

<br>

<br>

## courir

### 1. Préparez-vous

Afin de développer un service, les programmes ci-dessous doivent d'abord être installés.

-   maven 3.5+ (la version n'a pas vraiment d'importance)
-   jdk 11 (ouvertjdk)

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
-   Vaadin 22+

...

### 3. paramètres pom

Dans les paramètres du référentiel du pom racine du projet, vous devez enregistrer le référentiel comme suit.

(Il est déjà configuré dans le projet passe-partout, vous pouvez donc l'utiliser tel quel)

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

#### 2) dépendance micro-admin-mono ici

micro-admin-mono est une dépendance de service qui regroupe chaque microservice de RCM-MicroShop en un seul pour une utilisation pratique en mode monolithique, puis intègre le site d'administration, y compris le projet ADMIN.

    <dependencies>
        <dependency>
          <groupId>io.rchemist</groupId>
          <artifactId>micro-admin-mono</artifactId>
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

_Lors de l'ajout de micro-admin-mono en tant que dépendance, vous devez ajouter les paramètres liés à Spring Cloud comme exclusion. Il s’agit de l’empêcher de fonctionner en mode microservice._

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

### 4. Paramètres yml de l'application

#### 1) séquence de chargement yml

-   (1) application.yml
-   (2) application-override.yml de chaque service référencé
-   (3) application-{spring.profiles.active}-override.yml pour chaque service auquel il fait référence
-   (4) application-override.yml
-   (5) application-{spring.profiles.active}-override.yml

Les services de (2) à (3) sont chargés dans l'ordre suivant : commun, actif, cms, commerce, client, événement et locataire.

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
      port: 9800
      host: #ELASTIC_SEARCH_ADDRESS
      username:
      password:
  elasticsearch:
    rest:
      port: 9800
      host: #ELASTIC_SEARCH_ADDRESS
      username: 
      password: 
  datasource:
    rcm-elastic: 
      common: # 서비스명: for override spring.data.elasticsearch.* values each at services
        port: 9800
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

Fondamentalement, il vous suffit d'exécuter java -jar demo-micro-admin-mono-0.0.1-SNAPSHOT.jar. Cependant, si vous faites cela, tous les chemins de classe continueront à être recherchés lorsque le fichier jar Spring Boot sera chargé, ce qui rendra le démarrage de SpringBoot très lent.

Pour éviter ce problème, utilisez la syntaxe suivante :

```shell
java -jar -Xverify:none -XX:TieredStopAtLevel=1 demo-micro-admin-mono-0.0.1-SNAPSHOT.jar
```
