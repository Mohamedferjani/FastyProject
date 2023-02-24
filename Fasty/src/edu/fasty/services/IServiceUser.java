/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.User;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author MSI GAMING
 */
public class IServiceUser implements IService<User> {
Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(User u) {
            try {
            String req = "INSERT INTO `user` (`nom`, `prenom`,`adresse`,`email`,`cin`,`num_tel`,`password`,`token`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, u.getPrenom());
            ps.setString(1, u.getNom());
            ps.setString(3, u.getAdresse());
            ps.setString(4, u.getEmail());
            ps.setInt(5, u.getCin());
            ps.setInt(6, u.getNum_tel());
            ps.setString(7, u.getPassword());
            ps.setString(8, u.getToken());
            ps.executeUpdate();
            System.out.println("Tzed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id_user = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User u) {
        try {
            String req = "UPDATE `user` SET `nom` = '" + u.getNom() + "' , `prenom` = '" + u.getPrenom() + "' , `adresse` = '" + u.getAdresse() + "' , `email` = '" + u.getEmail()+ "' , `cin` = '" + u.getCin()+ "' , `num_tel` = '" + u.getNum_tel()+ "' , `password` = '" + u.getPassword()+ "' , `token` = '" + u.getToken()+ "' WHERE id_user = " + u.getId_user();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u;
                u = new User(rs.getInt("id_user"),rs.getInt("cin"),rs.getInt("num_tel"), rs.getString("nom"), rs.getString("prenom"),rs.getString("adresse"),rs.getString("email"),rs.getString("password"),rs.getString("token"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    return list;
    }

   @Override
    public User getOneById(int id) {
    User u = null;
        try {
            String req = "Select * from user WHERE id_user = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                 u = new User(rs.getInt("id_user"),rs.getInt("cin"),rs.getInt("num_tel"), rs.getString("nom"), rs.getString("prenom"),rs.getString("adresse"),rs.getString("email"),rs.getString("password"),rs.getString("token"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return u;    }
    
    private String hashPasswd(String R){
        User u= new User ();
        R = u.getPassword();
        return BCrypt.hashpw(R, BCrypt.gensalt());
    }
}
