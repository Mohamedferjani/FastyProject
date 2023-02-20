/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Bid;
import edu.fasty.entities.Event;
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
 * @author IHEB
 */
public class ServiceBid implements IBid<Bid> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Bid B) {

  try {
            String req = "INSERT INTO `bid` (`id_event`, `id_produit`,`starting_price`,`id_last_bidder`) VALUES (?,?,?,?)";
          
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setInt(1, B.getId_event());
           ps.setInt(2, B.getId_produit());
           ps.setInt(3, B.getStarting_price());
           ps.setInt(4,B.getId_last_bidder());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(int id) {
try {
            String req = "DELETE FROM `bid` WHERE id_bid = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("bid deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Bid B) {
 try {
            String req = "UPDATE `bid` SET `id_produit` = '" +B.getId_produit() + "', `starting_price` = '" + B.getStarting_price() + "',`id_last_bidder` ='"+B.getId_last_bidder() +"' WHERE `bid`.`id_bid` = " + B.getId_bid();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("bid UPDATED  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Bid> getAll() {
          List<Bid> list = new ArrayList<>();
          
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Bid bid1 = new Bid(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
                list.add(bid1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        




    }

    @Override
    public Bid getOneById(int id) {
      Bid bid1=null;
             try {
            String req = "Select * from bid where id_bid ="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                bid1 = new Bid(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return bid1;
        
        
        
}
}