/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.services;

import FastyApp.entities.Transaction;
import FastyApp.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISSAM
 */
public class ServiceTransaction implements InterfaceTransaction<Transaction>{
    
     Connection connect = DataSource.getInstance().getCnx();

    @Override
    public void ajouterTransaction(Transaction t) {
          try {

            String req = "INSERT INTO `transaction`( `id_recepteur`, `id_expiditeur`, `id_produit`) VALUES ('" + t.getId_recepteur() + "','" + t.getId_expiditeur() + "','"+ t.getId_produit()+"')";
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Trasaction Created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @Override
    public void supprimerTransaction(int id) {
        try {
            String req = "DELETE FROM `transaction` WHERE id_transaction = " + id;
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("transaction Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierTransaction(Transaction t) {
         try {
           
            String req = "UPDATE `transaction` SET `id_recepteur` = '" + t.getId_recepteur() + "' ,`id_expiditeur` = '" + t.getId_expiditeur() + "' ,`id_produit`= '" + t.getId_produit() + "' WHERE id_transaction = " + t.getId_transaction(); 
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Transaction Updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Transaction> getAllTransaction() {
       List<Transaction> list = new ArrayList<>();
        try {
            String req = "Select * from transaction";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Transaction t = new Transaction(rs.getInt(1), rs.getInt("2"), rs.getInt(3), rs.getInt(4), rs.getString(5));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

    @Override
    public Transaction getOneByIdT(int id) {
         Transaction t = null;
        try {
            String req = "Select * from transaction WHERE id_transaction = "+id;
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                  if (rs.getInt(1)==id){
                      
                t = new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));}
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return t;
    }
       
    }
    

