/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author IHEB
 */
public class Bid {
    private int id_bid  ;
    private int id_event   ;
    private int id_produit    ;
    private int starting_price    ;
    private User id_User   ;
    private int id_lastbidder   ;
    private LocalDate last_modified_time	;
    
    
    public Bid(int id_event, int id_produit, int starting_price, User id_User, int id_lastbidder) {
        this.id_event = id_event;
        this.id_produit = id_produit;
        this.starting_price = starting_price;
        this.id_User = id_User;
        this.id_lastbidder = id_lastbidder;
    }

    public int getId_bid() {
        return id_bid;
    }

    public Bid(int starting_price, User id_User) {
        this.starting_price = starting_price;
        this.id_User = id_User;
    }

    public Bid(int starting_price, User id_User, int id_lastbidder) {
        this.starting_price = starting_price;
        this.id_User = id_User;
        this.id_lastbidder = id_lastbidder;
    }

    public Bid(int starting_price, User id_User, int id_lastbidder, LocalDate last_modified_time) {
        this.starting_price = starting_price;
        this.id_User = id_User;
        this.id_lastbidder = id_lastbidder;
        this.last_modified_time = last_modified_time;
    }

    public Bid(int id_event, int id_produit, int starting_price, User id_User, LocalDate last_modified_time) {
        this.id_event = id_event;
        this.id_produit = id_produit;
        this.starting_price = starting_price;
        this.id_User = id_User;
        this.last_modified_time = last_modified_time;
    }

    public Bid(int id_event, int id_produit, int starting_price, User id_User) {
        this.id_event = id_event;
        this.id_produit = id_produit;
        this.starting_price = starting_price;
        this.id_User = id_User;
    }

    public Bid(int id_bid, int id_event, int id_produit, int starting_price, User id_User) {
        this.id_bid = id_bid;
        this.id_event = id_event;
        this.id_produit = id_produit;
        this.starting_price = starting_price;
        this.id_User = id_User;
    }

    
    public LocalDate getLast_modified_time() {
        return last_modified_time;
    }

    public void setLast_modified_time(LocalDate last_modified_time) {
        this.last_modified_time = last_modified_time;
    }
    

    

    public int getId_lastbidder() {
        return id_lastbidder;
    }

    public void setId_lastbidder(int id_lastbidder) {
        this.id_lastbidder = id_lastbidder;
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

    public User getId_User() {
        return id_User;
    }

//    public void setId_User(int id_User) {
//        this.id_User = id_User;
//    }

    public Bid() {
    }


    @Override
    public String toString() {
        return "Bid{" + "id_bid=" + id_bid + ", id_event=" + id_event + ", id_produit=" + id_produit + ", starting_price=" + starting_price + ", id_User=" + id_User + '}';
    }


}