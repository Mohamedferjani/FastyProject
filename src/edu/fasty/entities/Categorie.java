/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fasty.entities;

/**
 *
 * @author Homrani
 */
public class Categorie {
    private int id_categorie ;
    private String nom_categorie ;
    private String type_categorie ;
    private User id_user ;

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getType_categorie() {
        return type_categorie;
    }

    public void setType_categorie(String type_categorie) {
        this.type_categorie = type_categorie;
    }

    public User getId_user() {
        return id_user;
    }

//    public void setId_user(int id_user) {
//        this.id_user = id_user;
//    }

    public Categorie() {
    }

    public Categorie(String nom_categorie, String type_categorie, User id_user) {
        this.nom_categorie = nom_categorie;
        this.type_categorie = type_categorie;
        this.id_user = id_user;
    }
    public Categorie(int id_categorie,String nom_categorie, String type_categorie, User id_user) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.type_categorie = type_categorie;
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", nom_categorie=" + nom_categorie + ", type_categorie=" + type_categorie + ", id_user=" + id_user + '}';
    }
    
    
}
