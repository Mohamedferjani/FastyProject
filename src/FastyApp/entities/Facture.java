/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.entities;

import java.util.Objects;

/**
 *
 * @author ISSAM
 */
public class Facture {
    
    private int id_facture;
    private String montant;
    private int id_prop;
    private int id_client;
    private int id_produit;

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public int getId_prop() {
        return id_prop;
    }

    public void setId_prop(int id_prop) {
        this.id_prop = id_prop;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Facture() {
    }

    public Facture(int id_facture, String montant, int id_prop, int id_client, int id_produit) {
        this.id_facture = id_facture;
        this.montant = montant;
        this.id_prop = id_prop;
        this.id_client = id_client;
        this.id_produit = id_produit;
    }

    public Facture(String montant, int id_prop, int id_client, int id_produit) {
        this.montant = montant;
        this.id_prop = id_prop;
        this.id_client = id_client;
        this.id_produit = id_produit;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id_facture;
        hash = 19 * hash + Objects.hashCode(this.montant);
        hash = 19 * hash + this.id_prop;
        hash = 19 * hash + this.id_client;
        hash = 19 * hash + this.id_produit;
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
        final Facture other = (Facture) obj;
        if (this.id_facture != other.id_facture) {
            return false;
        }
        if (this.id_prop != other.id_prop) {
            return false;
        }
        if (this.id_client != other.id_client) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (!Objects.equals(this.montant, other.montant)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Facture{" + "id_facture=" + id_facture + ", montant=" + montant + ", id_prop=" + id_prop + ", id_client=" + id_client + ", id_produit=" + id_produit + '}';
    }

   
    
}
