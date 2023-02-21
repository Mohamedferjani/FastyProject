package edu.geekerz.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 public class DataSource {
     
    //LES ATTRIBUTS 
    private Connection conn;
    private static DataSource instance;
    private final String USER="root";
    private final String PWD="";
    private final String URL="jdbc:mysql://localhost:3306/fasty";
    
    //PRIVATE CONSTRUCTOR
    private DataSource() {
        try{
            conn=DriverManager.getConnection(URL, USER, PWD);
            System.out.println("CONNECTED !");
        }catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    //SINGLETON QUI ASSURE LA CONNEXION SE FAIT UNE SEUL FOIS
    public static DataSource getInstance(){
        if(instance==null){
            instance=new DataSource();
        }
        return instance;
    }
    
    //GETTER CONN
    public Connection getConn() {
        return conn;
    }
     
    
}
