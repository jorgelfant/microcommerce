package com.ecommerce.microcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
Spring Initializr

Avez-vous déjà commandé une salade à composer au restaurant ? Et bien, Spring Initializr fait plus ou moins
la même chose : il vous permet de composer votre application selon vos besoins.

Cette classe, générée automatiquement par Spring Boot, est le point de démarrage de l'application

Elle lance, entre autres, la classe SpringApplication, responsable du démarrage de l'application Spring.
Cette classe va créer le fameux ApplicationContext dans lequel iront toutes les configurations générées automatiquement
ou ajoutées par vos soins.

Mais le plus important ici, c'est bien sûr l'annotation @SpringBootApplication, qui est une simple encapsulation de
trois annotations :

    1. @Configuration : donne à la classe actuelle la possibilité de définir des configurations qui iront remplacer
      les traditionnels fichiers XML. Ces configurations se font via des Beans.

    2. @EnableAutoConfiguration : l'annotation vue précédemment qui permet, au démarrage de Spring, de générer
       automatiquement les configurations nécessaires en fonction des dépendances situées dans notre classpath.

    3. @ComponentScan : Indique qu'il faut scanner les classes de ce package afin de trouver des Beans de configuration.

Nous reviendrons sur la configuration dans une prochaine section. Si vous souhaitez personnaliser finement le
comportement de Spring Boot, il vous suffit de remplacer @SpringBootApplication par ces 3 annotations  :

@Configuration
@EnableAutoConfiguration
@ComponentScan

Cette modification permet notamment de paramétrer l'annotation @ComponentScan, par exemple pour cibler des fichiers à scanner.

application.properties
Ce fichier est votre ami (oui, comme Google :)). Ce fichier va vous permettre de modifier très simplement un nombre
impressionnant de configurations liées à Spring Boot et ses dépendances. Par exemple : changer le port d'écoute de Tomcat,
l'emplacement des fichiers de log, les paramètres d'envoi d'emails, etc... Je vous invite à jeter un oeil sur la liste complète
ici. Nous reviendrons sur ce fichier dans une prochaine section.

MicrocommerceApplicationTests.java
Ce fichier vous permet d'écrire vos tests.

Exécuter l'application
Nous n'avons rien ajouté dans notre application pour l'instant, mais nous pouvons déjà l'exécuter. Si vous n'avez pas
le panneau Maven à droite, rendez-vous en bas à gauche d'IntelliJ pour l'activer :

Double-cliquez ensuite sur "Install" sous "LilfeCycle" de Maven dans le panneau de droite :

L'application sera compilée et vous retrouverez le jar sous le nouveau dossier "Target" créé pour l'occasion par Maven :

Exécutez enfin l'application depuis un terminal comme n'importe quel jar grâce à la commande :

java -jar Chemin/vers/microcommerce/target/microcommerce-0.0.1-SNAPSHOT.jar

Dans les dernières lignes vous remarquerez cette phrase : " Tomcat started on port(s): 8080 (http)" Ce qui vous
indique que votre application tourne et qu'elle est en écoute grâce à tomcat sur le port 8080.

Et bien, rendez-vous dans votre navigateur à l'adresse http://localhost:8080. Vous obtenez alors cette magnifique erreur,
car nous n'avons encore pas fourni d'éléments  à afficher !

Pour faciliter l'exécution de notre service, vous pouvez profiter d'un raccourci d'IntelliJ. Pour cela, faites un clic
droit sur le nom de la classe contenant la méthode main (MicroserviceApplication.java) puis cliquez sur "Run"  dans le
menu contextuel qui s'affiche. La figure suivante illustre cette opération :

L'application se lance et vous pouvez avoir le retour dans la console intégrée en bas. Cette opération est à faire
uniquement la première fois. Pour les fois suivantes, il suffira d'appuyer sur le bouton Play en haut à droite pour
la démarrer et l'arrêter avec le bouton rouge :

