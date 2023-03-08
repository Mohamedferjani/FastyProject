/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fasty.services;

import edu.fasty.entities.Bid;
import edu.fasty.utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IHEB
 */
public class ServiceBid implements IService<Bid>{
    Connection cnx;
    Statement st;
    public ServiceBid (){
        cnx = Myconnexion.getInstance().getCnx();}


    @Override
    public void ajouter(Bid bid) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `bid`(`id_event`, `id_produit`, `starting_price`, `id_User`) VALUES ('"+bid.getId_event()+"','"+bid.getId_produit()+"','"+bid.getStarting_price()+"','"+bid.getId_User()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
    }

    @Override
    public List<Bid> afficher() throws SQLException {
        List<Bid> LBid = new ArrayList<Bid>();
        st = cnx.createStatement();       
        String query = "SELECT * FROM bid";
        ResultSet rs= st.executeQuery(query);
        while (rs.next()){
        Bid bid = new Bid();
        bid.setId_bid(rs.getInt("id_bid"));
        bid.setId_event(rs.getInt("id_event"));
        bid.setId_produit(rs.getInt("id_produit"));
        bid.setStarting_price(rs.getInt("starting_price"));
        bid.setId_User(rs.getInt("id_User"));
        LBid.add(bid);}        
        return LBid;
    }

    @Override
    public void supprimer(int id_bid) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from bid where id_bid =" + id_bid ;
        stm.executeUpdate(query);
    }
    
    public Bid SearchById(long id_bid) throws SQLException{
        Statement stm = cnx.createStatement();
        Bid bid = new Bid();
        String query = "select * from bid where id_bid="+id_bid;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        bid.setId_bid(rs.getInt("id_bid"));
        bid.setId_event(rs.getInt("id_event"));
        bid.setId_produit(rs.getInt("id_produit"));
        bid.setId_User(rs.getInt("id_User"));
        bid.setStarting_price(rs.getInt("starting_price"));}
        return bid;
     }

    @Override
    public void modifier(int id_BidModifier, Bid bid) throws SQLException {
        Statement stm = cnx.createStatement();
        Bid b =SearchById(id_BidModifier);
        String query = "UPDATE `bid` SET `id_event`='"+bid.getId_event()+"',`id_produit`='"+bid.getId_produit()+"',`starting_price`='"+bid.getStarting_price()+"',`id_User`='"+bid.getId_User()+"' where id_bid="+b.getId_bid();
        stm.executeUpdate(query);
    }
    
        public void modifierFront(int id_BidModifier, Bid bid) throws SQLException {
        Statement stm = cnx.createStatement();
        Bid b =SearchById(id_BidModifier);
        String query = "UPDATE `bid` SET `starting_price`='"+bid.getStarting_price()+"',`id_User`='"+bid.getId_User()+"',`id_lastbidder`='"+bid.getId_lastbidder()+"',`last_modified_time`='"+bid.getLast_modified_time()+"' where id_bid="+b.getId_bid();
        stm.executeUpdate(query);
    }
        
public String getBidWinner(int id_bid) {
    String winner = null;
    try {
        Statement stm = cnx.createStatement();
         String query = "SELECT id_lastbidder FROM bid WHERE id_bid = " + id_bid;
        ResultSet rs = stm.executeQuery(query);
        if (rs.next()) {
            winner = rs.getString("id_lastbidder");
            System.out.print(winner);
        }
        rs.close();
        stm.close();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceBid.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return winner;
}
public Date getLastTimeDataModifiedbyBidId(int id_bid){

Date lastdate = null;
    try {
        Statement stm = cnx.createStatement();
         String query = "SELECT last_modified_time FROM bid WHERE id_bid = " + id_bid;
        ResultSet rs = stm.executeQuery(query);
        if (rs.next()) {
             lastdate = rs.getDate("last_modified_time");
             //System.out.print(lastdate);
        }
        rs.close();
        stm.close();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceBid.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return lastdate;
}

}
 
 
 
    

