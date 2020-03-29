package com.ecommerce.microcommerce.model;

//----------------------------------------------------------------------------------------------------------------------
//                                        Renvoyez une réponse JSON
//----------------------------------------------------------------------------------------------------------------------
/*
Nous allons commencer par créer une classe qui représente un produit. Cette classe est souvent appelée Bean ou JavaBean.
Un Bean est une classe classique qui doit être "sérialisable" et avoir au minimum :

un constructeur public sans argument,

des getters et setters pour toutes les propriétés de la classe.

Commencez par créer une nouvelle classe Product que vous allez placer dans un package "model" sous le package microcommerce :

Très bien :) ! Maintenant à chaque fois que quelqu'un appelle notre URI "/Produits/{id}", nous voudrions renvoyer un
produit au format JSON qui correspond à notre classe Product.

Retournez sur le code précédent et remplacer la méthode afficherUnProduit par celle-ci :

                      //Récupérer un produit par son Id
                      @GetMapping(value="/Produits/{id}")
                      public Product afficherUnProduit(@PathVariableintid) {
                          Product product=newProduct(id, new String("Aspirateur"), 100 );
                          return product;
                      }
*/
public class Product {
    private int id;
    private String nom;
    private int prix;

    //constructeur par default
    public Product() {
    }

    //Constructeur pour nos tests
    public Product(int id, String nom, int prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix + '}';
    }
}
