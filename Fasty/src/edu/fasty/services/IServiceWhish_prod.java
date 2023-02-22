/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.User;
import edu.fasty.entities.Whish_prod;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI GAMING
 */
public class IServiceWhish_prod implements IService<Whish_prod> {
Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Whish_prod w) {
 try {
            String req = "INSERT INTO `whishlist_produit` (`id_Whishlist`,`id_produit`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, w.getId_whishlist());
            ps.setInt(2, w.getId_produit());
            ps.executeUpdate();
            System.out.println("Tzed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `whishlist_produit` WHERE id_whishlist = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Whish_prod w) {
        try {
            String req = "UPDATE `whishlist_produit` SET `id_produit` = '" + w.getId_produit() + "' WHERE id_whishlist = " + w.getId_whishlist();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @Override
    public List<Whish_prod> getAll() {
        List<Whish_prod> list = new ArrayList<>();
        try {
            String req = "Select * from whishlist_produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Whish_prod w;
                w = new Whish_prod(rs.getInt("id_whishlist"),rs.getInt("id_whishlist"));
                list.add(w);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    return list;    }

    @Override
    public Whish_prod getOneById(int id) {
   Whish_prod w = null;
        try {
            String req = "Select * from whishlist_prod WHERE id_whishlist = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                 w = new Whish_prod(rs.getInt("id_whishlist"),rs.getInt("id_produit"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return w;    }
    
}
