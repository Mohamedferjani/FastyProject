package edu.fasty.services;

import edu.fasty.entities.CategoryRating;
import edu.fasty.entities.Rating;
import edu.fasty.utils.DataSource;
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
    ServiceCategoryRating scr=new ServiceCategoryRating();
    
    //OVVERRIDE DES METHODES
    @Override
    public void ajouter(Rating r) {
        
        try {
            String req = "INSERT INTO `rating`( `rating_value`, `id_user`, `rating-date`, `comment`, `id_Cat_Rating`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setDouble(1, r.getRatingValue());
            ps.setInt(2, r.getIdUser());
            ps.setString(3,r.getRatingdate());
            ps.setString(4, r.getComment());
            ps.setInt(5,r.getCategory().getId_Cat_Rating());
            ps.executeUpdate();
            System.out.println("Rating added  !");
            
            /*
            //ajouter est affecter l'id:
            String idreq="SELECT LAST_INSERT_ID();";
                Statement st2 = conn.createStatement();
                ResultSet rs =st2.executeQuery(idreq);
                while (rs.next()){
                    int getid=rs.getInt(1);
                    r.setIdRating(getid);
                }
                if (r.getIdRating()!= 0){
                    System.out.println("Id affected!");
                }*/
            
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
            String req = "UPDATE `rating` SET `rating_value`='"+r.getRatingValue()+"',`id_user`='"+r.getIdUser()+"',`rating-date`='"+r.getRatingdate()+"',`comment`='"+r.getComment()+"',`id_Cat_Rating`='"+r.getCategory().getId_Cat_Rating()+"' WHERE id_rating ="+id;
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
                CategoryRating category = scr.getOneByID(rs.getInt(6));
                Rating r = new Rating(rs.getInt(1), rs.getInt(3), rs.getDouble(2),rs.getString(4),rs.getString(5),category);
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
                 CategoryRating category = scr.getOneByID(rs.getInt(6));
                 r = new Rating(rs.getInt(1), rs.getInt(3), rs.getDouble(2),rs.getString(4),rs.getString(5),category);}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return r;
        
    }
    
}
