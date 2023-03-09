package edu.fasty.tests;

import edu.fasty.entities.CategoryRating;
import edu.fasty.entities.Rating;
import edu.fasty.entities.Score;
import edu.fasty.entities.Wishlist;
import edu.fasty.services.ServiceCategoryRating;
import edu.fasty.services.ServiceRating;
import edu.fasty.services.ServiceScore;
import edu.fasty.services.ServiceWishlist;
import edu.fasty.utils.DataSource;
import java.sql.Date;
import java.util.ArrayList;

public class MainClass {
    
    public static void main (String[] args ){
        
        //TESTER LE SINGLETON
//        DataSource c1=DataSource.getInstance() ;
//        DataSource c2=DataSource.getInstance() ;
//        DataSource c3=DataSource.getInstance() ;
//        DataSource c4=DataSource.getInstance() ;

//        ArrayList<Integer> list_Produit = new ArrayList<>();
//        list_Produit.add(5);
//        list_Produit.add(10);
//        list_Produit.add(33);
//        ServiceWishlist sw=new ServiceWishlist();
//        Wishlist w1=new Wishlist(3,list_Produit);
//        sw.ajouter(w1);
//        System.out.println(sw.getAll());
//        System.out.println(sw.getOneByID(3));
//        Wishlist w2=new Wishlist(2,"Wishlist1modifier","1,2,3modifier","1999-02-02");
        //sw.modifier(1, w2);
       // System.out.println(sw.getOneByName("Wishlist1modifier"));
        
        
        
        //ServiceRating sr=new ServiceRating();
        //System.out.println(sr.getOneByID(3));
        
//          sr.ajouter(r1);
//          sr.ajouter(r2);
//          sr.ajouter(r3);

//          r.supprimer(1);

//         sr.modifier(6, r4);

//           System.out.println(sr.getOneByID(5));
         //System.out.println(sr.getAll());
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        Score s1=new Score(1,111,"good","2023-02-16");
//        Score s2=new Score(2,222,"bad","2023-02-16");  
//        Score s3=new Score(3,333,"nutral","2023-02-16");
//        Score s4=new Score(4,444,"very good","2023-02-16");
//        
//        ServiceScore ss=new ServiceScore();
//        
//        Score s5=new Score(4,555,"very 5 good","2023-02-16");
//        
//        ss.ajouter(s1);
//        ss.ajouter(s2);
//        ss.ajouter(s3);
//        ss.ajouter(s4);
        
//        ss.supprimer(1);
        
//        ss.modifier(7, s5);

//        System.out.println(ss.getOneByID(5));
//        System.out.println(ss.getAll());
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        CategoryRating cr1=new CategoryRating("Electronics & Gadgets","Rating for electronics and gadgets","E & G LOGO");
        
//        CategoryRating cr2=new CategoryRating("Books & Magazines","Rating for books and magazines","B & M LOGO");
//        cr2.setId_Cat_Rating(2);
//        Rating r5=new Rating(4,44,"2022-02-22","commentaire4",cr2);
//        CategoryRating cr3=new CategoryRating("Toys & Games","Rating for toys and games","T & Ga LOGO");
//        CategoryRating cr4=new CategoryRating("Jewelry & Watches","Rating for jewelry & watches","J & W LOGO");
//        Rating r1=new Rating(1,11,"2022-02-22","commentaire1",cr3);
//        Rating r2=new Rating(2,22,"2022-02-22","commentaire2",cr4);
//        Rating r3=new Rating(3,33,"2022-02-22","commentaire3",cr2);
//        Rating r4=new Rating(4,44,"2022-02-22","commentaire4",cr1);
//        ServiceCategoryRating scr=new ServiceCategoryRating();
//        
//        scr.ajouter(cr1);
//        scr.ajouter(cr2);
//        scr.ajouter(cr3);
//        scr.ajouter(cr4);
//        sr.ajouter(r1);
//        sr.ajouter(r2);
//        sr.ajouter(r3);
//        sr.ajouter(r4);
        //sr.ajouter(r5);
        
        //cr4.setDescription_Cat_Rating("My new description");
        //scr.modifier(4, cr4);
        //scr.supprimer(7);
        //System.out.println(scr.getOneByID(1));
        //System.out.println(scr.getAll());
        




    }
    
    
    
    
    
    
    
 
}
