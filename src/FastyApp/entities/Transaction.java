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
public class Transaction {
    private int id_transaction;
    private int id_recepteur;
    private int id_expiditeur;
    private int id_produit;
    private String NomTransaction;

    public String getNomTransaction() {
        return NomTransaction;
    }

    public void setNomTransaction(String NomTransaction) {
        this.NomTransaction = NomTransaction;
    }

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public int getId_recepteur() {
        return id_recepteur;
    }

    public void setId_recepteur(int id_recepteur) {
        this.id_recepteur = id_recepteur;
    }

    public int getId_expiditeur() {
        return id_expiditeur;
    }

    public void setId_expiditeur(int id_expiditeur) {
        this.id_expiditeur = id_expiditeur;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public Transaction() {
    }

    public Transaction(int id_transaction, int id_recepteur, int id_expiditeur, int id_produit, String NomTransaction) {
        this.id_transaction = id_transaction;
        this.id_recepteur = id_recepteur;
        this.id_expiditeur = id_expiditeur;
        this.id_produit = id_produit;
        this.NomTransaction = NomTransaction;
    }
     public Transaction( int id_recepteur, int id_expiditeur, int id_produit, String NomTransaction) {
        this.id_recepteur = id_recepteur;
        this.id_expiditeur = id_expiditeur;
        this.id_produit = id_produit;
        this.NomTransaction = NomTransaction;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id_transaction;
        hash = 71 * hash + this.id_recepteur;
        hash = 71 * hash + this.id_expiditeur;
        hash = 71 * hash + this.id_produit;
        hash = 71 * hash + Objects.hashCode(this.NomTransaction);
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
        final Transaction other = (Transaction) obj;
        if (this.id_transaction != other.id_transaction) {
            return false;
        }
        if (this.id_recepteur != other.id_recepteur) {
            return false;
        }
        if (this.id_expiditeur != other.id_expiditeur) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (!Objects.equals(this.NomTransaction, other.NomTransaction)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id_transaction=" + id_transaction + ", id_recepteur=" + id_recepteur + ", id_expiditeur=" + id_expiditeur + ", id_produit=" + id_produit + ", NomTransaction=" + NomTransaction + '}';
    }
    

    
}
    