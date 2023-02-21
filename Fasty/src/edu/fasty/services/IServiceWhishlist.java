/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Whishlist;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MSI GAMING
 */
/*public class IServiceWhishlist implements IService<Whishlist> {

    public void ajouterW(Whishlist w) {
                   try {
            String req = "INSERT INTO `whishlist` (`id_whislist`, `list_produit`,`id_user`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, w.getList_produit());
            ps.setInt(1, w.getId_whislist());
            ps.setInt(3, w.getId_user());

            ps.executeUpdate();
            System.out.println("Tzed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Whishlist u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Whishlist> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Whishlist getOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    

