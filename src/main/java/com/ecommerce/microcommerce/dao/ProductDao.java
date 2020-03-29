package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
//                                            Créez le DAO
//----------------------------------------------------------------------------------------------------------------------
/*
Nous allons nous rapprocher un peu plus d'un cas d'utilisation réel en créant la DAO nécessaire pour communiquer
avec une base de données. Nous allons simuler celle-ci grâce à des données statiques.

Créez un package et nommez-le dao puis créez dedans une interface nommée ProductDao, dans laquelle vous allez
déclarer les opérations que nous allons implémenter.

Nous indiquons dans cette interface que les opérations suivantes sont possibles :

                           findAll : renvoie la liste complète de tous les produits ;

                           findById : renvoie un produit par son Id ;

                           save : ajoute un produit.

Les noms des méthodes ne sont pas choisis au hasard. En effet, il faut suivre les conventions citées ici
par exemple afin de bénéficier plus tard de certaines fonctionnalités qui vous feront gagner beaucoup de temps.

Maintenant que notre interface est prête, nous allons pouvoir créer son implémentation. Créez une classe
ProductDaoImpl qui implémente l'interface que nous venons de créer. Générez ensuite automatiquement cette
implémentation en appuyant sur Cmd +N sur Mac ou Ctrl +N sur Windows, puis Implement Methods . Vous obtenez alors ceci  :
*/
public interface ProductDao {
    public List<Product> findAll();

    public Product productFindById(int id);

    public Product productSave(Product product);
}
