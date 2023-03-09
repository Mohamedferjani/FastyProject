/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fasty.services;

import edu.fasty.entities.PackageDeal;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author IHEB
 */
public class ServicePackageDeal implements IEService<PackageDeal>{
    
    Connection cnx;
    Statement st;
    public ServicePackageDeal (){
        cnx = DataSource.getInstance().getCnx();}

    @Override
    public void ajouter(PackageDeal packageDeal) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `packagedeal`(`id_event`, `listeproduit`) VALUES ('"+packageDeal.getId_event()+"','"+packageDeal.getListeproduit()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
    }

    @Override
    public List<PackageDeal> afficher() throws SQLException {
        List<PackageDeal> lpackageDeal = new ArrayList<PackageDeal>();
        st = cnx.createStatement();       
        String query = "SELECT * FROM packagedeal";
        ResultSet rs= st.executeQuery(query);
        while (rs.next()){
        PackageDeal packageDeal = new PackageDeal();
        packageDeal.setId_package(rs.getInt("id_package"));
        packageDeal.setId_event(rs.getInt("id_event"));
        packageDeal.setListeproduit(rs.getString("listeproduit"));
        lpackageDeal.add(packageDeal);}        
        return lpackageDeal;
    }

    @Override
    public void supprimer(int id_package) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from packagedeal where id_package =" + id_package ;
        stm.executeUpdate(query);
    }
    
    public PackageDeal SearchById(long id_package) throws SQLException{
        Statement stm = cnx.createStatement();
        PackageDeal packageDeal = new PackageDeal();
        String query = "select * from packagedeal where id_package="+id_package;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        packageDeal.setId_package(rs.getInt("id_package"));
        packageDeal.setId_event(rs.getInt("id_event"));
        packageDeal.setListeproduit(rs.getString("listeproduit"));}
        return packageDeal;
     }

    @Override
    public void modifier(int id_PackageModifier, PackageDeal packageDeal) throws SQLException {
        Statement stm = cnx.createStatement();
        PackageDeal p =SearchById(id_PackageModifier);
        String query = "UPDATE `packagedeal` SET `id_event`='"+packageDeal.getId_event()+"',`listeproduit`='"+packageDeal.getListeproduit()+"' where id_package="+p.getId_package();
        stm.executeUpdate(query);
    }
    
}
