/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.fasty.tests;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.fasty.entities.Categorie;
import edu.fasty.entities.Produit;
import edu.fasty.services.ServiceCategorie;
import edu.fasty.services.ServiceProduit;
import java.sql.SQLException;

/**
 *
 * @author Homrani
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
                /******************************************************************************* Categorie *********************************************************************/
        ServiceCategorie sCategorie= new ServiceCategorie();
        //Categorie categorie = new Categorie("aa","aa",1);


/******************************* Ajouter Categorie *********************************************/
//           sCategorie.ajouter(categorie);

/******************************* Afficher Categorie *********************************************/
 //           System.out.println(sCategorie.afficher());

/******************************* Supprimer Categorie *********************************************/
//            sCategorie.supprimer(2);

/******************************* Modifier Categorie *********************************************/
//            sCategorie.modifier(3, categorie);


                /******************************************************************************* Produit *********************************************************************/
        ServiceProduit sProduit= new ServiceProduit();
      //  Produit produit = new Produit("mm","mm","mm","mm",1,3);


/******************************* Ajouter Categorie *********************************************/
//           sProduit.ajouter(produit);

/******************************* Afficher Categorie *********************************************/
//           System.out.println(sProduit.afficher());

/******************************* Supprimer Categorie *********************************************/
//            sProduit.supprimer(2);

/******************************* Modifier Categorie *********************************************/
//            sProduit.modifier(1, produit);
    }
    
}
