/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.entities.Reponse;
import edu.fasty.entities.User;
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
 * @author FERJANI
 */
public class ServiceReponse implements RService{
Connection con = DataSource.getInstance().getCnx();
   @Override
    public void ajouterReponse(Reponse r) {
       try {
    String req = "INSERT INTO `reponse` (`id_user`,`id_forum`,`contenu`,`id_question`) VALUES (?,?,?,?)";
    PreparedStatement ps = con.prepareStatement(req);
    ps.setInt(1, r.getIduser().getId_user());
    ps.setInt(2, r.getForumId().getId_forum());
    ps.setString(3, r.getContenu());
    ps.setInt(4, r.getQuestionId().getId_question());
    ps.executeUpdate();
    System.out.println("Response Successfully Created !");
} catch (SQLException ex) {
    System.err.println(ex.getMessage());
}

    }

    @Override
    public void supprimerReponse(int id) {
try {
            String req = "DELETE FROM `reponse` WHERE id_reponse ="+id;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse Successfully Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }     }

    @Override
    public void modifierReponse(Reponse r) {
try {
            String req = "UPDATE `reponse` SET `contenu` = '"+r.getContenu()+"' WHERE `reponse`.`id_reponse` = "+r.getId_reponse();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Response Successfully Updated !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Reponse> getAllReponses() {
IServiceUser su = new IServiceUser();
List<Reponse> reponses = new ArrayList<>();
         try {
            String req = "SELECT * from reponse";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                ServiceForum sf = new ServiceForum();
                ServiceQuestion sq = new ServiceQuestion();
                Forum f =  sf.getForumById(rs.getInt(3));
                Question q = sq.getQuestionById(rs.getInt(5));
                User u = su.getOneById(rs.getInt("id_user"));
                Reponse r = new Reponse(rs.getInt(2),f,rs.getString("contenu"),q,u);
                reponses.add(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return reponses;    }

    @Override
    public Reponse getReponseById(int id) {
        IServiceUser su = new IServiceUser();
Reponse r = null;
         try {
            String req = "SELECT * from Reponse WHERE id_reponse ="+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            if(rs.next()){
                ServiceForum sf = new ServiceForum();
                ServiceQuestion sq = new ServiceQuestion();
                Forum f =  sf.getForumById(rs.getInt(3));
                Question q = sq.getQuestionById(rs.getInt(5));
                User u = su.getOneById(rs.getInt("id_user"));
r = new Reponse(rs.getInt(2),f,rs.getString("contenu"),q,u);    
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return r;    }
    
    public List<Reponse> getAllReponsesByQuestion(int id) {
        IServiceUser su = new IServiceUser();
List<Reponse> reponses = new ArrayList<>();
         try {
            String req = "SELECT * from reponse WHERE id_question ="+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                ServiceForum sf = new ServiceForum();
                ServiceQuestion sq = new ServiceQuestion();
                Forum f =  sf.getForumById(rs.getInt(3));
                Question q = sq.getQuestionById(rs.getInt(5));
                User u = su.getOneById(rs.getInt("id_user"));
                Reponse r = new Reponse(rs.getInt(1),f,rs.getString("contenu"),q,u);
                reponses.add(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return reponses;    }

    
}
