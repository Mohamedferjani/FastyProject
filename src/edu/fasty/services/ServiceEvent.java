/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fasty.services;

import edu.fasty.entities.Evenement;
import edu.fasty.entities.User;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author IHEB
 */
public class ServiceEvent implements IEService<Evenement>{

        Connection cnx;
        Statement st;
    public ServiceEvent (){
        cnx = DataSource.getInstance().getCnx();}
    
/*****************************Add Evenement***************************/    
    @Override
    public void ajouter(Evenement evenement) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `evenement`(`titre`, `date`, `id_user`) VALUES ('"+evenement.getTitre()+"','"+LocalDate.now()+"','"+evenement.getId_user().getId_user()+"')";
        st.executeUpdate(query);
        }         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
    }

    @Override
    public List<Evenement> afficher() throws SQLException {
        IServiceUser su = new IServiceUser();
        List<Evenement> Levent = new ArrayList<Evenement>();
        st = cnx.createStatement();       
        String query = "SELECT * FROM evenement";
        ResultSet rs= st.executeQuery(query);
        while (rs.next()){
            User u = su.getOneById(rs.getInt("id_user"));
        Evenement event = new Evenement(rs.getInt("id_evenement"),rs.getTimestamp(2).toLocalDateTime(),rs.getString("titre"),u);
//        event.setId_evenement(rs.getInt("id_evenement"));
//        event.setDate(rs.getTimestamp(2).toLocalDateTime());
//        event.setId_user(rs.getInt("id_user"));
//        event.setTitre(rs.getString("titre"));
        Levent.add(event);}        
        return Levent;
    }

    @Override
    public void supprimer(int id_evenement) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from evenement where id_evenement =" + id_evenement ;
        stm.executeUpdate(query);
    }

    public Evenement SearchById(long id_evenement) throws SQLException{
        IServiceUser su = new IServiceUser();
        Statement stm = cnx.createStatement();
        Evenement evenement = null;
        String query = "select * from evenement where id_evenement="+id_evenement;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            User u = su.getOneById(rs.getInt("id_user"));
            evenement = new Evenement(rs.getInt("id_evenement"),rs.getTimestamp(2).toLocalDateTime(),rs.getString("titre"),u);
//        evenement.setId_evenement(rs.getInt("id_evenement"));
//        evenement.setDate(rs.getTimestamp(2).toLocalDateTime());
//        evenement.setId_user(rs.getInt("id_user"));
//        evenement.setTitre(rs.getString("titre"));
        
        }
        return evenement;
        }
    
    
    @Override
    public void modifier(int id_EventModifier, Evenement event) throws SQLException {
        Statement stm = cnx.createStatement();
        Evenement e =SearchById(id_EventModifier);
        String query = "UPDATE `evenement` SET `date`='"+LocalDateTime.now()+"',`id_user`='"+event.getId_user().getId_user()+"',`titre`='"+event.getTitre()+"' where id_evenement="+e.getId_evenement();
        stm.executeUpdate(query);
    }
    
    
    public HashMap<String, Double> StatistiqueParTitle() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT titre, COUNT(*) as nb FROM evenement GROUP BY titre;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("titre");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}

}
