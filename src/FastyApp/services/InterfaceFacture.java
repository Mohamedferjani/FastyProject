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
public interface InterfaceFacture <F>
{
    
    
    public void ajouterFacture(F p);
    public void supprimerFacture(int id);
    public void modifierFacture(F p);
    public List<F> getAllFacture();
    public F getOneByIdF(int id);
}
