/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
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
public class ServiceQuestion implements QService{
Connection con = DataSource.getInstance().getCnx();
     @Override
    public void ajouterQuestion(Question q) {
        int iduser=15;
        try {
            String req = "INSERT INTO `question` (`id_user`,`id_forum`,`contenu`) VALUES ('"+iduser+"','"+q.getForumId().getId_forum()+"','"+q.getContenu()+"')";
            PreparedStatement ps = con.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("Forum Successfully Created !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerQuestion(int id) {
           try {
            String req = "DELETE FROM `question` WHERE id_question ="+id;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Question Successfully Deleted !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifierQuestion(Question q) {
        try {
            String req = "UPDATE `question` SET `contenu` = '"+q.getContenu()+"' WHERE `question`.`id_question` = "+q.getId_question();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Question Successfully Updated !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
         try {
            String req = "SELECT * from question";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                ServiceForum sf = new ServiceForum();
              Forum f =  sf.getForumById(rs.getInt(1));
                Question q = new Question(f,rs.getString("contenu"),rs.getInt("id_user"));
                questions.add(q);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return questions;    }

    @Override
    public Question getQuestionById(int id) {
Question q = null;
         try {
            String req = "SELECT * from question WHERE id_question ="+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            if(rs.next()){
                ServiceForum sf = new ServiceForum();
                Forum f =  sf.getForumById(rs.getInt(1));
            q = new Question(rs.getInt("id_question"),f,rs.getString("contenu"),rs.getInt("id_user"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return q;
    }
     @Override
    public List<Question> getAllQuestionsById(int id) {
         List<Question> questions = new ArrayList<>();
         try {
            String req = "SELECT * from question WHERE id_forum ="+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                ServiceForum sf = new ServiceForum();
                Forum f =  sf.getForumById(rs.getInt(1));
                Question q = new Question(rs.getInt("id_question"),f,rs.getString("contenu"),rs.getInt("id_user"));
                questions.add(q);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return questions; 
    }

    @Override
    public List<Question> getALLQuestionByUser(int id) {
        List<Question> questions = new ArrayList<>();
         try {
            String req = "SELECT * from question WHERE id_user ="+id;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                ServiceForum sf = new ServiceForum();
                Forum f =  sf.getForumById(rs.getInt(2));
                Question q = new Question(rs.getInt("id_question"),f,rs.getString("contenu"),rs.getInt("id_user"));
                questions.add(q);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return questions; 
    }
}
