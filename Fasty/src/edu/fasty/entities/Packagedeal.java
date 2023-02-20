/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.entities;

import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author IHEB
 */
public class Packagedeal {
    private int id_package;
    private int id_event;
    private int Product1;
    private int Product2;
    private int Product3;

    public Packagedeal() {
    }

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

    public int getProduct1() {
        return Product1;
    }

    public void setProduct1(int Product1) {
        this.Product1 = Product1;
    }

    public int getProduct2() {
        return Product2;
    }

    public void setProduct2(int Product2) {
        this.Product2 = Product2;
    }

    public int getProduct3() {
        return Product3;
    }

    public void setProduct3(int Product3) {
        this.Product3 = Product3;
    }

    public Packagedeal(int Product1, int Product2, int Product3) {
        this.Product1 = Product1;
        this.Product2 = Product2;
        this.Product3 = Product3;
    }

    public Packagedeal(int Product1, int Product2) {
        this.Product1 = Product1;
        this.Product2 = Product2;
    }

    @Override
    public String toString() {
        return "Packagedeal{" + "id_event=" + id_event + ", Product1=" + Product1 + ", Product2=" + Product2 + ", Product3=" + Product3 + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id_package;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Packagedeal other = (Packagedeal) obj;
        if (this.id_package != other.id_package) {
            return false;
        }
        return true;
    }

    public Packagedeal(int id_package, int id_event, int Product1, int Product2, int Product3) {
        this.id_package = id_package;
        this.id_event = id_event;
        this.Product1 = Product1;
        this.Product2 = Product2;
        this.Product3 = Product3;
    }

    public Packagedeal(int id_event, int Product1, int Product2, int Product3) {
        this.id_event = id_event;
        this.Product1 = Product1;
        this.Product2 = Product2;
        this.Product3 = Product3;
    }
    
    
    

}