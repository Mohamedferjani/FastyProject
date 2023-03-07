/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

import java.time.LocalDateTime;

/**
 *
 * @author IHEB
 */
public class Evenement {
    private int id_evenement ;
    private LocalDateTime date;
    private String titre ;
    private int id_user  ;

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Evenement() {
    }

    public Evenement(LocalDateTime date, String titre, int id_user) {
        this.date = date;
        this.titre = titre;
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", date=" + date + ", titre=" + titre + ", id_user=" + id_user + '}';
    }
  
}
