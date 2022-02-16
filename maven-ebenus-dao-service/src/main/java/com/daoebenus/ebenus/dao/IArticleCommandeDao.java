/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao;

import com.daoebenus.ebenus.dao.entities.ArticleCommande;
import java.util.List;

/**
 *
 * @author Quentin
 */
public interface IArticleCommandeDao {
    public List<ArticleCommande> findAllArticleCommandes();
    
    public ArticleCommande findArticleCommandeById(int idArticleCommande);
    
    public List<ArticleCommande> findArticleCommandeByUtilisateurId(int idUtilisateur);
    
    public List<ArticleCommande> findArticleCommandeByCommandeId(int idCommande);
    
    public List<ArticleCommande> findArticleCommandeByAdresseId(int idAdresse);
    
    public List<ArticleCommande> findArticleCommandeByProduitId(int idProduit);
    
    public ArticleCommande createArticleCommande (ArticleCommande articleCommande);

    public ArticleCommande updateArticleCommande (ArticleCommande articleCommande);
    
    public boolean deleteArticleCommande (ArticleCommande articleCommande);
}
