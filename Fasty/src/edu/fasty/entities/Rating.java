package edu.fasty.entities;

import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Rating {
    
    //LES ATTRIBUTS
    int idRating;
    int idUser;
    double ratingValue;
    String ratingdate;
    String comment;
    CategoryRating category;
    
    //LES CONSTRUCTEURS

    public Rating() {
    }

    public Rating(int idUser, double ratingValue, String ratingdate, String comment, CategoryRating category) {
        this.idUser = idUser;
        this.ratingValue = ratingValue;
        this.ratingdate = ratingdate;
        this.comment = comment;
        this.category = category;
    }

    public Rating(int idRating, int idUser, double ratingValue, String ratingdate, String comment, CategoryRating category) {
        this.idRating = idRating;
        this.idUser = idUser;
        this.ratingValue = ratingValue;
        this.ratingdate = ratingdate;
        this.comment = comment;
        this.category = category;
    }
    
    //GETTERS AT SETTERS

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getRatingdate() {
        return ratingdate;
    }

    public void setRatingdate(String ratingdate) {
        this.ratingdate = ratingdate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CategoryRating getCategory() {
        return category;
    }

    
    public void setCategory(CategoryRating category) {
        this.category = category;
    }

    //TO STRING , HACHCODE AND EQUALS

    @Override
    public String toString() {
        String nom="null" ,prenom="null";
        Connection conn=DataSource.getInstance().getConn();
        try{
        String req = "SELECT  `nom`, `prenom` FROM `user` WHERE `id_user` = "+idUser;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        nom=rs.getString(1);
        prenom=rs.getString(2);
        }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "User name=" + nom +" "+ prenom + ", ratingValue=" + ratingValue + ", ratingdate=" + ratingdate + ", comment=" + comment + ", category=" + category.getName_Cat_Rating() ;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Rating other = (Rating) obj;
        if (this.idRating != other.idRating) {
            return false;
        }
        return true;
    }
    
    
    
}
