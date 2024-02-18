# release-micro

Release server for RCM micro-services

# Getting Started

## Admin Example

<https://github.com/rchemist/demo-micro-admin-mono>

## Front Example

<https://github.com/rchemist/demo-micro-front-mono>

## Use only CommonSpring6

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

## Use only the modules you need

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

You can create an admin page using the @AdminEntity annotation.

### common-class-transformer

You can transform a class using ClassTransformer.

### common-data-jpa

Integrate with Spring Data to automate caching, search, and CRUD.

### common-tata-ga-atomic

You can use distributed transactions using Atomikos in MicroService mode.

### common-documentation

Table definitions and API documents can be automated.

### common-extension-manager

You can extend functionality without affecting the original code.

### common-spring6

Boilerplate used when creating Spring6-based projects.
All common projects are included.

### common-utils

This is a collection of Utils used in RCM projects.

## cloud-\*

This is a module to support MicroService.

<br/>

## micro

### micro-admin

Provides administrator tool UI.

### micro-asset

Provides ASSET management functions and API.

### micro-cms

Provides CMS functions and API.

### micro-customer

Provides customer management features and API.

### micro-event

Provides event management functions and API.

### micro-template

A module for processing Thymeleaf templates. Include it in your project only if you use Thymeleaf.

### micro-tenant

Provides multi-site and administrator functions.

<br/>

## modules

### module-admin

admin module

### module-article

bulletin board module

### module-custom-field

Custom field module

### module-jwt

JWT module

### module-korean-token-filter

Filters to expand Korean search

### notification

Notification modules such as mail/SMS/KakaoTalk/push
