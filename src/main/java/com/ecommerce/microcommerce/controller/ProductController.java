package com.ecommerce.microcommerce.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Nous allons commencer par indiquer à Spring que ce contrôleur est un contrôleur REST. Saisissez le code suivant
dans la classe ProductController :

Vous connaissez sans doute l'annotation @Controller de Spring qui permet de désigner une classe comme contrôleur,
lui conférant la capacité de traiter les requêtes de type GET, POST, etc. Vous ajoutez ensuite @ResponseBody aux
méthodes qui devront répondre directement sans passer par une vue.

@RestController est simplement la combinaison des deux annotations précédentes. Une fois ajouté, il indique que
cette classe va pouvoir traiter les requêtes que nous allons définir. Il indique aussi que chaque méthode va renvoyer
directement la réponse JSON à l'utilisateur, donc pas de vue dans le circuit.

--------------------------
Méthode pour GET /Produits
-------------------------

Commençons par créer une méthode listeProduits, très simple, qui retourne un String. Comme nous n'avons pas encore
de produits, on retourne une simple phrase pour tester.

Dans ce code,  c'est l'annotation @RequestMapping  qui permet de faire le lien entre l'URI "/Produits",
invoquée via GET, et la méthode listeProduits .

Cette annotation accepte plusieurs paramètres , dont voici les principaux :

value : C'est ici que vous indiquez l'URI à laquelle cette méthode doit répondre. Vous pouvez également indiquer
des paramètres, nous y reviendrons.

method : Vous indiquez ici à quel type de requêtes cette méthode doit répondre. Dans notre cas, notre méthode
listeProduits ne sera déclenchée que si l'URI est exactement "/Produits" et que la requête est de type GET.
Si vous appelez "/Produits" avec un POST, notre méthode ne sera tout simplement pas évoquée. method accepte
toutes les requêtes CRUD et plus encore.

produces : Dans certains cas d'utilisations avancées, vous aurez besoin de préciser, par exemple, que votre méthode
est capable de répondre en XML et en JSON. Cela entre aussi dans le choix de la méthode qui correspond le mieux
à la requête. Si la requête contient du XML et que vous avez 2 méthodes identiques, dont une capable de produire
du XML, c'est celle-ci qui sera appelée. Il en va de même pour consumes qui précise les formats acceptés.
Dans la plupart des cas, vous n'avez pas besoin de renseigner ces paramètres.

Interagir avec les données
///////////////////////////
Nous allons maintenant modifier notre contrôleur afin qu'elle utilise notre couche DAO pour manipuler les produits

Explication du code

Tout d'abord, nous avons créé une variable de type ProductDao, que nous avons annotée avec @Autowired afin que Spring
se charge d'en fabriquer une instance. ProductDao a désormais accès à toutes les méthodes que nous avons définies.

Nous avons changé listeProduits afin qu'elle nous retourne une liste de produits List . Ensuite, il a suffi d'invoquer
la méthode findAll créée précédemment pour qu'elle nous retourne tous les produits.

De même, pour afficherUnProduit qui fait appel à findById .

Testez !

Lancez le Microservice et rendez-vous à http://localhost:9090/Produits/, vous obtiendrez ceci :

// 20191222172558
// http://localhost:9090/Produits/

[
  {
    "id": 1,
    "nom": "ordinateur portable",
    "prix": 350
  },
  {
    "id": 2,
    "nom": "Aspirateur Robot",
    "prix": 500
  },
  {
    "id": 3,
    "nom": "Table de Ping Pong",
    "prix": 750
  }
]

Bingo :D ! Vous avez la liste des produits que vous avez définis au format JSON prête à être consommée par n'importe quel Microservice REST.

Rendez-vous ensuite à http://localhost:9090/Produits/1 pour afficher produit par produit.

Le processus pour obtenir le résultat est le suivant :

      1. L'utilisateur envoie une requête GET vers /Produits/2.

      2. Le dispatcheur cherche dans votre contrôleur la méthode qui répond au pattern "/Produits/{id}" et l'exécute.

      3. La méthode (dans ce cas listeProduits ) fait appel au DAO pour qu'il communique avec la base de données.
         Il récupère les informations sur le produit puis il crée une instance de Product qu'il renvoie ensuite à
         votre méthode.

