/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.services;

import FastyApp.entities.Produit;

import FastyApp.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author ISSAM
 */
public class ServiceProduit implements InterfaceProduit<Produit> {

    Connection connect = DataSource.getInstance().getCnx();

    @Override
    public double getMontantById(int pId) {
        double value = 0.0;
        try {
            String req = "SELECT p.valeur FROM produit p WHERE p.id_produit = " + pId;
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                value = Double.parseDouble(rs.getString("valeur"));
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
            Statement st = connect.createStatement();
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
