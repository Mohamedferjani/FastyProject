/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.tests;


/**
 *
 * @author ISSAM
 */

import FastyApp.utils.DataSource;
import FastyApp.entities.Facture;
import FastyApp.entities.Transaction;
import FastyApp.services.ServiceFacture;
import FastyApp.services.ServiceTransaction;


public class MainClass {
    
    public static void main (String[] args ){
        
        //DataSource data = new DataSource();
        
        Facture f1 = new Facture( "5168" , 2, 1,651);
  //      Transaction t1 = new Transaction(1,1,2,651);
        
//       
//        
//        
        ServiceFacture sf = new ServiceFacture();
        ServiceTransaction st = new ServiceTransaction();
        Transaction tt = st.getOneByIdT(1);
        System.out.println(tt.toString());
     // st.ajouterTransaction(t1);
     /*t1.setId_produit(123);
       st.modifierTransaction(t1);*/
     //sf.ajouterFacture(f1);
     //sf.supprimerFacture(3);
     //f1.setMontant("3000");
     //sf.modifierFacture(f1);
     
        
        
    }
    
    
    
    
    
}
