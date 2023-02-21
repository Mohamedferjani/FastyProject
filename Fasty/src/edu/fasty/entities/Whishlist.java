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
    private String list_produit;

    public Whishlist(String list_produit) {
        this.list_produit = list_produit;
    }

    public Whishlist(int id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public Whishlist(int id_wishlist, String list_produit) {
        this.id_wishlist = id_wishlist;
        this.list_produit = list_produit;
    }

    @Override
    public String toString() {
        return "Whishlist{" + "id_wishlist=" + id_wishlist + ", list_produit=" + list_produit + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_wishlist;
        hash = 97 * hash + Objects.hashCode(this.list_produit);
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
        if (!Objects.equals(this.list_produit, other.list_produit)) {
            return false;
        }
        return true;
    }

    public int getId_wishlist() {
        return id_wishlist;
    }

    public void setId_wishlist(int id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public String getList_produit() {
        return list_produit;
    }

    public void setList_produit(String list_produit) {
        this.list_produit = list_produit;
    }

 
    
}
