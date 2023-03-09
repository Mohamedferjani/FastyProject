/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

import java.util.ArrayList;

/**
 *
 * @author skand
 */
public class Wishlist {
    
    //LES ATTRIBUTS
    int id_wishlist;
    int id_user;
    ArrayList<Integer> list_Produit = new ArrayList<>();

    
    //LES CONSTRUCTEURS

    public Wishlist() {
    }

    
    public Wishlist(int id_user, ArrayList<Integer> list_Produit) {
    this.id_user = id_user;
    this.list_Produit = list_Produit;
}


    public Wishlist(int id_wishlist, int id_user, ArrayList<Integer> list_Produit) {
        this.id_wishlist = id_wishlist;
        this.id_user = id_user;
        this.list_Produit = list_Produit;
    }
    
    //Getters and Setters

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

    public ArrayList<Integer> getList_Produit() {
        return list_Produit;
    }

    public void setList_Produit(ArrayList<Integer> list_Produit) {
        this.list_Produit = list_Produit;
    }
    
    //TO STRING

    @Override
    public String toString() {
        return "Wishlist{"  + ", id_user=" + id_user + ", list_Produit=" + list_Produit + '}';
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
