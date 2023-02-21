/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Forum;
import edu.fasty.entities.Question;
import edu.fasty.entities.Reponse;
import java.util.List;

/**
 *
 * @author FERJANI
 */
public interface FService {
    public boolean ajouterForum(Forum f);
    public void supprimerForum(int id);
    public void modifierForum(Forum f);
    public List<Forum> getAllForums();
    public Forum getForumById(int id);
    public void ajouterQuestion(Question q);
    public void supprimerQuestion(int id);
    public void modifierQuestion(Question q);
    public List<Question> getAllQuestions();
    public Question getQuestionById(int id);
    public void ajouterReponse(Reponse r);
    public void supprimerReponse(int id);
    public void modifierReponse(Reponse r);
    public List<Reponse> getAllReponses();
    public Reponse getReponseById(int id);
}
