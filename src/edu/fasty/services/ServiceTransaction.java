/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Transaction;
import edu.fasty.entities.User;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ServiceTransaction implements InterfaceTransaction<Transaction>{
    
     Connection connect = DataSource.getInstance().getCnx();

    @Override
    public void ajouterTransaction(Transaction t) {
          try {

String req = "INSERT INTO `transaction` (`id_recepteur`, `id_expiditeur`, `NomTransaction`, `id_produit`) VALUES (?, ?, ?, ?)";
PreparedStatement pstmt = connect.prepareStatement(req);
pstmt.setInt(1, t.getId_recepteur().getId_user());
pstmt.setInt(2, t.getId_expiditeur().getId_user());
pstmt.setString(3, t.getNomTransaction());
pstmt.setInt(4, t.getId_produit());
pstmt.executeUpdate();    
//Statement st = connect.createStatement();
//            st.executeUpdate(req);
            System.out.println("Trasaction Created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @Override
    public boolean supprimerTransaction(int id) {
        boolean res = false;
        try {
            String req = "DELETE FROM `transaction` WHERE id_transaction = " + id;
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("transaction Deleted !");
            res=true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    @Override
    public void modifierTransaction(Transaction t) {
         try {
           
            String req = "UPDATE `transaction` SET `id_recepteur` = '" + t.getId_recepteur().getId_user() + "' ,`id_expiditeur` = '" + t.getId_expiditeur().getId_user() + "' ,`id_produit`= '" + t.getId_produit() + "' WHERE id_transaction = " + t.getId_transaction(); 
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Transaction Updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Transaction> getAllTransaction() {
        IServiceUser su = new IServiceUser();
       ObservableList<Transaction> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from transaction";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = su.getOneById(rs.getInt(2));
                User u2 = su.getOneById(rs.getInt(3));
                Transaction t = new Transaction(rs.getInt(1), u, u2, rs.getInt(4), rs.getString(5));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

    @Override
    public Transaction getOneByIdT(int id) {
           IServiceUser su = new IServiceUser();
         Transaction t = null;
        try {
            String req = "Select * from transaction WHERE id_transaction = "+id;
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                  if (rs.getInt(1)==id){
                       User u = su.getOneById(rs.getInt(2));
                User u2 = su.getOneById(rs.getInt(3));
                t = new Transaction(rs.getInt(1), u, u2, rs.getInt(4), rs.getString(5));}
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return t;
    }


       
    }
    

