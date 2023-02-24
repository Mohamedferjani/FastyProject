package edu.geekerz.services;


import edu.geekerz.entities.Score;
import edu.geekerz.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceScore implements InterfaceServices<Score> {
    Connection conn=DataSource.getInstance().getConn();

    @Override
    public void ajouter(Score s) {
        
        try {
            String req = "INSERT INTO `score`(`id_score`, `score_value`, `id_user`, `score_sentiment`, `score_date`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, s.getIdScore());
            ps.setDouble(2, s.getScoreValue());
            ps.setInt(3, s.getIdUser());
            ps.setString(4,s.getScoreSentiment());
            ps.setString(5, s.getScoreDate());
            ps.executeUpdate();
            System.out.println("score added  !");
            
            /*
              //ajouter est affecter l'id:
            String idreq="SELECT LAST_INSERT_ID();";
                Statement st2 = conn.createStatement();
                ResultSet rs =st2.executeQuery(idreq);
                while (rs.next()){
                    int getid=rs.getInt(1);
                    s.setIdScore(getid);
                }
                if (s.getIdScore()!= 0){
                    System.out.println("Id affected!");
                }*/
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(int id) {
        
        try {
            String req = "DELETE FROM `score` WHERE id_score = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Score deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifier(int id,Score s) {
        
        try {
            String req = "UPDATE `score` SET `score_value`='"+s.getScoreValue()+"',`id_user`='"+s.getIdUser()+"',`score_sentiment`='"+s.getScoreSentiment()+"',`score_date`='"+s.getScoreDate()+"' WHERE id_score ="+ id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Score updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Score> getAll() {
        
        List<Score> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `score`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Score s = new Score(rs.getInt(1), rs.getInt(3), rs.getDouble(2),rs.getString(4),rs.getString(5));
                list.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

    @Override
    public Score getOneByID(int id) {
        
        Score s = null;
        try {
            String req = "SELECT * FROM `score`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt(1)==id){
                 s = new Score(rs.getInt(1), rs.getInt(3), rs.getDouble(2),rs.getString(4),rs.getString(5));}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return s;
        
    }
    
}
