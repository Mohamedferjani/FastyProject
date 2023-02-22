/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.tests;

import edu.fasty.entities.Produit;
import edu.fasty.services.ServiceProduit;
import edu.fasty.utils.DataSource;
/**
 *
 * @author Hp
 */

public class MainClass {
    public static void main(String[] args) {
        
        Produit p1 = new Produit(1,1,"3","aaa", "M","tdtyd");
        
        ServiceProduit sp = new ServiceProduit();
        sp.ajouter(p1);
    
}  
}