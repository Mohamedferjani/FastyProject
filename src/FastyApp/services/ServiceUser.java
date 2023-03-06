/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.services;

import FastyApp.entities.Transaction;
import FastyApp.entities.User;
import FastyApp.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ISSAM
 */
public class ServiceUser implements InterfaceUser<User> {

    Connection connect = DataSource.getInstance().getCnx();

    @Override
    public String getUserFullNameById(int id) {
        User user = null;
        try {
            String req = "SELECT * FROM user WHERE id_user = " + id;
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                user = new User(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("cin"), rs.getString("num_tel"), rs.getString("password"), rs.getString("token"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user.getPrenom()+" "+user.getNom();
        
        //ssssssssssssssssssssssssssssssssssssssssssssss
    }

}
