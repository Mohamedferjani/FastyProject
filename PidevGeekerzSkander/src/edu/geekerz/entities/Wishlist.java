/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.geekerz.entities;

/**
 *
 * @author skand
 */
public class Wishlist {
    
    //LES ATTRIBUTS
    int id_wishlist;
    int id_user;
    String name_wishlist;
    String list_produit;
    String creation_date;
    
    //LES CONSTRUCTEURS

    public Wishlist() {
    }

    public Wishlist(int id_wishlist, int id_user, String name_wishlist, String list_produit, String creation_date) {
        this.id_wishlist = id_wishlist;
        this.id_user = id_user;
        this.name_wishlist = name_wishlist;
        this.list_produit = list_produit;
        this.creation_date = creation_date;
    }

    public Wishlist(int id_user, String name_wishlist, String list_produit, String creation_date) {
        this.id_user = id_user;
        this.name_wishlist = name_wishlist;
        this.list_produit = list_produit;
        this.creation_date = creation_date;
    }

    public Wishlist(String name_wishlist, String list_produit, String creation_date) {
        this.name_wishlist = name_wishlist;
        this.list_produit = list_produit;
        this.creation_date = creation_date;
    }
    
    
    //GETTERS ET SETTERS

    public int getId_wishlist() {
        return id_wishlist;
    }

    public void setId_wishlist(int id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_wishlist() {
        return name_wishlist;
    }

    public void setName_wishlist(String name_wishlist) {
        this.name_wishlist = name_wishlist;
    }

    public String getList_produit() {
        return list_produit;
    }

    public void setList_produit(String list_produit) {
        this.list_produit = list_produit;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
    
    //TO STRING , HACHCODE AND EQUALS

    @Override
    public String toString() {
        return "Wishlist{" + "id_user=" + id_user + ", name_wishlist=" + name_wishlist + ", list_produit=" + list_produit + ", creation_date=" + creation_date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Wishlist other = (Wishlist) obj;
        if (this.id_wishlist != other.id_wishlist) {
            return false;
        }
        return true;
    }
    
    
    
    
}
