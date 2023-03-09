/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fasty.services;


import edu.fasty.entities.Categorie;
import edu.fasty.entities.Produit;
import edu.fasty.entities.User;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.fasty.services.IPService;

/**
 *
 * @author Homrani
 */
public class ServiceProduit implements IPService<Produit>{
    Connection cnx;
    Statement st;
    public ServiceProduit (){
        cnx = DataSource.getInstance().getCnx();}

    @Override
    public void ajouter(Produit produit) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `produit`(`nom_produit`, `description`, `img_produit`, `valeur`, `id_user`, `id_categorie`) VALUES ('"+produit.getNom_produit()+"','"+produit.getDescription()+"','"+produit.getImg_produit()+"','"+produit.getValeur()+"','"+produit.getId_user().getId_user()+"','"+produit.getId_categorie()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
    }

    @Override
    public List<Produit> afficher() throws SQLException {
        IServiceUser us = new IServiceUser();
                Produit produit =null;
        List<Produit> Lcategorie= new ArrayList<Produit>();
        st = cnx.createStatement();       
        String query = "SELECT * FROM produit LEFT JOIN transaction ON produit.id_produit = transaction.id_produit WHERE transaction.id_produit IS NULL;";
        ResultSet rs= st.executeQuery(query);
        while (rs.next()){
             User u = us.getOneById(rs.getInt("id_user"));
            produit = new Produit(rs.getInt("id_produit"),rs.getString("nom_produit"),rs.getString("description"),rs.getString("img_produit"),rs.getString("valeur"),u,rs.getInt("id_categorie"));
//        produit.setId_produit(rs.getInt("id_produit"));
//        produit.setNom_produit(rs.getString("nom_produit"));    
//        produit.setDescription(rs.getString("description"));
//        produit.setImg_produit(rs.getString("img_produit"));
//        produit.setValeur(rs.getString("valeur"));
//        produit.setId_user(rs.getInt("id_user"));
//        produit.setId_categorie(rs.getInt("id_categorie"));
        Lcategorie.add(produit);
        }        
        return Lcategorie;
    }

    @Override
    public void supprimer(int id_produit) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from produit where id_produit  =" + id_produit ;
        stm.executeUpdate(query);
    }
    
    public Produit SearchById(long id_produit) throws SQLException{
        IServiceUser us = new IServiceUser();
        Statement stm = cnx.createStatement();
        Produit produit = null;
        String query = "select * from produit where id_produit="+id_produit;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            User u = us.getOneById(rs.getInt("id_user"));
            produit = new Produit(rs.getInt("id_produit"),rs.getString("nom_produit"),rs.getString("description"),rs.getString("img_produit"),rs.getString("valeur"),u,rs.getInt("id_categorie"));
//        produit.setId_produit(rs.getInt("id_produit"));
//        produit.setNom_produit(rs.getString("nom_produit"));    
//        produit.setDescription(rs.getString("description"));
//        produit.setImg_produit(rs.getString("img_produit"));
//        produit.setValeur(rs.getString("valeur"));
//        produit.setId_user(rs.getInt("id_user"));
//        produit.setId_categorie(rs.getInt("id_categorie"));
        
        }
        return produit;
        }

    @Override
    public void modifier(int id_ProduitModifier, Produit produit) throws SQLException {
        Statement stm = cnx.createStatement();
        Produit p =SearchById(id_ProduitModifier);
        String query = "UPDATE `produit` SET `nom_produit`='"+produit.getNom_produit()+"',`description`='"+produit.getDescription()+"',`img_produit`='"+produit.getImg_produit()+"',`description`='"+produit.getDescription()+"',`valeur`= '"+produit.getValeur()+"',`id_categorie`='"+produit.getId_categorie()+"' where id_produit ="+p.getId_produit();
        stm.executeUpdate(query);
    }

    @Override
    public Produit getCategorieByid(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produit getCategorieByNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public double getMontantById(int pId) {
        double value = 0.0;
        try {
            String req = "SELECT p.valeur FROM produit p WHERE p.id_produit = " + pId;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                if(rs.getString("valeur").matches("\\d+(\\.\\d+)?")){
                value = Double.parseDouble(rs.getString("valeur"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return value;
    }
    
    public String getNameById(int pId) {
        String name = "";
        try {
            String req = "SELECT p.nom_produit FROM produit p WHERE p.id_produit = " + pId;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                name = rs.getString("nom_produit");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }
    
}
