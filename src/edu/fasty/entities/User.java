/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

import java.util.Objects;
import javafx.scene.image.Image;

/**
 *
 * @author MSI GAMING
 */
public class User {
    private int id_user,cin,num_tel;
    private String nom, prenom,adresse,email,password,token;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
//    private blob photo;
private int id_role ;
    public User() {
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public User(int id_user, int cin, int num_tel, String nom, String prenom, String adresse, String email, String password, int id_role,String image) {
        this.id_user = id_user;
        this.cin = cin;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.id_role = id_role;
        this.image = image;
    }

    public User(int cin, int num_tel, String nom, String prenom, String adresse, String email, String password , int id_role,String image) {
        this.cin = cin;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.id_role = id_role;
        this.image = image;    }

//    public User(int cin, int num_tel, String nom, String prenom, String adresse, String email, String password) {
//        
//        this.cin = cin;
//        this.num_tel = num_tel;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.adresse = adresse;
//        this.email = email;
//        this.password = password;
//    }

    public User(int id_user, int cin, int num_tel, String nom, String prenom, String adresse, String email, String password) {
        this.id_user = id_user;
        this.cin = cin;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id_user;
        hash = 23 * hash + this.cin;
        hash = 23 * hash + this.num_tel;
        hash = 23 * hash + Objects.hashCode(this.nom);
        hash = 23 * hash + Objects.hashCode(this.prenom);
        hash = 23 * hash + Objects.hashCode(this.adresse);
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + Objects.hashCode(this.password);
        hash = 23 * hash + Objects.hashCode(this.token);
        hash = 23 * hash + this.id_role;
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
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.cin != other.cin) {
            return false;
        }
        if (this.num_tel != other.num_tel) {
            return false;
        }
        if (this.id_role != other.id_role) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", cin=" + cin + ", num_tel=" + num_tel + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email + ", password=" + password + ", token=" + token + ", id_role=" + id_role + '}';
    }


   
    
    
}
