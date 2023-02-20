/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Event;
import edu.fasty.entities.Packagedeal;
import edu.fasty.services.IPackage;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author IHEB
 */
public class ServicePackage implements IPackage <Packagedeal>{
            Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Packagedeal P) {

     try {
            String req = "INSERT INTO `packagedeal` (`id_event`, `product1`,`product2`,`product3`) VALUES (?,?,?,?)";
            //String req2= "SELECT id_user FROM `user` WHERE email = 'irezgui@gmail.com'";
            //PreparedStatement ps2 = cnx.prepareStatement(req2);
            PreparedStatement ps = cnx.prepareStatement(req);
            
            
            ps.setInt(1,P.getId_event());
            ps.setInt(2,P.getProduct1());
            ps.setInt(3,P.getProduct2());
            ps.setInt(4,P.getProduct3());

            //    ResultSet resultSet=ps2.executeQuery();
//             int userId = 0;
//                if (resultSet.next()) {
//                    userId = resultSet.getInt(1);
//                }
              //System.out.print(userId);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void supprimer(int id) {
try {
            String req = "DELETE FROM `packagedeal` WHERE id_package = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("pkg deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Packagedeal E) {

           try {
            String req = "UPDATE `packagedeal` SET `product1` = '" +E.getProduct1()+ "', `product2` = '" + E.getProduct2() + "',`product3` ='"+E.getProduct3() +"' WHERE `packagedeal`.`id_package` = " + E.getId_package();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("EVENT UPDATED updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public List<Packagedeal> getAll() {
        List<Packagedeal> list = new ArrayList<>();
          
        try {
            String req = "Select * from packagedeal";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Packagedeal Ev = new Packagedeal(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
                list.add(Ev);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
        


    }

    @Override
    public Packagedeal getOneById(int id) {
        Packagedeal pkg=null;
             try {
            String req = "Select * from packagedeal where id_package = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                pkg = new Packagedeal(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return pkg;
    }



    }



