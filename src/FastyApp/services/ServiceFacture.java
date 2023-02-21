/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.services;

import FastyApp.entities.Facture;
import FastyApp.utils.DataSource;
import java.sql.Statement;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ISSAM
 */
public class ServiceFacture implements InterfaceFacture<Facture>{

      Connection connect = DataSource.getInstance().getCnx();
    @Override
    public void ajouterFacture(Facture p) {
        try {
            String req = "INSERT INTO `facture` (`id_facture`, `montant`, `id_prop`, `id_client`, `id_produit` ) VALUES ('" + p.getId_facture() + "' , '" + p.getMontant()+  "' , '" + p.getId_prop()+ "' , '" + p.getId_client()+ "' , '" + p.getId_produit() + "')";
        
            Statement s = connect.createStatement();
            s.executeUpdate(req);
            System.out.println("Facture Created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
    }

    @Override
    public void supprimerFacture(int id) {
         try {
            String req = "DELETE FROM `facture` WHERE  id_facture = " + id;
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Facture Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierFacture(Facture p) {
            try {
            String req = "UPDATE `facture` SET `montant`= '"+ p.getMontant() + "', `id_prop`= '"+p.getId_prop() +"', `id_client`= '" + p.getId_client() + "' ,`id_produit`= '" + p.getId_produit() +"' WHERE id_facture = '" + p.getId_facture() + "'";
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Facture> getAllFacture() {
        List<Facture> list = new ArrayList<>();
        try {
            String req = "Select * from facture";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Facture f = new Facture (rs.getInt(1), rs.getString("montant"), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                list.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

    @Override
    public Facture getOneByIdF(int id) {
         Facture f = null;
        try {
            String req = "Select * from facture";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                  if (rs.getInt(1)==id){
            f = new Facture(rs.getInt(1), rs.getString("montant"), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                  }
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return f;
       
    }
    
}
