/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import java.util.List;

/**
 *
 * @author IHEB
 * @param <B>
 */

    
    
    
    public interface IBid <B>{
    public void ajouter(B E);
    public void supprimer(int id);
    public void modifier(B E);
    public List<B> getAll();
    public B getOneById(int id);
}


    
