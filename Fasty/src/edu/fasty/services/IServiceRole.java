/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Role;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GAMING
 */
public class IServiceRole {
       Connection cnx = DataSource.getInstance().getCnx();

    public void insert(Role t) {
String requete="insert into role (type) values (?)";
        try {
            PreparedStatement usr=cnx.prepareStatement(requete);
            usr.setString(1,t.getType());
            usr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Role t) {
        String requete="delete from role where id_role = "+t.getId_role();
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(IServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Role t) {
         String requete = "update role set  type=? where id_role="+t.getId_role();
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1,t.getType()); 
            pst.executeUpdate();
            System.out.println("role mise à jour");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de le role" + ex.getMessage());
        }
    }

    public List<Role> readAll() {
        String requete ="select * from role";
        List<Role> list=new ArrayList<>();
        try {
            Statement st=cnx.createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
               Role t=new Role
        ( rs.getInt("id_role"),rs.getString("type"));
               list.add(t);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(IServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Role readById(int id) {
        String requete ="select * from role where id_role="+id;
       Role r=new Role();
       
       try {
             Statement st=cnx.createStatement();
           ResultSet rs= st.executeQuery(requete);
           while(rs.next()){
           r.setId_role(rs.getInt("id_role"));
           r.setType(rs.getString("type")); 
          
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(IServiceRole.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return r;
    }

    public void insert1(Role t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
