/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.tests;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.entities.Reponse;
import edu.fasty.services.ServiceForum;
import edu.fasty.utils.DataSource;
import java.util.List;
import javax.xml.ws.Response;

/**
 *
 * @author FERJANI
 */
public class MainClass {
    public static void main(String[] args) {
           // add a new forum
        Forum forum1 = new Forum("A forum for discussing topics","");
        Forum forum2 = new Forum("aaaa","");
        Forum forum3 = new Forum(4,"testing","");
        ServiceForum sf = new ServiceForum();
       sf.ajouterForum(forum1);
       sf.ajouterForum(forum2);
       sf.ajouterForum(forum3);
       
        forum2.setTitre("Testing the update");
        sf.modifierForum(forum2);
        //sf.supprimerForum(8);
        List<Forum> forums = sf.getAllForums();
        for (Forum f : forums) {
            System.out.println(f);
        }
//        Forum a = sf.getForumById(7);
//        System.out.println(a.getTitre());
       Question question = new Question(1,forum2.getId_forum(), "How do I use this example?");
//        sf.ajouterQuestion(question);
         Reponse response = new Reponse(1,forum2.getId_forum(),"You can start by reading the documentation or following tutorials online",question.getId_question());
      //  sf.ajouterReponse(response);
         Question question2 = new Question(2,forum2.getId_forum(), "How you doing today?");
           //   sf.ajouterQuestion(question2);
           
         Reponse response2 = new Reponse(2,forum2.getId_forum(),"Im doing fine , thanks for asking !",question2.getId_question());
       //  sf.ajouterReponse(response2);
         
          //Question q1 = sf.getQuestionById(2);
   //   System.out.println(q1.getContenu());
      // Reponse r1 = sf.getReponseById(2);
   //   System.out.println(r1.getContenu());
            
            //question.setContenu("Is this update working properly?");
           // sf.modifierQuestion(question);
           // response.setContenu("Yes , its working perfectly !");
          //  sf.modifierReponse(response);
            
//List<Question> questions = sf.getAllQuestions();
//            for (Question q : questions){
//                System.out.println(q);}
//            List<Reponse> reponses = sf.getAllReponses();
//            for (Reponse r : reponses){
//                System.out.println(r);}
            

   
    }
}
