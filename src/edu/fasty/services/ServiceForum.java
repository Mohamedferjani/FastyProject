/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.entities.Reponse;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author FERJANI
 */
public class ServiceForum implements FService{
    Connection con = DataSource.getInstance().getCnx();
@Override
    public boolean ajouterForum(Forum f) {
        int iduser =15;
        try {
            String req1 = "SELECT * FROM forum WHERE forum.titre = '"+f.getTitre()+"'";
             Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req1);
            if(rs.next()){
                Alert alertType=new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("Name already exists !");
                alertType.show();
                return false;
            }else {
                String req = "INSERT INTO `forum` (`titre`, `id_user`, `contenu`) VALUES ('"+f.getTitre()+"', '"+iduser+"' , '"+f.getContenu()+"')";
                PreparedStatement ps = con.prepareStatement(req);
                ps.executeUpdate(req);
                System.out.println("Forum Successfully Created !");
                return true;
            }
          
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public void supprimerForum(int id) {
         try {
            String req = "DELETE FROM `forum` WHERE id_forum ="+id;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Forum Successfully Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public void modifierForum(Forum f) {
        try {
            String req = "UPDATE `forum` SET `titre` = '"+f.getTitre()+"' , `contenu` = '" +f.getContenu()+"'  WHERE `forum`.`id_forum` = "+ f.getId_forum();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Forum Successfully Updated !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
    @Override
    public List<Forum> getAllForums() {
        List<Forum> forums = new ArrayList<>();
         try {
            String req = "SELECT * from forum";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Forum f = new Forum(rs.getInt(1),rs.getString("titre"),rs.getString("contenu"));
                forums.add(f);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return forums;
    }

  

    @Override
    public Forum getForumById(int id) {
        Forum f = null;
         try {
            String req = "SELECT * from forum WHERE id_forum ="+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            if(rs.next()){
            f = new Forum(rs.getInt(1),rs.getString("titre"),rs.getString("contenu"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return f;
    }
}