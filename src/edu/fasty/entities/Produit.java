/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fasty.entities;

/**
 *
 * @author Homrani
 */
public class Produit {
    private int id_produit ;
    private String nom_produit ;
    private String description ;
    private String img_produit ;
    private String valeur ;
    private User id_user ;
    private int id_categorie  ;

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_produit() {
        return img_produit;
    }

    public void setImg_produit(String img_produit) {
        this.img_produit = img_produit;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public User getId_user() {
        return id_user;
    }

//    public void setId_user(int id_user) {
//        this.id_user = id_user;
//    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public Produit() {
    }

     public Produit(int id_produit,String nom_produit, String description, String img_produit, String valeur, User id_user, int id_categorie) {
         this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.description = description;
        this.img_produit = img_produit;
        this.valeur = valeur;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
    }
    public Produit(String nom_produit, String description, String img_produit, String valeur, User id_user, int id_categorie) {
        this.nom_produit = nom_produit;
        this.description = description;
        this.img_produit = img_produit;
        this.valeur = valeur;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", description=" + description + ", img_produit=" + img_produit + ", valeur=" + valeur + ", id_user=" + id_user + ", id_categorie=" + id_categorie + '}';
    }
    
    
}
