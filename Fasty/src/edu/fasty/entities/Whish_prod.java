/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

/**
 *
 * @author MSI GAMING
 */
public class Whish_prod {
    int id_whishlist,id_produit,id_user;

    public Whish_prod(int id_whishlist, int id_produit, int id_user) {
        this.id_whishlist = id_whishlist;
        this.id_produit = id_produit;
        this.id_user = id_user;
    }

    public Whish_prod() {
    }

    public Whish_prod(int id_produit, int id_user) {
        this.id_produit = id_produit;
        this.id_user = id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getId_user() {
        return id_user;
    }
    public int getId_whishlist() {
        return id_whishlist;
    }
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_whishlist(int id_whishlist) {
        this.id_whishlist = id_whishlist;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_whishlist;
        hash = 79 * hash + this.id_produit;
        hash = 79 * hash + this.id_user;
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
        final Whish_prod other = (Whish_prod) obj;
        if (this.id_whishlist != other.id_whishlist) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Whish_prod{" + "id_whishlist=" + id_whishlist + ", id_produit=" + id_produit + ", id_user=" + id_user + '}';
    }


    
}

