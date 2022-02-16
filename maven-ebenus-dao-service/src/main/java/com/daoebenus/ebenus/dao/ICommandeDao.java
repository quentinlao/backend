/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao;

import com.daoebenus.ebenus.dao.entities.Commande;
import java.util.List;

/**
 *
 * @author Quentin
 */
public interface ICommandeDao {
    
    public List<Commande> findAllCommandes();

    public Commande findCommandeById(int idCommande);
    
    public List<Commande> findCommandeByProduitId(int idProduit);
    
    public List<Commande> findCommandeByProduitNom(String nomProduit);
    
    public List<Commande> findCommandeByProduitReference(String referenceProduit);
    
    public List<Commande> findCommandeByUtilisateurId(int idUtilisateur);
    
    public List<Commande> findCommmandeByUtilisateurIdentifiant(String identifiantUtilisateur);
    
    public Commande createCommande(Commande commande);

    public Commande updateCommande(Commande commande);

    public boolean deleteCommande(Commande commande);
    
}