N'oubliez pas, dans votre console, d'arrêter l'application afin de libérer le port pour une utilisation ultérieure.

Vous pouvez en profiter pour essayer la personnalisation de l'auto-configuration de Spring Boot via application.properties. Vous pouvez ainsi changer un nombre impressionnant de paramètres grâce à une simple ligne dans le fichier application.properties.

Changeons par exemple le port du serveur. Ajoutez tout simplement cette ligne :

server.port 9090
Exécutez maintenant l'application. Spring vous indique alors que l'application tourne désormais sur le port 9090 :

Figure 25

Vous pouvez le vérifier en vous rendant également à l'url http://localhost:9090.

buscar este en el dossier resources abajo del dossier java todoo esto en src/main/resources/application.properties

//----------------------------------------------------------------------------------------------------------------------
//                                        Créez l'API REST
//----------------------------------------------------------------------------------------------------------------------
Nous arrivons maintenant au coeur du Microservice que nous voulons développer. Ce Microservice va devoir être RESTful
et donc pouvoir communiquer de cette manière.

Il est indispensable, si vous n'êtes pas déjà à l'aise avec REST, de consulter ce cours afin de pouvoir comprendre
ce qui suit.

Quels sont les besoins ?

Nous avons besoin d'un Microservice capable de gérer les produits. Pour cela, il doit pouvoir exposer une API REST
qui propose toutes les opérations CRUD (Create, Read, Update, Delete).

Nous allons donc avoir :

       * Une classe Produit qui représente les caractéristiques d'un produit (nom, prix, etc.) ;

       * Un contrôleur qui s'occupera de répondre aux requêtes CRUD et de faire les opérations nécessaires.

Nous voulons donc pouvoir appeler notre Microservice sur les URLs suivantes :

       * Requête GET à /Produits : affiche la liste de tous les produits ;

       * Requête GET à /Produits/{id} : affiche un produit par son Id ;

       * Requête PUT à /Produits/{id} : met à jour un produit par son Id ;

       * Requête POST à /Produits : ajoute un produit ;

       * Requête DELETE à /Produits/{id} : supprime un produit par son Id.

Passons au code !
------------------------------------------------------------------------------------------------------------------------
Créez le controleur REST

Nous allons créer un contrôleur et le placer dans un Package "controller", lui-même situé dans un package "web".
Pour ce faire, faites un clic droit sur le package principal puis New -> Java Class :

