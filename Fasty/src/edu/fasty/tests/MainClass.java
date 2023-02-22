/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.tests;

import edu.fasty.entities.Bid;
import edu.fasty.entities.Event;
import edu.fasty.entities.Packagedeal;
import edu.fasty.services.ServiceBid;
import edu.fasty.services.ServiceEvent;
import edu.fasty.services.ServicePackage;
import edu.fasty.utils.DataSource;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author IHEB
 */
public class MainClass {
        public static void main(String[] args) {
             LocalDate myObj = LocalDate.now();
   // Event ne= new Event(myObj,1,"hi",true);
    //Event ne2= new Event(myObj,1,"hiau^datezeaze",false);
    //System.out.print(ne);
    //Connection cnx = DataSource.getInstance().getCnx();
     //ServiceEvent se = new ServiceEvent();
     //System.out.prise.getMyEvents(1);
    // se.ajouter(ne);
     //se.ajouter(ne2);

     //System.out.print(se.getAll());
     //System.out.print(se.getOneById(5));
     // se.supprimer(4);
     //se.modifier(ne);
     //Packagedeal pkg1= new Packagedeal(5,"2","23","44");
     //ServicePackage se1 = new ServicePackage();
     //se1.ajouter(pkg1);
    // Bid bid1=new Bid(13,2,42,1);
     //ServiceBid bidmannager=new ServiceBid();
     //bidmannager.ajouter(bid1);
     //System.out.print(bidmannager.getOneById(5));
    // bidmannager.supprimer(5);

}
}