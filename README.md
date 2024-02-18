# release-micro
Release server for RCM micro-services

# Getting Started

## Admin Example
https://github.com/rchemist/demo-micro-admin-mono

## Front Example
https://github.com/rchemist/demo-micro-front-mono

## CommonSpring6 만 사용
```xml
  <!-- 중략 -->

  <parent>
    <groupId>io.rchemist</groupId>
    <artifactId>rcm-platform-bom</artifactId>
    <version>0.0.5-SNAPSHOT</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <!-- 중략 -->

  <dependencies>
    <dependency>
        <groupId>io.rchemist</groupId>
        <artifactId>common-spring6</artifactId>
    </dependency>
  </dependencies>
  

```

## 필요한 모듈만 사용

```xml
  <!-- 중략 -->

  <parent>
    <groupId>io.rchemist</groupId>
    <artifactId>rcm-platform-bom</artifactId>
    <version>0.0.5-SNAPSHOT</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <!-- 중략 -->

  <dependencies>
    <!-- ASSET 관리 모듈 -->
    <dependency>
        <groupId>io.rchemist</groupId>
        <artifactId>micro-asset</artifactId>
    </dependency>
    <!-- 게시판 모듈 -->
    <dependency>
        <groupId>io.rchemist</groupId>
        <artifactId>module-article</artifactId>
    </dependency>
    <!-- 중략 -->
  </dependencies>
  

```

# Repositories

## common

### common-admin-entity
@AdminEntity 어노테이션을 사용하여 관리자 페이지를 생성할 수 있습니다.

### common-class-transformer
ClassTransformer를 사용하여 클래스를 변환할 수 있습니다.

### common-data-jpa
SpringData 와 통합해 캐싱, 검색, CRUD 를 자동화 할 수 있습니다.

### common-data-jpa-atomikos
MicroService 모드에서 Atomikos 를 사용하여 분산 트랜잭션을 사용할 수 있습니다.

### common-documentation
테이블정의서, API 문서를 자동화 할 수 있습니다.

### common-extension-manager
원본 코드에 영향을 주지 않고 기능을 확장할 수 있습니다.

### common-spring6
Spring6 기반 프로젝트를 생성할 때 사용하는 Boilerplate 입니다.
common 프로젝트가 모두 포함되어 있습니다.

### common-utils
RCM 프로젝트에서 사용하는 Util 모음입니다.

## cloud-*
MicroService 를 지원하기 위한 모듈입니다.

<br/>

## micro

### micro-admin
관리자도구 UI를 제공합니다.

### micro-asset
ASSET 관리 기능 및 API를 제공합니다.

### micro-cms
CMS 기능 및 API를 제공합니다.

### micro-customer

고객 관리 기능 및 API를 제공합니다.

### micro-event

이벤트 관리 기능 및 API를 제공합니다.  

### micro-template

Thymeleaf 템플릿을 처리하기 위한 모듈입니다. Thymeleaf 을 사용할 경우에만 프로젝트에 포함합니다.

### micro-tenant

멀티사이트, 관리자 기능을 제공합니다.

<br/>

## modules

### module-admin

관리자 모듈

### module-article

게시판 모듈

### module-custom-field

커스텀필드 모듈

### module-jwt
JWT 모듈

### module-korean-token-filter
한글 검색 확장을 위한 필터

### notification
메일/SMS/카카오톡/푸시 등 알림 모듈
