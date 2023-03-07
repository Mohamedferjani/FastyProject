package edu.fasty.entities;

import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Score {
    
    //LES ATTRIBUTS
    int idScore;
    int idUser;
    double scoreValue;
    String scoreSentiment;
    String scoreDate;
    
    //LES CONSTRUCTEURS

    public Score() {
    }

    public Score(int idUser, double scoreValue, String scoreSentiment, String scoreDate) {
        this.idUser = idUser;
        this.scoreValue = scoreValue;
        this.scoreSentiment = scoreSentiment;
        this.scoreDate = scoreDate;
    }

    

    public Score(int idScore, int idUser, double scoreValue, String scoreSentiment, String scoreDate) {
        this.idScore = idScore;
        this.idUser = idUser;
        this.scoreValue = scoreValue;
        this.scoreSentiment = scoreSentiment;
        this.scoreDate = scoreDate;
    }
    
    //GETTERS ET SETTERS

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(double scoreValue) {
        this.scoreValue = scoreValue;
    }

    public String getScoreSentiment() {
        return scoreSentiment;
    }

    public void setScoreSentiment(String scoreSentiment) {
        this.scoreSentiment = scoreSentiment;
    }

    public String getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
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
        return "idUser=" + nom +" "+prenom + ", scoreValue=" + scoreValue + ", scoreSentiment=" + scoreSentiment + ", scoreDate=" + scoreDate ;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Score other = (Score) obj;
        if (this.idScore != other.idScore) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
}
