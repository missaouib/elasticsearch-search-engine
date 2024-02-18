# version-micro

Serveur de publication pour les micro-services RCM

# Commencer

## Exemple d'administrateur

<https://github.com/rchemist/demo-micro-admin-mono>

## Exemple avant

<https://github.com/rchemist/demo-micro-front-mono>

## Utilisez uniquement CommonSpring6

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

## Utilisez uniquement les modules dont vous avez besoin

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

# Dépôts

## commun

### entité-admin-commune

Vous pouvez créer une page d'administration à l'aide de l'annotation @AdminEntity.

### transformateur de classe commune

Vous pouvez transformer une classe à l'aide de ClassTransformer.

### données-communes-jpa

Intégrez-vous à Spring Data pour automatiser la mise en cache, la recherche et CRUD.

### commun-tata-ga-atomique

Vous pouvez utiliser des transactions distribuées à l'aide d'Atomikos en mode MicroService.

### documentation commune

Les définitions de tables et les documents API peuvent être automatisés.

### gestionnaire d'extensions communes

Vous pouvez étendre les fonctionnalités sans affecter le code d'origine.

### ressort-commun6

Boilerplate utilisé lors de la création de projets basés sur Spring6.
Tous les projets courants sont inclus.

### utilitaires communs

Il s'agit d'une collection d'utilitaires utilisés dans les projets RCM.

## nuage-\*

Il s'agit d'un module pour prendre en charge MicroService.

<br/>

## micro

### micro-administrateur

Fournit l’interface utilisateur de l’outil d’administrateur.

### micro-actif

Fournit des fonctions de gestion des ACTIFS et une API.

### micro-cms

Fournit des fonctions CMS et une API.

### micro-client

Fournit des fonctionnalités de gestion client et une API.

### micro-événement

Fournit des fonctions de gestion d’événements et une API.

### micro-modèle

Un module de traitement des modèles Thymeleaf. Incluez-le dans votre projet uniquement si vous utilisez Thymeleaf.

### micro-locataire

Fournit des fonctions multi-sites et d’administrateur.

<br/>

## modules

### module-admin

module d'administration

### article-module

module de tableau d'affichage

### module-champ-personnalisé

Module de champ personnalisé

### module-jwt

Module JWT

### module-coréen-jeton-filtre

Filtres pour élargir la recherche coréenne

### notification

Modules de notification tels que mail/SMS/KakaoTalk/push
