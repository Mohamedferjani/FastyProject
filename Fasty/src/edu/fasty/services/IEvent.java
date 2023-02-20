/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.services;

import java.util.List;

/**
 *
 * @author IHEB
 */

    public interface IEvent <T>{
    public void ajouter(T E);
    public void supprimer(int id);
    public void modifier(T E);
    public List<T> getAll();
    public T getOneById(int id);
}

