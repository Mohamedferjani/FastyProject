/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.services;

import java.util.List;

/**
 *
 * @author ISSAM
 */
public interface InterfaceTransaction <T> {
    
    public void ajouterTransaction(T p);
    public boolean supprimerTransaction(int id);
    public void modifierTransaction(T p);
    public List<T> getAllTransaction();
    public T getOneByIdT(int id);
        

    
}