*/
@SpringBootApplication
public class MicrocommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicrocommerceApplication.class, args);
	}

}
/*
Pour simplifier cette configuration, Spring Boot propose 2 fonctionnalités principales que nous allons voir dans la
 suite de ce chapitre :

    *l'auto-configuration,
    *les starters.

    //******************************************************************************************************************
    //                                           I. L'auto-configuration
    //******************************************************************************************************************

    Cette fonctionnalité est la plus importante de Spring Boot. Elle permet de configurer automatiquement votre
    application à partir des jar trouvés dans votre Classpath

    En d'autres termes, si vous avez importé des dépendances, Spring Boot ira consulter cette liste puis produira la
    configuration nécessaire pour que tout fonctionne correctement.

    Prenons l'exemple d'une application web dans laquelle vous avez les dépendances : Hibernate et Spring MVC.
    Normalement, vous devez créer les fichiers de configuration suivants :

       *appconfig-mvc.xml
       *web.xml
       *persitence.xml

   Comme vous ne connaissez pas nécessairement la syntaxe de ces fichiers par coeur, il vous faut consulter la
   documentation ou vous inspirer d'un ancien projet. Vous devez ensuite écrire le code Java qui permet de lier
   ces fichiers XML à ApplicationContext de Spring.

   Ensuite, vous adaptez et testez, puis ré-adaptez et re-testez encore pendant un bon moment avant que tout
   fonctionne... Bien sûr, dès que vous faites un changement dans votre application, il ne faut pas oublier de
   mettre à jour tous les fichiers de configuration, puis débugger de nouveau. Cela devient très vite très fastidieux !

   Voici l'équivalent de l'ensemble de ces étapes avec Spring MVC :

     @EnableAutoConfiguration

  C'est tout ! :magicien: Avec cette annotation, Spring Boot ira scanner la liste de vos dépendances, trouvant par
  exemple Hibernate. Ayant constaté que vous n'avez défini aucun autre datasource, il créera la configuration
  nécessaire et l'ajoutera à ApplicationContext.

  Bien entendu, vous pouvez très facilement personnaliser ces configurations, en créant vos Beans ou  vos propres
  fichiers de configuration. Spring Boot utilisera alors en priorité vos paramètres.


    //******************************************************************************************************************
    //                                          II. Les Starters
    //******************************************************************************************************************
    Les starters viennent compléter l'auto-configuration et font gagner énormément de temps, notamment lorsqu'on
    commence le développement d'un Microservice. Un starter va apporter à votre projet un ensemble de dépendances,
    communément utilisées pour un type de projet donné. Ceci va vous permettre de créer un "squelette" prêt à l'emploi
    très rapidement.

    L'autre énorme avantage est la gestion des versions. Plus besoin de chercher quelles versions sont compatibles
    puis de les ajouter une à une dans le pom.xml ! Il vous suffit d'ajouter une simple dépendance au starter de votre
    choix. Cette dépendance va alors ajouter, à son tour, les éléments dont elle dépend, avec les bonnes versions.

    Prenons l'exemple où vous souhaitez créer un Microservice. En temps normal, vous aurez besoin des dépendances
    suivantes :

    Spring ;

    Spring MVC ;

    Jackson (pour json) ;

    Tomcat ;

    ...

    Avec Spring Boot, vous allez tout simplement avoir une seule dépendance dans votre pom.xml :

                                 <dependency>
                                     <groupId>org.springframework.boot</groupId>
                                     <artifactId>spring-boot-starter-web</artifactId>
                                 </dependency>

   Tous les starters de Spring Boot sont au format spring-boot-starter-NOM_DU_STARTER

   Ce starter va charger les dépendances présentes dans le pom suivant :

                 <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
                    <modelVersion>4.0.0</modelVersion>
                    <parent>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starters</artifactId>
                        <version>1.5.9.RELEASE</version>
                    </parent>
                    <artifactId>spring-boot-starter-web</artifactId>
                    <name>Spring Boot Web Starter</name>
                    <description>Starter for building web, including RESTful, applications using Spring
                        MVC. Uses Tomcat as the default embedded container</description>
                    <url>http://projects.spring.io/spring-boot/</url>
                    <organization>
                        <name>Pivotal Software, Inc.</name>
                        <url>http://www.spring.io</url>
                    </organization>
                    <properties>
                        <main.basedir>${basedir}/../..</main.basedir>
                    </properties>
                    <dependencies>
                        <dependency>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter-tomcat</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>org.hibernate</groupId>
                            <artifactId>hibernate-validator</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>com.fasterxml.jackson.core</groupId>
                            <artifactId>jackson-databind</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>org.springframework</groupId>
                            <artifactId>spring-web</artifactId>
                        </dependency>
                        <dependency>
                            <groupId>org.springframework</groupId>
                            <artifactId>spring-webmvc</artifactId>
                        </dependency>
                    </dependencies>
                </project>

    Comme vous pouvez le voir, ce starter Spring Boot pour application web met en place la configuration des éléments
    suivants : tomcat, hibernate-validator, jackson, spring MVC.

    D'accord, mais cela ne m'explique pas comment il devine les versions ?

    Voici la réponse :

                           <parent>
                               <groupId>org.springframework.boot</groupId>
                               <artifactId>spring-boot-starter-parent</artifactId>
                               <version>1.5.9.RELEASE</version>
                           </parent>

 */