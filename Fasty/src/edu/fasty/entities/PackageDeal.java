
package edu.fasty.entities;

/**
 *
 * @author IHEB
 */
public class PackageDeal {
    private int id_package  ;
    private int id_event  ;
    private String listeproduit  ;

    public int getId_package() {
        return id_package;
    }

    public void setId_package(int id_package) {
        this.id_package = id_package;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getListeproduit() {
        return listeproduit;
    }

    public void setListeproduit(String listeproduit) {
        this.listeproduit = listeproduit;
    }

    public PackageDeal() {
    }

    public PackageDeal(int id_package, int id_event, String listeproduit) {
        this.id_package = id_package;
        this.id_event = id_event;
        this.listeproduit = listeproduit;
    }

    public PackageDeal(int id_event, String listeproduit) {
        this.id_event = id_event;
        this.listeproduit = listeproduit;
    }

    @Override
    public String toString() {
        return "PackageDeal{" + "id_package=" + id_package + ", id_event=" + id_event + ", listeproduit=" + listeproduit + '}';
    }
    
    
}
