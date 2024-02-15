# demo-micro-front-mono

`RCM-MicroShop 플랫폼은 마이크로 서비스 플랫픔으로, 각 서비스 별 API 를 제공하며, JWT 토큰을 기반으로 한 인증을 지원합니다. 이 샘플 코드는 API를 사용하지 않고 직접 플랫폼 코드와 통합하는 SpringBoot 프로젝트입니다.`

**boilerplate code that can be used when running RCM-MicroShop in Monolithic mode**

-   For authentication, use SpringSecurity's default Authentication instead of JWT.
-   Call service methods directly instead of RESTful API. (You can also use RESTful API by setting platform.config.webmvc.front=true.)

`RCM-MicroShop 플랫폼의 코드는 https://github.com/rchemist/release-micro 에 공개되어 있습니다.`

## Developing services

### 1. Create a project

Download or fork this boilerplate code project and create a new project.

_At this time, the original contents of the existing pom.xml file and MonolithicFrontApplication.java file must be maintained. (Additions are possible)_

<br>

### 2. Add the necessary code

-   Add dependency to pom.xml<br>To add dependencies that have not been added to the existing project, add them through the dependency settings in pom.xml.
-   Most useful libraries when developing web services, such as lombok, hibernate, and common-utils, have been added, so first check the dependency details in pom.xml and then add the necessary ones.
-   Developing services<br>You can start development the exact same way you would develop any other project. However, before development`실행하기`Please be sure to read and understand the contents.

<br>

### 3. Already reserved Controller Endpoint

#### Built-in Controller Endpoint

Built-in Controller can be activated through the following settings. (Default true)

```yaml
platform:
  config:
    webmvc:
      front: true   # 기본값이 true
```

If this setting is enabled, the endpoint URL below is already in use inside the platform and cannot be used in further development.

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
    -   It is not supported by community projects, but use of this endpoint URL is prohibited.
-   /event/\*\*
    -   It is not supported in monolithic service mode, but use of the corresponding endpoint URL is prohibited.
-   /tenant/\*\*
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

**if`platform.config.webmvc.front`If the value is set to false, all Front Controllers provided by the RCM MicroShop platform will not be registered as beans, and Endpoints will not be added. However, all other beans such as Service/Dao operate normally, so if you want to develop Front View independently, you can set this value to false.**

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

#### 2) micro-front-mono dependency 추가

micro-front-mono is a project that bundles each microservice of RCM-MicroShop into one for convenient use in monolithic mode.

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

_When adding micro-front-mono as a dependency, you must add settings related to spring cloud as exclusion. This is to prevent it from operating in microservice mode._

There is also micro-admin-mono, which corresponds to Admin instead of Front.

You can use it in the same way when configuring the Admin service. An example for this is<https://github.com/rchemist/demo-micro-shop>You can check it here.

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

#### 4) micro-front-mono 의 RCM Micro-Shop dependencies

-   cloud-common
    Shared resource management of microservices.
-   micro-asset
    Asset data management<br>File Upload Download and AssetView
-   micro-cms<br>notice board<br>Community (Group)<br>CMS-related services such as pages
-   micro-customer<br>Front member service
-   micro-tenant<br>Admin related services<br>Admin member<br>Administrator tool-related services such as security, custom fields, and custom options

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

### 5. Run Monolithic Front Application

Run MonolithicFrontApplication.

#### 1) Execute Class Transform

The RCM platform uses ClassTransform technology to change Class in Runtime ClassLoader. ClassTransform must be performed when a SpringBoot application runs.

    PlatformClassTransformHelper.initializeDefaultTransformedClasses();

If application.run() is performed without this code being executed, the application will not run.

For more details, please refer to io.rchemist.front.MonolithicFrontApplication.java.

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

Basically, you just need to run java -jar demo-micro-front-mono-0.0.1-SNAPSHOT.jar. However, if you do this, all class paths will continue to be searched when the spring boot jar file is loaded, making SpringBoot startup very slow.

To avoid this problem, you can use the following syntax:

```shell
java -jar -Xverify:none -XX:TieredStopAtLevel=1 demo-micro-front-mono-0.0.1-SNAPSHOT.jar
```
