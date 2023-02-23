/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

import java.util.Objects;

/**
 *
 * @author MSI GAMING
 */
public class Whishlist {
    private int id_wishlist;
    private int id_produit;
    private int id_user;

    public Whishlist(int id_wishlist, int id_produit, int id_user) {
        this.id_wishlist = id_wishlist;
        this.id_produit = id_produit;
        this.id_user = id_user;
    }

    public Whishlist(int id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public Whishlist() {
    }

    public Whishlist(int id_wishlist, int id_produit) {
        this.id_wishlist = id_wishlist;
        this.id_produit = id_produit;
    }

    public int getId_wishlist() {
        return id_wishlist;
    }

    public void setId_wishlist(int id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id_wishlist;
        hash = 47 * hash + this.id_produit;
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
        final Whishlist other = (Whishlist) obj;
        if (this.id_wishlist != other.id_wishlist) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "id_wishlist=" + id_wishlist + ", id_produit=" + id_produit + '}';
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
  
}
