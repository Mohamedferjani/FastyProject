/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Produit;
import edu.fasty.entities.User;
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
    
     Connection connect = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Wishlist w) {    
        try {
            String req = "INSERT INTO `wishlist`(`list_produit`,`id_user`) VALUES (?,?)";
            PreparedStatement ps = connect.prepareStatement(req);
            ArrayList<Integer> listidproduits = new ArrayList();
            for (int i = 0; i < w.getList_Produit().size(); i++) {
            Produit value = w.getList_Produit().get(i);
     listidproduits.add(value.getId_produit());
}
            String idproduits = listidproduits.toString();
            ps.setInt(2,w.getId_user().getId_user());
            ps.setString(1,idproduits);
            ps.executeUpdate();
            System.out.println("Wishlist added  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `wishlist` WHERE `id_wishlist`= " + id;
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Wishlist deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifier(int id, Wishlist w) {
         String produitString = w.getList_Produit().toString();
         ArrayList<Integer> listidproduits = new ArrayList();
            for (int i = 0; i < w.getList_Produit().size(); i++) {
            Produit value = w.getList_Produit().get(i);
     listidproduits.add(value.getId_produit());
}
         String idproduits = listidproduits.toString();
         try {
            String req = "UPDATE `wishlist` SET `list_produit`='"+ idproduits +"' WHERE `id_user`="+ id;
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Wishlist updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     
    }
    
   public Wishlist getOneByIduser(int id) {
        IServiceUser us = new IServiceUser();
        Wishlist w = null;
        try {
            String req = "SELECT id_user FROM `wishlist` where id_user = "+id;
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt(1)==id){
                    User u = us.getOneById(rs.getInt(3));
                 String produitString = rs.getString(2);
                produitString = produitString.substring(1, produitString.length() - 1); // Remove square brackets
                String[] produitArray = produitString.split(", "); // Split string into individual elements
                ArrayList<Integer> list_Produit = new ArrayList<>();
                for (String str : produitArray) {
                    list_Produit.add(Integer.parseInt(str)); // Convert each element to an integer and add it to the ArrayList
                }
                
                // w = new Wishlist(rs.getInt(1),u,list_Produit);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return w;
        
    }
    @Override
    public List<Wishlist> getAll() {
        
        List<Wishlist> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `wishlist`";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String produitString = rs.getString(3);
                produitString = produitString.substring(1, produitString.length() - 1); // Remove square brackets
              //  String[] produitArray = produitString.split(", "); // Split string into individual elements
//                ArrayList<Produit> list_Produit = new ArrayList<>();
//                for (String str : produitArray) {
//                    list_Produit.add(Integer.parseInt(str)); // Convert each element to an integer and add it to the ArrayList
//                }
                
//                Wishlist w = new Wishlist(rs.getInt(1), rs.getInt(2),list_Produit);
//                list.add(w);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

    @Override
    public Wishlist getOneByID(int id) {
        
        Wishlist w = null;
//        try {
//            String req = "SELECT * FROM `wishlist1`";
//            Statement st = connect.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                if (rs.getInt(1)==id){
//                 String produitString = rs.getString(3);
//                produitString = produitString.substring(1, produitString.length() - 1); // Remove square brackets
//                String[] produitArray = produitString.split(", "); // Split string into individual elements
//                ArrayList<Integer> list_Produit = new ArrayList<>();
//                for (String str : produitArray) {
//                    list_Produit.add(Integer.parseInt(str)); // Convert each element to an integer and add it to the ArrayList
//                }
//                
//                 w = new Wishlist(rs.getInt(1), rs.getInt(2),list_Produit);
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

        return w;
        
    }
    
    
    
    
}
