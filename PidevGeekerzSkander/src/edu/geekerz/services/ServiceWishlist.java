/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.geekerz.services;

import edu.geekerz.entities.Wishlist;
import edu.geekerz.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skand
 */
public class ServiceWishlist implements InterfaceServices<Wishlist> {
    
    Connection conn=DataSource.getInstance().getConn();

    @Override
    public void ajouter(Wishlist w) {
        
        try {
            String req = "INSERT INTO `wishlist1`(`id_user`, `name_wishlist`, `list_produit`, `creation_date`) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1,w.getId_user());
            ps.setString(2,w.getName_wishlist());
            ps.setString(3,w.getList_produit());
            ps.setString(4,w.getCreation_date());
            ps.executeUpdate();
            System.out.println("Wishlist added  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(int id) {
        
        try {
            String req = "DELETE FROM `wishlist1` WHERE `id_wishlist`= " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Wishlist deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifier(int id, Wishlist w) {
        
         try {
            String req = "UPDATE `wishlist1` SET `name_wishlist`='"+ w.getName_wishlist() +"',`list_produit`='"+ w.getList_produit() +"',`creation_date`='"+ w.getCreation_date() +"' WHERE `id_wishlist`="+ id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Wishlist updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Wishlist> getAll() {
        
        List<Wishlist> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `wishlist1`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Wishlist w = new Wishlist(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5));
                list.add(w);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

    @Override
    public Wishlist getOneByID(int id) {
        
        Wishlist w = null;
        try {
            String req = "SELECT * FROM `wishlist1`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt(1)==id){
                 w = new Wishlist(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5));}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return w;
        
    }
    
    public Wishlist getOneByName(String name) {
        
        Wishlist w = null;
        try {
            String req = "SELECT * FROM `wishlist1`";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString(3).equals(name)){
                 w = new Wishlist(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getString(4), rs.getString(5));}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return w;
        
    }
    
    
}
