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
public class Reponse {
    private int id_reponse;
    private int questionId;
     private int forumId;
    private String contenu;
    

    public Reponse() {
    }

    public Reponse(int forumId,String contenu,int questionId) {
        this.forumId = forumId;
        this.contenu = contenu;
        this.questionId = questionId;
        
    }

    public Reponse(int id_reponse, int forumId,String contenu,int questionId) {
        this.id_reponse = id_reponse;
       this.forumId = forumId;
        this.contenu = contenu;
        this.questionId = questionId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Reponse{" + "contenu=" + contenu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Reponse other = (Reponse) obj;
        if (this.id_reponse != other.id_reponse) {
            return false;
        }
        return true;
    }
    
}
