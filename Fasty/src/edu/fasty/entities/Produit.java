/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

/**
 *
 * @author Hp
 */
public class Produit {
private int id_produit ,id_user, id_categorie ;
private String nom_produit, valeur, img_produit , description;

public Produit(){

}

    public Produit(int id_user, int id_categorie, String nom_produit, String valeur, String img_produit, String description) {
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.nom_produit = nom_produit;
        this.valeur = valeur;
        this.img_produit = img_produit;
        this.description = description;
    }

    public Produit(String nom_produit, String valeur, String img_produit, String description) {
        this.nom_produit = nom_produit;
        this.valeur = valeur;
        this.img_produit = img_produit;
        this.description = description;
    }

    public Produit(int id_produit, int id_user, int id_categorie, String nom_produit, String valeur, String img_produit, String description) {
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.nom_produit = nom_produit;
        this.valeur = valeur;
        this.img_produit = img_produit;
        this.description = description;
    }

   
    public int getId_produit() {
        return id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public String getValeur() {
        return valeur;
    }

    public String getImg_produit() {
        return img_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public void setImg_produit(String img_produit) {
        this.img_produit = img_produit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "nom_produit=" + nom_produit + ", valeur=" + valeur + ", img_produit=" + img_produit + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id_produit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        return true;
    }

    public String getDescprition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }







}

