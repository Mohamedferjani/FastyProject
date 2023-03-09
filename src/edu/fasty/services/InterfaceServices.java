
package edu.fasty.services;

import java.util.List;


public interface InterfaceServices<T> {
    
    public void ajouter(T p);
    public void supprimer(int id);
    public void modifier(int id,T p);
    public List<T> getAll();
    public T getOneByID(int id);
    
}
