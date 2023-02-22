/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

import java.util.Objects;

/**
 *
 * @author Hp
 */
public class categorie {
    private int id_categorie , id_user;
    private String nom_categorie , type_categorie;
    
public categorie(){

}    

    public categorie(String nom_categorie, String type_categorie) {
        this.nom_categorie = nom_categorie;
        this.type_categorie = type_categorie;
    }

    public categorie(int id_categorie, int id_user, String nom_categorie, String type_categorie) {
        this.id_categorie = id_categorie;
        this.id_user = id_user;
        this.nom_categorie = nom_categorie;
        this.type_categorie = type_categorie;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public String getType_categorie() {
        return type_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public void setType_categorie(String type_categorie) {
        this.type_categorie = type_categorie;
    }

    @Override
    public String toString() {
        return "categorie{" + "id_categorie=" + id_categorie + ", id_user=" + id_user + ", nom_categorie=" + nom_categorie + ", type_categorie=" + type_categorie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_categorie;
        hash = 97 * hash + this.id_user;
        hash = 97 * hash + Objects.hashCode(this.nom_categorie);
        hash = 97 * hash + Objects.hashCode(this.type_categorie);
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
        final categorie other = (categorie) obj;
        if (this.id_categorie != other.id_categorie) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.nom_categorie, other.nom_categorie)) {
            return false;
        }
        if (!Objects.equals(this.type_categorie, other.type_categorie)) {
            return false;
        }
        return true;
    }





}



