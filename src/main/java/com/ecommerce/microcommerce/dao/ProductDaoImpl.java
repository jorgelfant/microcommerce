package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
Maintenant que notre interface est prête, nous allons pouvoir créer son implémentation. Créez une classe
ProductDaoImpl qui implémente l'interface que nous venons de créer. Générez ensuite automatiquement cette
implémentation en appuyant sur Cmd +N sur Mac ou Ctrl +N sur Windows, puis Implement Methods . Vous obtenez alors ceci  :

Normalement , cette classe est censée communiquer avec la base de données pour récupérer les produits ou en ajouter.
Nous allons simuler ce comportement en créant des Produits en dur dans le code.

Explication du code

@Repository : cette annotation est appliquée à la classe afin d'indiquer à Spring qu'il s'agit d'une classe qui
gère les données, ce qui nous permettra de profiter de certaines fonctionnalités comme les translations des erreurs.
Nous y reviendrons.

Pour tester avec des données statiques , vu que nous n'en sommes pas encore à la base de données, nous définissons
ici un tableau de Product  dans lequel nous ajoutons 3 produits statiques. Les méthodes suivantes sont ensuite
redéfinies afin de renvoyer les données adéquates :

   1. findAll  : renvoie tous les produits que nous avons créés ;

   2. findById : vérifie s'il y a un produit avec l'id donnée dans notre liste de produits et le renvoie en cas de
      correspondance ;

save : ajoute le produit reçu à notre liste.

Très bien, votre couche DAO est prête ! Vous allez pouvoir l'utiliser pour simuler la récupération et l'ajout de
produits depuis une base de données.
*/

@Repository
public class ProductDaoImpl implements ProductDao {
    public static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "ordinateur portable", 350));
        products.add(new Product(2, "Aspirateur Robot", 500));
        products.add(new Product(3, "Table de Ping Pong", 750));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product productFindById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product productSave(Product product) {
        products.add(product);
        return product;
    }
    /*
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public String productFindById(int id) {
        return null;
    }

    @Override
    public String productSave(Product product) {
        return null;
    }
    */

}
