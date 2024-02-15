# demo-micro-admin-mono

This is a boilerplate code service that can be used when running RCM-MicroShop's ADMIN service in Monolithic mode.
The code for the RCM-MicroShop platform is<https://github.com/rchemist/release-micro>It is open to the public.

## Developing services

### 1. Create a project

Download or fork this boilerplate code project and create a new project.

_At this time, the original contents of the existing pom.xml file and MonolithicAdminApplication.java file must be maintained. (Additions are possible)_

<br>

### 2. Add the necessary code

-   Add dependency to pom.xml<br>To add dependencies that have not been added to the existing project, add them through the dependency settings in pom.xml.
-   Most useful libraries when developing web services, such as lombok, hibernate, and common-utils, have been added, so first check the dependency details in pom.xml and then add the necessary ones.
-   Developing services<br>You can start development the exact same way you would develop any other project. However, before development`실행하기`Please be sure to read and understand the contents.
-   For reference, the built-in ADMIN site is developed with Vaadin.

<br>

### 3. Administrator tool settings

#### Login screen settings

-   The login screen setting property is a sub-element of platform.config.admin.view.login.\*.

| item                               | property                        | Input example                                  | explanation                                                                                                                                              |
| ---------------------------------- | ------------------------------- | ---------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------- |
| login title                        | title                           | Rchemist Admin Console                         | Title displayed on login form                                                                                                                            |
| Additional explanatory message     | description                     | Welcome to RCM MicroShop Admin Console!        | Explanatory text displayed at the bottom of the title                                                                                                    |
| Login logo image                   | logoImage                       | ./image/login_logo.png                         | Logo image to display at the top of the login form. If you do not enter anything, nothing will be displayed, and it must be saved within 40px in height. |
| Login form title                   | loginFormTitle                  | log in                                         | Text to be displayed at the top of the login input form                                                                                                  |
| Retrieve password form title       | requestFormTitle                | find password                                  | Text to be displayed at the top of the password search input form                                                                                        |
| ID label                           | labelUsername                   | id                                             | Explanatory text displayed at the bottom of the title                                                                                                    |
| password label                     | labelPassword                   | password                                       | Explanatory text displayed at the bottom of the title                                                                                                    |
| Retrieve password input form label | labelRequest                    | ID or email                                    | Explanatory text displayed at the bottom of the title                                                                                                    |
| Login error title                  | errorLoginSubmitTitle           | I can't log in.                                | When login fails! Error title to be displayed with                                                                                                       |
| Login error message                | errorLoginSubmitDescription     | Please verify your username or password again. | Login failure details                                                                                                                                    |
| Password retrieval error title     | errorRequestSubmitTitle         | Your user information cannot be verified.      | When password recovery fails! Error title to be displayed with                                                                                           |
| Password retrieval error message   | error RequestSubmit Description | Please check your ID or email again.           | Detailed explanation when password recovery fails                                                                                                        |
| Login SUBMIT                       | buttonLoginText                 | log in                                         | Login input form SUBMIT button text                                                                                                                      |
| Login link text                    | buttonLinkLoginText             | To the login screen                            | Link text when switching from the password search input form to the login input form                                                                     |
| Find your password SUBMIT          | buttonRequestText               | find password                                  | Password search input form SUBMIT button text                                                                                                            |
| Retrieve password link text        | buttonLinkRequestText           | Can't log in?                                  | Link text when switching from the login input form to the password input form                                                                            |
| theme                              | theme                           | red                                            | Select a login screen theme. BLUE, LIGHT_BLUE, GREEN, LIGHT_GREEN, VIOLET, YELLOW, YELLOW_WHITE, HOT_PINK, RED, BLACK                                    |

-   login theme

![로그인\_테마](./asset/login_theme.png)

#### Change menu logo image

-   platform.config.admin.\*

| item                                       | property        | Input example          |
| ------------------------------------------ | --------------- | ---------------------- |
| Logo image URL at the top of the menu      | view.logo-image | /images/logo.png       |
| Logo image at the top of the menu ALT TEXT | view.logo-text  | Rchemist Admin Console |

#### Dashboard bulletin board view settings

-   platform.config.admin.dashboard.board.\*