Votre méthode retourne l'instance reçue, qui est transformée à la volée en JSON grâce à Jackson.
*/
@RestController
public class ProductController {

    @Autowired // @Autowired  afin que Spring se charge d'en fabriquer une instance
    private ProductDao productDao;

    //Récupérer la liste des produits
    @RequestMapping(value = "/Produits", method = RequestMethod.GET)
    public List<Product> listeProduits() {
        return productDao.findAll();
    }

    //Récupérer un produit par son Id
    @GetMapping(value = "/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
        //Product product = new Product(id, new String("Aspirateur"), 100);
        return productDao.productFindById(id);
    }
    //-------------------------------------ajouter un produit avec POST-------------------------------------------------
    //ajouter un produit
    @PostMapping(value = "/Produits")
    public void ajouterProduit(@RequestBody Product product) {
        productDao.productSave(product);
    }
    //------------------------------------------------------------------------------------------------------------------
    /*
Méthode pour GET /Produits/{id}
Créons maintenant une autre méthode capable d'accepter un Id de produit en paramètre :

    @RequestMapping(value = "/Produits/{id}", method = RequestMethod.GET)
    public String afficherUnProduit(@PathVariable int id) {
        return "Vous avez demandé un produit avec l'id  " + id;
    }

    @GetMapping(value = "/Produits/{id}")
    public String afficherUnProduit(@PathVariable int id) {
        return "Vous avez demandé un produit avec l'id  " + id;
    }
    */
}
/*
La première différence dans cette méthode est l'ajout de {id} à l'URI. Cette notation permet d'indiquer que cette
méthode doit répondre uniquement aux requêtes avec une URI de type /Produits/25 par exemple. Comme nous avons indiqué
que id doit être un int (dans @PathVariable int id), vous pouvez vous amuser à passer une chaine de caractères à la
place, vous verrez que Spring vous renverra une erreur.

Il existe des raccourcis pour éviter d'écrire l'annotation à rallonge @RequestMapping. Par exemple, pour une
 @RequestMapping qui accepte des requêtes de type GET, vous pouvez la remplacer par @GetMapping. Vous obtenez un
 code comme ceci :
                              @GetMapping(value = "/Produits/{id}")
                              public String afficherUnProduit(@PathVariable int id) {
                                  return "Vous avez demandé un produit avec l'id  " + id;
                              }

Le code devient plus élégant et lisible quand vous avez une dizaine de méthodes par exemple. Vous avez aussi,
les mêmes équivalents pour les autres types : PostMapping, DeleteMapping, etc.

Tout cela est très bien, mais nous aimerions bien renvoyer une liste de vrais produits au format JSON et plus
seulement des phrases inutiles. C'est ce que nous allons voir dans la prochaine section.


Lancez de nouveau le Microservice puis rendez-vous par exemple sur http://localhost:9090/Produits/27 pour
 regarder ce qui se passe.

 Vous obtenez une belle réponse formatée en JSON comme par magie :magicien:.

Comment est-ce possible ?
////////////////////////

Vous avez indiqué au début que cette classe est un contrôleur REST grâce à l'annotation @RestController.
Spring sait alors que les réponses aux requêtes qu'il vous passe devront être très probablement en format JSON.

L'auto-configurateur va alors chercher si vous avez dans votre classpath une dépendance capable de transformer
un object Java en JSON, et inversement. Bingo ! Il y a justement Jackson qui a été importé avec le starter que
nous avons utilisé. Le Bean Product que nous renvoyons est donc transformé en JSON puis servi en réponse.

Vous venez donc de créer un premier Microservice REST sans avoir à manipuler JSON ni à parser les requêtes HTTP.

Si la réponse JSON n'est pas aussi présentable dans votre navigateur que celle de ma capture d'écran, je vous
conseille d'installer une extension comme JSON Viewer pour chrome, afin de rendre tout cela plus lisible ;).
*/
