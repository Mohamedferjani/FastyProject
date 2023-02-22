/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Event;
import edu.fasty.services.IEvent;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author IHEB
 */
public class ServiceEvent implements IEvent <Event>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Event E) {

      try {
            String req = "INSERT INTO `evenement` (`date`, `isAuction`,`id_user`,`titre`,`description`) VALUES (?,?,?,?,?)";
            //String req2= "SELECT id_user FROM `user` WHERE email = 'irezgui@gmail.com'";
            //PreparedStatement ps2 = cnx.prepareStatement(req2);
            PreparedStatement ps = cnx.prepareStatement(req);
        //    ResultSet resultSet=ps2.executeQuery();
//             int userId = 0;
//                if (resultSet.next()) {
//                    userId = resultSet.getInt(1);
//                }
              //System.out.print(userId);
            ps.setDate(1, java.sql.Date.valueOf(E.getDate()));
            ps.setBoolean(2, E.isIsAuction());
            ps.setInt(3,E.getId_user());
            ps.setString(4,E.getTitre());
            ps.setString(5,E.getDescription());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
try {
            String req = "DELETE FROM `evenement` WHERE id_evenement = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("EVENT deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }



    }

    @Override
    public void modifier(Event E) {
          try {
            String req = "UPDATE `evenement` SET `date` = '" +E.getDate() + "', `isAuction` = '" + E.isIsAuction() + "',`titre` ='"+E.getTitre() + "', `description` = '" + E.getDescription() +"' WHERE `evenement`.`id_evenement` = " + E.getId_evenement();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("EVENT UPDATED updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Event> getAll() {
          List<Event> list = new ArrayList<>();
          
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Event Ev = new Event(rs.getInt(1),rs.getDate(2).toLocalDate(),rs.getBoolean(3),rs.getInt(4),rs.getString("titre"),rs.getString("description"));
                list.add(Ev);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        }
        public List<Event> getMyEvents(int id ) {
          List<Event> list = new ArrayList<>();
          
        try {
            String req = "Select * from evenement where id_user = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Event Ev = new Event(rs.getInt(1),rs.getDate(2).toLocalDate(),rs.getBoolean(3),rs.getInt(4),rs.getString("titre"),rs.getString("description"));
                list.add(Ev);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
        
        
    }
    

    @Override
    public Event getOneById(int id) {
        Event Ev=null;
             try {
            String req = "Select * from evenement where id_evenement = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Ev = new Event(rs.getInt(1),rs.getDate(2).toLocalDate(),rs.getBoolean(3),rs.getInt(4),rs.getString("titre"),rs.getString("description"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Ev;
    }
    
}
