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
public class Question {
    private int id_question;
    private Forum forumId;
    private String contenu;
    private User iduser;
  //  private Reponse reponse_id;

    public User getIduser() {
        return iduser;
    }

//    public void setIduser(int iduser) {
//        this.iduser = iduser;
//    }
    

    public Question() {
    }

   

    public Question(Forum f ,String contenu , User iduser) {
       this.forumId = f;
        this.contenu = contenu;
        this.iduser = iduser;
    }

    public Question(int id_question,Forum f , String contenu , User iduser) {
        this.id_question = id_question;
       this.forumId =f;
        this.contenu = contenu;
        this.iduser = iduser;
    }
 

     public Forum getForumId() {
        return forumId;
    }

    
    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Question{" + "contenu=" + contenu + '}';
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
        final Question other = (Question) obj;
        if (this.id_question != other.id_question) {
            return false;
        }
        return true;
    }
    
    
}
