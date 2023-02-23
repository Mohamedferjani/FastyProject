/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.User;
import edu.fasty.entities.Whishlist;
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
public class IServiceWhishlist implements IService<Whishlist> {
Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Whishlist w) {
           try {
            String req = "INSERT INTO `wishlist` (`id_wishlist`, `id_produit`,`id_user`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(2, w.getId_produit());
            ps.setInt(1, w.getId_wishlist());
            ps.setInt(3, w.getId_user());
            ps.executeUpdate();
            System.out.println("Tzed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `whishlist` WHERE id_wishlist = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("wishlist deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Whishlist u) {
        try {
            String req = "UPDATE `wishlist` SET `id_produit` = '" + u.getId_produit() + "' , `id_user` = '" + u.getId_user() + "'  WHERE id_wishlist = " + u.getId_wishlist();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Wishlist updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Whishlist> getAll() {
            List<Whishlist> list = new ArrayList<>();
        try {
            String req = "Select * from wishlist";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Whishlist w;
                w = new Whishlist(rs.getInt("id_wishlist"),rs.getInt("id_produit"),rs.getInt("id_user"));
                list.add(w);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    return list;
    }

    @Override
    public Whishlist getOneById(int id) {
    Whishlist w = null;
        try {
            String req = "Select * from wishlist WHERE id_user = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                     w = new Whishlist(rs.getInt("id_wishlist"),rs.getInt("id_produit"),rs.getInt("id_user"));            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return w;    }
    }



