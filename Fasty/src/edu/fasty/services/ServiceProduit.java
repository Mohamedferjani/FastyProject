/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Produit;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hp
 */
public class ServiceProduit implements INTService<Produit> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Produit p) {
        try {
            String req = "INSERT INTO `Produit` (`nom_produit`, `description`,`img_produit`,`valeur`,`id_user`, `id_categorie`) VALUES ('" + p.getNom_produit() + "', '" + p.getDescription() + "', '" + p.getImg_produit() + "', '" + p.getValeur() + "', '" + p.getId_user()+ "', '" + p.getId_categorie()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
     public void ajouter2(Produit p) {
        try {
            String req = "INSERT INTO `personne` (`nom_produit`, `description`,`img_produit`,`valeur`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNom_produit());
            ps.setString(2, p.getDescprition());
            ps.setString(3, p.getImg_produit());
            ps.setString(4, p.getValeur());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `produit` WHERE id_produit = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(Produit p) {
        try {
            String req = "UPDATE `produit` SET `nom` = '" + p.getNom_produit() + "', `description` = '" + p.getDescription() +"', `img_produit` = '" + p.getImg_produit() +"', `valeur` = '" + p.getValeur() + "' WHERE `produit`.`id_produit` = " + p.getId_produit();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public List<Produit> getAll() {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from personne";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit p = new Produit(rs.getString("nom_produit"), rs.getString("description"), rs.getString("img_produit"), rs.getString("valeur"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public Produit getOneById(int id_produit) {
        Produit p = null;
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                 p = new Produit(rs.getString("nom_produit"), rs.getString("description"), rs.getString("img_produit"), rs.getString("valeur"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }
    
}