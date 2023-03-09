/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import java.util.List;

/**
 *
 * @author MSI GAMING
 */
public interface IService <T>{
    public void ajouter(T u);
    public void supprimer(int id);
    public void modifier(T u);
    public List<T> getAll();
    public T getOneById(int id);
    public T RechercherUserparEmailMdp(String email,String mdp);
    public boolean UserExiste(String email);
    
}
