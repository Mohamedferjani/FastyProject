/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Wishlist;
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
 * @author skand
 */
public class ServiceWishlist implements InterfaceServices<Wishlist> {
    
    Connection conn=DataSource.getInstance().getConn();

    @Override
    public void ajouter(Wishlist w) {
        
        try {
            String req = "INSERT INTO `wishlist1`(`id_user`, `list_produit`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            String produitString = w.getList_Produit().toString();
            System.out.println(produitString);
            ps.setInt(1,w.getId_user());
            ps.setString(2,produitString);
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
         String produitString = w.getList_Produit().toString();
        
         try {
            String req = "UPDATE `wishlist1` SET `list_produit`='"+ produitString +"' WHERE `id_wishlist`="+ id;
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
                String produitString = rs.getString(3);
                produitString = produitString.substring(1, produitString.length() - 1); // Remove square brackets
                String[] produitArray = produitString.split(", "); // Split string into individual elements
                ArrayList<Integer> list_Produit = new ArrayList<>();
                for (String str : produitArray) {
                    list_Produit.add(Integer.parseInt(str)); // Convert each element to an integer and add it to the ArrayList
                }
                
                Wishlist w = new Wishlist(rs.getInt(1), rs.getInt(2),list_Produit);
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
                 String produitString = rs.getString(3);
                produitString = produitString.substring(1, produitString.length() - 1); // Remove square brackets
                String[] produitArray = produitString.split(", "); // Split string into individual elements
                ArrayList<Integer> list_Produit = new ArrayList<>();
                for (String str : produitArray) {
                    list_Produit.add(Integer.parseInt(str)); // Convert each element to an integer and add it to the ArrayList
                }
                
                 w = new Wishlist(rs.getInt(1), rs.getInt(2),list_Produit);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return w;
        
    }
    
    
    
    
}
