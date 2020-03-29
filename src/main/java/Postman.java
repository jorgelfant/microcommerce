public class Postman {
    //------------------------------------------------------------------------------------------------------------------
    //                                      Testez votre API grâce à Postman
    //------------------------------------------------------------------------------------------------------------------
  /*
     Comme vous avez dû le remarquer, j'ai jusqu'ici soigneusement évité d'implémenter la requête POST. En effet,
     lorsque nous créons une méthode utilisant ce verbe HTTP, il n'est pas possible de la tester simplement via le
     navigateur comme pour une simple requête GET. Nous allons utiliser un logiciel appelé Postman qui permet d'envoyer
     toutes sortes de requêtes et les personnaliser très finement. Il permet également de gérer l'authentification, les
     scripts de tests, etc.

    //------------------------------------------------------------------------------------------------------------------
    //                                      Implémentez POST
    //------------------------------------------------------------------------------------------------------------------

    Commençons par créer la méthode qui permet d'ajouter un produit :

                      //ajouter un produit
                        @PostMapping(value = "/Produits")
                        public void ajouterProduit(@RequestBody Product product) {
                             productDao.save(product);
                        }

   @PostMapping : vous pouvez voir qu'ici l'URI indiquée (/Produits) est exactement la même que dans la méthode
   listeProduits définie au chapitre précédent. Alors, comment Spring sait-il laquelle appeler ? Et bien grâce
   justement aux annotations @PostMapping et @GetMapping ! Celles-ci indiquent à quel type de requête HTTP  la
   méthode est associée, POST  ou GET  respectivement. Si on envoie une requête POST "/Produits" c'est la méthode
   annotée avec @PostMapping qui sera donc appelée.

   @RequestBody : Nous avons vu plus tôt comment Spring Boot a configuré Jackson pour convertir les objets Java
   renvoyés en réponse (Product dans notre cas) en JSON. Ici, nous avons besoin du contraire. Nous allons recevoir
   une requête POST avec les informations d'un nouveau produit au format JSON. Il nous faut donc constituer un
   objet Product à partir de ce JSON.

   C'est là que @RequestBody vient à la rescousse. Cette annotation demande à Spring que le JSON contenu dans la
   partie body de la requête HTTP soit converti en objet Java. Comment ?  Spring, qui a déjà tout auto-configuré
   au début, ira simplement chercher la librairie capable de faire cela et l'utiliser. Dans notre cas c'est Jackson,
   mais cela pourrait tout à fait être Gson.

   La requête JSON est ainsi convertie, dans notre cas, en objet Product puis passée en paramètre à ajouterProduit.
   Il ne nous reste plus qu'à appeler la méthode save que nous avons déjà créée et le nouveau produit est ajouté.

   Testons tout cela, voulez-vous ?

    //------------------------------------------------------------------------------------------------------------------
    //                                    Comprendre Postman
    //------------------------------------------------------------------------------------------------------------------

    Postman est un logiciel qui se focalise sur les tests des API. Il est devenu très populaire pour tester les
    Microservices, notamment grâce à sa simplicité et ses fonctionnalités très spécialisées.

    Postman existe à ce jour en extension pour Chrome, mais l'éditeur a annoncé la fin prochaine de celle-ci.
    Je vous recommande donc d'installer le logiciel. L'application est régulièrement mise à jour et l'interface peut
    donc être légèrement différente de celle présentée dans la suite de ce chapitre.

  */
}
