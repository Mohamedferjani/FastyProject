/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.FacebookType;
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
import javafx.scene.control.Alert;

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

    @Override
    public void PostQuestionOnFacebook(Question q) {
      String accessToken = "EAADELBJHovIBAPZCZA5Ce7OWKBgoQ7ueqcPaVlpKUrNSBgcM8LWn0G7JjjlMV2ROdxw1pSZCpzhMTjiRQZCa0PltDY0Ji0C8Uxubv2CzZB172oe5kVW4LRjZAWW8qcsB1vZBBW68AVLxSzwAZBLhw4PiORdbqD4GpEtigI26NxAngXToNbIT9EbVRjVftEUYlnY6PpOpmLcRDrUpxZAZCCAM1S";
        String pageId = "102900889410694"; // replace with your page ID
        String message = q.getContenu();
        FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);

        try {
            FacebookType result = fbClient.publish(pageId + "/feed", FacebookType.class,
                    Parameter.with("message", message));
            System.out.println("Post published on page: " + result.getId());
             Alert ok=new Alert(Alert.AlertType.INFORMATION);
               ok.setTitle("DONE");
               ok.setHeaderText("Question successfully published on your Facebook Page !");
               ok.show();
        } catch (FacebookOAuthException ex) {
            System.err.println("Failed to post on page: " + ex.getMessage());
            Alert ok=new Alert(Alert.AlertType.ERROR);
               ok.setTitle("Error");
               ok.setHeaderText("There is an error while publishing...");
               ok.show();
        }
    }
}
