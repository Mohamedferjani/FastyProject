/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

/**
 *
 * @author IHEB
 */
public class Bid {
    private int id_bid;
    private int id_event;
    private int id_produit;
    private int starting_price;
    private int id_last_bidder;

    public Bid() {
    }

    public Bid(int id_event, int id_produit, int starting_price, int id_last_bidder) {
        this.id_event = id_event;
        this.id_produit = id_produit;
        this.starting_price = starting_price;
        this.id_last_bidder = id_last_bidder;
    }

    public Bid(int id_bid, int id_event, int id_produit, int starting_price, int id_last_bidder) {
        this.id_bid = id_bid;
        this.id_event = id_event;
        this.id_produit = id_produit;
        this.starting_price = starting_price;
        this.id_last_bidder = id_last_bidder;
    }

    public int getId_bid() {
        return id_bid;
    }

    public void setId_bid(int id_bid) {
        this.id_bid = id_bid;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getStarting_price() {
        return starting_price;
    }

    public void setStarting_price(int starting_price) {
        this.starting_price = starting_price;
    }

    public int getId_last_bidder() {
        return id_last_bidder;
    }

    public void setId_last_bidder(int id_last_bidder) {
        this.id_last_bidder = id_last_bidder;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id_bid;
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
        final Bid other = (Bid) obj;
        if (this.id_bid != other.id_bid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bid{" + "id_event=" + id_event + ", id_produit=" + id_produit + ", starting_price=" + starting_price + ", id_last_bidder=" + id_last_bidder + '}';
    }
    
}
