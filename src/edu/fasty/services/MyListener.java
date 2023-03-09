/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import edu.fasty.entities.Categorie;
import edu.fasty.entities.Produit;


public interface MyListener {
    
    public void onClickListener(Produit p);
    public void onClickListener2(Categorie c);
    
    

}
