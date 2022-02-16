/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao;

import com.daoebenus.ebenus.dao.entities.Produit;
import java.util.List;

/**
 *
 * @author Quentin
 */
public interface IProduitDao {
    
    public List<Produit> findAllProduits();

    public Produit findProduitById (int idProduit);
    
    public List<Produit> findProduitByNom(String nomProduit);
    
    public Produit findProduitByReference(String reference);
    
    public Produit createProduit(Produit produit);
    
    public Produit updateProduit(Produit produit);
    
    public boolean deleteProduit(Produit produit);
}
