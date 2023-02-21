package edu.geekerz.entities;

import java.sql.Date;


public class Rating {
    
    //LES ATTRIBUTS
    int idRating;
    int idUser;
    double ratingValue;
    String ratingdate;
    String comment;
    
    //LES CONSTRUCTEURS

    public Rating() {
    }

    public Rating(int idUser, double ratingValue, String ratingdate, String comment) {
        this.idUser = idUser;
        this.ratingValue = ratingValue;
        this.ratingdate = ratingdate;
        this.comment = comment;
    }

    public Rating(int idRating, int idUser, double ratingValue, String ratingdate, String comment) {
        this.idRating = idRating;
        this.idUser = idUser;
        this.ratingValue = ratingValue;
        this.ratingdate = ratingdate;
        this.comment = comment;
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
    
    //TO STRING , HACHCODE AND EQUALS

    @Override
    public String toString() {
        return "Rating{" + "ratingValue=" + ratingValue + ", ratingdate=" + ratingdate + ", comment=" + comment + '}';
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