| item                                            | property   | Input example             | explanation                                                                                                                                                                    |
| ----------------------------------------------- | ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Bulletin board alias for inquiry                | alias      | notice, qna, free, report | If you enter the alias of a registered bulletin board, the latest posts from that bulletin board will be displayed on the dashboard.                                           |
| Number of posts to display                      | row-size   | 3                         | The dashboard displays a set number of posts from each bulletin board.                                                                                                         |
| Whether to display two bulletin boards per line | split-view | true                      | When displaying bulletin boards on the dashboard screen, select whether to display one bulletin board per line (false) or split them into two bulletin boards per line (true). |
| Dashboard cache time (minutes)                  | refresh    | 5                         | Bulletin board data is searched at each set cache cycle.                                                                                                                       |

#### Administrator tools menu settings

-   platform.config.admin.menu.\*

| item               | property | Input example               | explanation                                                                                                                                                                                                                                                                                      |
| ------------------ | -------- | --------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Available menu     | explicit | DASHBOARD, CUSTOMER, TENANT | Instead of the menu provided by default in the administrator tool, if you explicitly set a menu to use, only that menu will be displayed. The string that can be entered is the SectionType value defined in the SectionType class. If there are no settings, the entire menu will be displayed. |
| Menu not available | exclude  | CUSTOMER, TENANT            | You can remove a menu designated as property from the administrator tools menu.                                                                                                                                                                                                                  |

#### Administrator tool login security settings (OTP)

If you enable the following option, you can use the 2-factor login function using the specified authentication method when logging in to the administrator tool.

-   platform.config.admin.notification.\*

| item                             | property         | Input example        | explanation                                                                                                                                                                              |
| -------------------------------- | ---------------- | -------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| How to find your ID and password | find-id-password | EMAIL, SMS, KAKAO... | Set the type of message to send login information when retrieving the ID and password of the administrator tool. See io.rchemist.common.jpa.domain.type.NotificationType.type.           |
| 2 Factor Login                   | two-factor-login | SMS, COCOA...        | Set what type of message the Secure code will be sent to when using the 2-factor login function in the administrator tool. See io.rchemist.common.jpa.domain.type.NotificationType.type. |

<br>

#### Administrator tools login security settings (MFA)

Enabling the following option allows you to use MFA authentication when logging in to Administrator Tools.

-   platform.config.admin.security.mfa.\*

| item                               | property      | Input example | explanation                                                                        |
| ---------------------------------- | ------------- | ------------- | ---------------------------------------------------------------------------------- |
| Whether to use MFA                 | force         | true          | Whether to use MFA when logging in to administrator tools; default value is false. |
| MFA Code Algorithm                 | algorithm     | He got old 12 | Hashing algorithm for MFA issuance code. Default is SHA512                         |
| MFA Code Length                    | digits-length | 12            | Length of the MFA issuance code. Default is 6                                      |
| MFA Code validity period (seconds) | time-period   | 30            | The validity period (in seconds) of the MFA issuance code. Default is 15           |

#### Administrator tool GRID settings

-   platform.config.admin.view.\*

| item                                     | property           | Input example | explanation                                                                                                                                                        |
| ---------------------------------------- | ------------------ | ------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| ListGrid Filter Mode                     | filter.type        | LAYER         | When displaying the detailed search filter of the list grid, whether to display it as a Div above the list (LAYER) or as a modal window (MODAL).                   |
| How to display date in listgrid          | filter.date-format | DATETIME      | When displaying the date in the list of list grid, whether to display only the date (yyyy-MM-dd) (DATE) or both the date and time (yyyy-MM-dd HH:mm:ss) (DATETIME) |
| How to display listgrid user information | grid.user-field    | loginName     | When displaying user information in the list of list grid, whether to show the login ID (loginName) or the user name (userName).                                   |

#### Administrator tools menu (LNB) settings

##### Show only specific menus

You can set to display only specific menus among the menus provided in the administrator tool. In this case, all other menus that are not set will not be displayed.

-   platform.config.admin.menu.\*

| item                 | property | Input example    | explanation                                                                                      |
| -------------------- | -------- | ---------------- | ------------------------------------------------------------------------------------------------ |
| Specify display menu | explicit | CUSTOMER,MILEAGE | Separate the SectionType.type values ​​you want to display with , and enter them without spaces. |

##### Do not display specific menus

You can set to not display certain menus among the menus provided in the administrator tool.

-   platform.config.admin.menu.\*

