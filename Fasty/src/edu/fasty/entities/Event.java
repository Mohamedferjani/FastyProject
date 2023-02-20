/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;
import java.time.LocalDate;
/**
 *
 * @author IHEB
 */
public class Event {
    private int id_evenement;
    private  LocalDate date;
    private boolean isAuction;
    private int id_user;
    private String titre;
    private String description;

    public Event(int id_evenement, LocalDate date, boolean isAuction, int id_user, String titre, String description) {
        this.id_evenement = id_evenement;
        this.date = date;
        this.isAuction = isAuction;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event(LocalDate date, boolean isAuction, int id_user, String titre, String description) {
        this.date = date;
        this.isAuction = isAuction;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
    }



    public Event() {
    }



    public Event(LocalDate date, int id_user, String titre, boolean isAuction) {
        this.date = date;
        this.id_user = id_user;
        this.titre = titre;
        this.isAuction = isAuction;
    }





    public int getId_evenement() {
        return id_evenement;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getId_user() {
        return id_user;
    }

    public String getTitre() {
        return titre;
    }

    public boolean isIsAuction() {
        return isAuction;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setIsAuction(boolean isAuction) {
        this.isAuction = isAuction;
    }

    @Override
    public String toString() {
        return "Event{" + "date=" + date + ", id_user=" + id_user + ", titre=" + titre + ", isAuction=" + isAuction + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id_evenement;
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
        final Event other = (Event) obj;
        if (this.id_evenement != other.id_evenement) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
