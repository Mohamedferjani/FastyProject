package edu.geekerz.services;

import edu.geekerz.entities.Rating;
import edu.geekerz.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceRating implements InterfaceServices<Rating> {
    //ETABLIR LA CONNECTION
    Connection conn=DataSource.getInstance().getConn();
    
    //OVVERRIDE DES METHODES
    @Override
    public void ajouter(Rating r) {
        
        try {
            String req = "INSERT INTO `rating`(`id_rating`, `rating_value`, `id_user`, `rating-date`, `comment`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, r.getIdRating());
            ps.setDouble(2, r.getRatingValue());
            ps.setInt(3, r.getIdUser());
            ps.setString(4,r.getRatingdate());
            ps.setString(5, r.getComment());
            ps.executeUpdate();
            System.out.println("Rating added  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(int id) {
        
        try {
            String req = "DELETE FROM `rating` WHERE id_rating = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Rating deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifier(int id,Rating r) {
        
        try {
            String req = "UPDATE `rating` SET `rating_value`='"+r.getRatingValue()+"',`id_user`='"+r.getIdUser()+"',`rating-date`='"+r.getRatingdate()+"',`comment`='"+r.getComment()+"' WHERE id_rating ="+id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Rating updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Rating> getAll() {
        
        List<Rating> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `rating`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Rating r = new Rating(rs.getInt(1), rs.getInt(3), rs.getDouble(2),rs.getString(4),rs.getString(5));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
        

    @Override
    public Rating getOneByID(int id) {
        
        Rating r = null;
        try {
            String req = "SELECT * FROM `rating`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt(1)==id){
                 r = new Rating(rs.getInt(1), rs.getInt(3), rs.getDouble(2),rs.getString(4),rs.getString(5));}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return r;
        
    }
    
}