| item                   | property | Input example    | explanation                                                                                      |
| ---------------------- | -------- | ---------------- | ------------------------------------------------------------------------------------------------ |
| Specify exclusion menu | exclude  | CUSTOMER,MILEAGE | Separate the SectionType.type values ​​you want to exclude with , and enter them without spaces. |

_If you set a menu set to explicit to exclude, the menu will not be visible._

#### Member management function settings

You can set the Admin Tools > Membership Management function.

-   platform.config.customer.create.admin.\*

| item                                    | property | Input example               | explanation                                                                                                                                                                                                                                                                                          |
| --------------------------------------- | -------- | --------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Create member<br/>Whether or not to use | enabled  | false                       | Whether to use the member creation function in the administrator tool (true) or not (false)<br/>Default is false                                                                                                                                                                                     |
| When creating a member<br/>field used   | fields   | loginName<br/>,emailAddress | Explicitly specify fields to use when creating members in the administrator tool<br/>There is no default value, and if this value does not exist, the default value of CustomerCreateForm is used.<br/>However, loginName and emailAddress are added unconditionally even if they are not specified. |

-   platform.config.admin.view.customer.list.\*

| item                     | property        | Input example | explanation                                                                                        |
| ------------------------ | --------------- | ------------- | -------------------------------------------------------------------------------------------------- |
| Activate hidden function | hidden-customer | true          | Whether to use the hidden member function in administrator tool member management. (default false) |

#### etc

-   platform.config.admin.\*

| item                       | property           | Input example | explanation                                                                                                                           |
| -------------------------- | ------------------ | ------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| Whether metadata is cached | metadata.cacheable | true          | Whether to cache administrator tool metadata. Must be set to true on operating systems. (default true)                                |
| site name                  | site-name          | RCHEMIST      | Site name used in the administrator tool application. (no default). Used when sending a message or displaying the site name in the UI |

### 4. Already reserved Controller Endpoint

The following endpoint URL is already in use inside the platform. The URL below cannot be used during further development.

**(Important) If you develop an endpoint with that URL, the service will not run.**

You can check the actual source code by opening the project in the IDE and exploring each Controller below.

-   /asset/\*\*
    -   io.rchemist.asset.controller.AssetManageController
    -   io.rchemist.asset.controller.AssetViewController
-   /customer/\*\*
    -   io.rchemist.customer.controller.CustomerController
    -   io.chemist.custom.controller.Customer AuthenticationController
-   /cms/\*\*
    -   io.rchemist.cms.group.controller.GroupController
    -   io.rchemist.cms.board.controller.BoardController
    -   io.rchemist.cms.page.controller.PageController
    -   io.rchemist.cms.page.controller.PageFieldController
-   /commerce/\*\*
    -   It is not supported by community projects, but the corresponding endpoint URL cannot be used.
-   /event/\*\*
    -   It is not supported in monolithic service mode, but the corresponding endpoint URL cannot be used.
-   /tenant/\*\*
    -   io.rchemist.tenant.controller.TenantController
    -   io.rchemist.tenant.security.AdminUserAuthenticationController
    -   io.rchemist.tenant.security.AdminUserController
    -   io.rchemist.tenant.security.ApiKeyController
    -   io.rchemist.tenant.security.ApiSecurityController
    -   io.rchemist.tenant.security.SecurityController
    -   io.rchemist.tenant.translate.TranslateController

<br>

<br>

## run

### 1. Get ready

In order to develop a service, the programs below must first be installed.

