/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Question;
import java.util.List;

/**
 *
 * @author FERJANI
 */
public interface QService {
       public void ajouterQuestion(Question q);
    public void supprimerQuestion(int id);
    public void modifierQuestion(Question q);
    public List<Question> getAllQuestions();
    public List<Question> getAllQuestionsById(int id);
    public List<Question> getALLQuestionByUser(int id);
    public Question getQuestionById(int id);
}
