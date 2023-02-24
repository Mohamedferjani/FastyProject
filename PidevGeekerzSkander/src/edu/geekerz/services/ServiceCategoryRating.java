package edu.geekerz.services;

import edu.geekerz.entities.CategoryRating;
import edu.geekerz.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceCategoryRating implements InterfaceServices<CategoryRating>  {
    
    //ETABLIR LA CONNECTION
    Connection conn=DataSource.getInstance().getConn();

    @Override
    public void ajouter(CategoryRating cr) {
        try {
            String req = "INSERT INTO `categoryrating`(`name_Cat_Rating`, `description_Cat_Rating`, `logo`) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, cr.getName_Cat_Rating());
            ps.setString(2, cr.getDescription_Cat_Rating());
            ps.setString(3,cr.getLogo());
            ps.executeUpdate();
            System.out.println("Category_Rating added  !");
            
            /*
            //ajouter est affecter l'id:
            String idreq="SELECT LAST_INSERT_ID();";
                Statement st2 = conn.createStatement();
                ResultSet rs =st2.executeQuery(idreq);
                while (rs.next()){
                    int getid=rs.getInt(1);
                    cr.setId_Cat_Rating(getid);
                }
                if (cr.getId_Cat_Rating()!= 0){
                    System.out.println("Id affected!");
                }*/
                
                
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(int id) {
        
        try {
            String req = "DELETE FROM `categoryrating` WHERE `id_Cat_Rating` = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Category_Rating deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifier(int id, CategoryRating cr) {
        
         try {
            String req = "UPDATE `categoryrating` SET `name_Cat_Rating`='"+cr.getName_Cat_Rating()+"',`description_Cat_Rating`='"+cr.getDescription_Cat_Rating()+"',`logo`='"+cr.getLogo()+"' WHERE `id_Cat_Rating`="+id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Category_Rating updated !"); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        
    }

    @Override
    public List<CategoryRating> getAll() {
        
        List<CategoryRating> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `categoryrating`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                CategoryRating cr = new CategoryRating(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(cr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

    @Override
    public CategoryRating getOneByID(int id) {
        
        CategoryRating cr = null;
        try {
            String req = "SELECT * FROM `categoryrating`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt(1)==id){
                 cr = new CategoryRating(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cr;
        
    }
    
    public CategoryRating getOneByName(String name) {
        
        CategoryRating cr = null;
        try {
            String req = "SELECT * FROM `categoryrating`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString(2).equals(name)){
                 cr = new CategoryRating(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cr;
        
    }
    
    
    
    
    
}
