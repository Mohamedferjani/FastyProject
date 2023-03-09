/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.fasty.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Homrani
 */
public interface IPService<T> {
    public void ajouter(T t);
    public List<T> afficher() throws SQLException;
    public void supprimer(int id) throws SQLException;
    public void modifier(int id, T t) throws SQLException;
    public T getCategorieByid(int id);
    public T getCategorieByNom(String nom);
    }