-   maven 3.5+ (version doesn't really matter)
-   jdk 11(openjdk)

### 2. Project development environment

The RCM Micro-Shop platform was developed with the following technologies:

-   Spring Boot 2.6.1
-   Spring Data JPA 2.6.1
-   Hibernate 5.6.0.Final
-   Hibernate Search 6.1.1.Final
-   QueryDsl 5.0.0
-   Elasticsearch 7.15.2
-   MongoDB 5
-   mysql 5.8+
-   Vaadin 22+

...

### 3. pom settings

In the repository settings of the project root pom, you must register the repository as follows.

(It is already set up in the boilerplate project, so you can just use it as is)

<br>

#### 1) GitHub repository settings

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

#### 2) micro-admin-mono dependency 추가

micro-admin-mono is a service dependency that bundles each microservice of RCM-MicroShop into one for convenient use in monolithic mode and then embeds the Admin site, including the ADMIN project.

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

_When adding micro-admin-mono as a dependency, you must add settings related to spring cloud as exclusion. This is to prevent it from operating in microservice mode._

<br>

#### 3) Project build settings

Add the following syntax to the build script so that a jar file for distribution is created when building Maven.

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

This setting allows projects created with SpringBoot to be built as fat jars and run independently.

<br>

### 4. Application yml settings

#### 1) yml load sequence

-   (1) application.yml
-   (2) application-override.yml of each referenced service
-   (3) application-{spring.profiles.active}-override.yml for each service it references
-   (4) application-override.yml
-   (5) application-{spring.profiles.active}-override.yml

Services from (2) to (3) are loaded in the following order: common, asset, cms, commerce, customer, event, tenant.\`

**If you don't want to worry about priority, you can set it in application-override.yml.**

<br>

#### 2) Application yml settings

Set DB, redis, elastic search server connection information, etc.

When applying settings, be sure to pay attention to yml load priority.

There is no need to set the same settings in all yml.

As explained in 1) above, you only need to set it once in the high-priority yml.

##### (1) Service port

The default service port is set to 8000.

This setting can also be changed in application yml.

```yaml
server:
  port: 8000
https:
  port: 8010
```

##### (2) DB connection information settings

###### Enabling single DB in monolithic mode

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

_If you want to separate the DB by service, you must additionally configure the above DB settings for each service in application.yml for each service in /main/resources/service-properties/._

<br>

###### Using mongoDB

By using mongoDB, you can expect improved performance compared to using RDB in some services such as ASSET and BOARD. To use mongoDB, set as follows.

```yaml
platform:
  config:
    provider:
      mongo: true
```

Then, set mongoDB connection information using the yml settings for each service as follows.

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

_When setting up mongoDB, you must specify which collection to use for each service._

<br>

###### REDIS connection information settings

The default provider of the platform's Spring Cache is set to jcache. The actual provider of jcache is REDIS.

```yaml
spring:
  cache:
    type: jcache
  redis:
    host: #REDIS_ADDRESS
    port: 6379
    password: 
```

###### redisson settings

Set redisson information to use REDIS cache in Hibernate.

Change redisson’s REDIS connection information in /main/resources/redisson-dev.yml.

```yaml
singleServerConfig:
  password: #REDIS PASSWORD
  address: #REDIS IP
```

<br>

###### ElasticSearch Settings

If you use the ES search engine, you can improve search performance.

To use ES, set as follows.

First, set as follows to activate HibernateSearch.

`/main/resources/bootstrap.yml`

```yaml
platform:
  config:
    provider:
      search: hibernate
```

<br>

And change the application settings as follows.

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

For further details on each option that can be set, please refer to the description of each item in the application yml.

<br>

#### 2) Service execution environment settings

The service execution environment is classified as follows, and each overrides yml settings in addition to application.yml settings.

-   local: local development environment<br>application-local.yml<br>application-local-override.yml
-   development: development server<br>application-development.yml<br>application-development-override.yml
-   staging: staging server<br>application-staging.yml<br>application-staging-override.yml
-   production: production server<br>application-production.yml<br>application-production-override.yml

<br>

If no settings are made with the jvm option, it runs in local mode by default.

    String profile = System.getProperty("spring.profiles.active");
    if(StringUtils.isEmpty(profile)) {
      application.setAdditionalProfiles("local");
    }

<br>

To change the execution environment, define the environment with the following options in jvm options or bootstrap.yml.

    #운영 서버 환경 설정 예시
    spring.profiles.active=production 

### 6. Deployment and execution

#### 1) build

In the project root directory`mvn clean install`Run .

If you want to extract only the jar file for distribution`mvn package`You can also run .

If the following message is output as a result of execution, it is completed normally.

```shell
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

<br>

#### 2) run

Before executing, be sure to check whether the jar is a fat jar.<br>(The file must be at least 1~200 MB)

Basically, you just need to run java -jar demo-micro-admin-mono-0.0.1-SNAPSHOT.jar. However, if you do this, all class paths will continue to be searched when the spring boot jar file is loaded, making SpringBoot startup very slow.

To avoid this problem, use the following syntax:

```shell
java -jar -Xverify:none -XX:TieredStopAtLevel=1 demo-micro-admin-mono-0.0.1-SNAPSHOT.jar
```
