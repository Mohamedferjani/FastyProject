/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Reponse;
import java.util.List;

/**
 *
 * @author FERJANI
 */
public interface RService {
     public void ajouterReponse(Reponse r);
    public void supprimerReponse(int id);
    public void modifierReponse(Reponse r);
    public List<Reponse> getAllReponses();
    public Reponse getReponseById(int id);
}
