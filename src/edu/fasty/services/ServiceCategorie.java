/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fasty.services;

import edu.fasty.entities.Categorie;
import edu.fasty.entities.User;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.fasty.services.IPService;

/**
 *
 * @author Homrani
 */
public class ServiceCategorie implements IPService<Categorie>{
    Connection cnx;
    Statement st;
    public ServiceCategorie (){
        cnx = DataSource.getInstance().getCnx();}

    @Override
    public void ajouter(Categorie categorie) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `categorie`(`nom_categorie`, `type_categorie`, `id_user`) VALUES ('"+categorie.getNom_categorie()+"','"+categorie.getType_categorie()+"','"+categorie.getId_user().getId_user()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        
    }
    @Override
    public List<Categorie> afficher() throws SQLException {
        IServiceUser us = new IServiceUser();
        List<Categorie> Lcategorie= new ArrayList<Categorie>();
        st = cnx.createStatement();       
        String query = "SELECT * FROM categorie";
        ResultSet rs= st.executeQuery(query);
        while (rs.next()){
            User u = us.getOneById(rs.getInt("id_user"));
        Categorie categorie = new Categorie(rs.getInt("id_categorie"),rs.getString("nom_categorie"),rs.getString("type_categorie"),u);
//        categorie.setId_categorie(rs.getInt("id_categorie"));
//        categorie.setNom_categorie(rs.getString("nom_categorie"));    
//        categorie.setType_categorie(rs.getString("type_categorie"));
       // categorie.setId_user(rs.getInt("id_user"));
        Lcategorie.add(categorie);}        
        return Lcategorie;
    }

    @Override
    public void supprimer(int id_categorie) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from categorie where id_categorie =" + id_categorie;
        stm.executeUpdate(query);
    }
    
        public Categorie SearchById(long id_categorie) throws SQLException{
                    IServiceUser us = new IServiceUser();
        Statement stm = cnx.createStatement();
        Categorie categorie = null;
        String query = "select * from categorie where id_categorie="+id_categorie;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            User u = us.getOneById(rs.getInt("id_user"));
            categorie = new Categorie(rs.getInt("id_categorie"),rs.getString("nom_categorie"),rs.getString("type_categorie"),u);
//        categorie.setId_categorie(rs.getInt("id_categorie"));
//        categorie.setNom_categorie(rs.getString("nom_categorie"));    
//        categorie.setType_categorie(rs.getString("type_categorie"));
       // categorie.setId_user(rs.getInt("id_user"))
                
                ;}
        return categorie;
        }

    @Override
    public void modifier(int id_CategorieModifier, Categorie categorie) throws SQLException {
        Statement stm = cnx.createStatement();
        Categorie c =SearchById(id_CategorieModifier);
        String query = "UPDATE `categorie` SET `nom_categorie`='"+categorie.getNom_categorie()+"',`type_categorie`='"+categorie.getType_categorie()+"' where id_categorie="+c.getId_categorie();
        stm.executeUpdate(query);
    }
    
    public HashMap<String, Double> StatistiqueParType() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT type_categorie, COUNT(*) as nb FROM categorie GROUP BY type_categorie;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("type_categorie");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}

    @Override
    public Categorie getCategorieByid(int id) {
                          IServiceUser us = new IServiceUser();

         Categorie c = null;
        try {
            String req = "Select * from categorie WHERE id_cateogrie = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = us.getOneById(rs.getInt("id_user"));
                 c = new Categorie(rs.getInt("id_categorie"),rs.getString("non_categorie"),rs.getString("type_categorie"),u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;    }

    @Override
    public Categorie getCategorieByNom(String nom) {
                            IServiceUser us = new IServiceUser();

         Categorie c = null;
        try {
        String req = "SELECT * FROM categorie WHERE nom_categorie = '" + nom + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = us.getOneById(rs.getInt("id_user"));
                 c = new Categorie(rs.getInt("id_categorie"),rs.getString("nom_categorie"),rs.getString("type_categorie"),u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }

    
}

