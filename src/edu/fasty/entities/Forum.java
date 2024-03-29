/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

/**
 *
 * @author FERJANI
 */
public class Forum {
    
    private int id_forum;
    private String titre;
    private String contenu;
    private User iduser;
    
    
    public Forum() {
    }

    public Forum(String titre,String contenu , User iduser) {
        this.titre = titre;
        this.contenu = contenu;
        this.iduser = iduser;
        
    }

    public Forum(int id_forum, String titre,String contenu , User u) {
        this.id_forum = id_forum;
        this.titre = titre;
        this.contenu = contenu;
        this.iduser = u;

    }
    
public int getId_forum() {
        return id_forum;
    }

    public void setId_forum(int id_forum) {
        this.id_forum = id_forum;
    }

    public User getIduser() {
        return iduser;
    }

//    public void setIduser(int iduser) {
//        this.iduser = iduser;
//    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Forum{" + "titre=" + titre + ", contenu=" + contenu + '}';
    }
    


    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Forum other = (Forum) obj;
        if (this.id_forum != other.id_forum) {
            return false;
        }
       
        return true;
    }
    
}
