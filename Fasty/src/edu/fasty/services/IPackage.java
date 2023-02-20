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
 * @param <P>
 */
public interface IPackage <P> {
   

  
    public void ajouter(P E);
    public void supprimer(int id);
    public void modifier(P E);
    public List<P> getAll();
    public P getOneById(int id);
}

 

