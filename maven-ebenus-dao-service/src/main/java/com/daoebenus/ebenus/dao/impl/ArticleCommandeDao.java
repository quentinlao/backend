/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.impl;

import com.daoebenus.ebenus.dao.ConnectionHelper;
import com.daoebenus.ebenus.dao.DriverManagerSingleton;
import com.daoebenus.ebenus.dao.IArticleCommandeDao;
import com.daoebenus.ebenus.dao.entities.Adresse;
import com.daoebenus.ebenus.dao.entities.ArticleCommande;
import com.daoebenus.ebenus.dao.entities.Commande;
import com.daoebenus.ebenus.dao.entities.Produit;
import com.daoebenus.ebenus.dao.entities.Utilisateur;
import com.daoebenus.ebenus.dao.exception.EbenusException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import static javafx.scene.input.KeyCode.R;

/**
 *
 * @author Quentin
 */
public class ArticleCommandeDao implements IArticleCommandeDao {

    private CommandeDao commandeDao = new CommandeDao(); 
    private UtilisateurDao utilisateurDao = new UtilisateurDao();
    private AdresseDao adresseDao = new AdresseDao();
    private ProduitDao produitDao = new ProduitDao();
    
    private ArticleCommande fillArticleCommande(ResultSet results, ArticleCommande articleCommande) {
        if (results != null && articleCommande != null) {
            try {
                articleCommande.setIdArticleCommande(results.getInt("idArticleCommande"));
                int idCommande = results.getInt("idCommande");
                Commande commande = commandeDao.findCommandeById(idCommande);              
                articleCommande.setCommande(commande);
                int idUtilisateur = results.getInt("idUtilisateur");
                Utilisateur utilisateur = utilisateurDao.findUtilisateurById(idUtilisateur);
                articleCommande.setUtilisateur(utilisateur);
               
                int idAdresse = results.getInt("idAdresse");
                Adresse adresse = adresseDao.findAdresseById(idAdresse);
                articleCommande.setAdresse(adresse);
                
                int idProduit = results.getInt("idProduit");
                Produit produit = produitDao.findProduitById(idProduit);
                articleCommande.setProduit(produit);
                
                articleCommande.setTotalArticleCommande(results.getDouble("totalArticleCommande"));
                articleCommande.setReference(results.getString("reference"));
                articleCommande.setQuantite(results.getInt("quantite"));
                articleCommande.setStatus(results.getString("statut"));
                articleCommande.setDateModification(results.getTimestamp("dateModification"));
                articleCommande.setVersion(results.getInt("version"));
            } catch (Exception e) {
                throw new EbenusException("échec lors de la récupération d'une adresse", e, 4);
            }
        }
        return articleCommande;

    }

    @Override
    public List<ArticleCommande> findAllArticleCommandes() {
        List<ArticleCommande> articleCommandeList = new ArrayList();
        ArticleCommande acToAdd = null;
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion;
        ResultSet results = null;
        try {
            sqlRequest = "SELECT * FROM articlecommande";
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                acToAdd = new ArticleCommande();
                acToAdd = fillArticleCommande(results, acToAdd);
                if (acToAdd != null) {
                    articleCommandeList.add(acToAdd);
                }
                   }
        } catch (Exception e) {
            throw new EbenusException("erreur lors du findAllArticleCommandes", 1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return articleCommandeList;
    }

    @Override
    public ArticleCommande findArticleCommandeById(int idArticleCommande) {
        ArticleCommande acToFind = null;
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion;
        ResultSet results = null;
        try {
            sqlRequest = "SELECT * FROM articlecommande WHERE idArticleCommande = ?";
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idArticleCommande);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                acToFind = new ArticleCommande();
                acToFind = fillArticleCommande(results, acToFind);
            }
        } catch (Exception e) {
            throw new EbenusException("erreur lors du findAllArticleCommandes", 1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return acToFind;
    }

    @Override
    public List<ArticleCommande> findArticleCommandeByUtilisateurId(int idUtilisateur) {
        List<ArticleCommande> articleCommandeList = new ArrayList();
        ArticleCommande acToAdd = null;
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion;
        ResultSet results = null;
        try {
            sqlRequest = "SELECT * FROM articlecommande WHERE idUtilisateur = ?";
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idUtilisateur);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                acToAdd = new ArticleCommande();
                acToAdd = fillArticleCommande(results, acToAdd);
                if (acToAdd != null) {
                    articleCommandeList.add(acToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("erreur lors du findAllArticleCommandes", 1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return articleCommandeList;
    }

    @Override
    public List<ArticleCommande> findArticleCommandeByCommandeId(int idCommande) {
        List<ArticleCommande> articleCommandeList = new ArrayList();
        ArticleCommande acToAdd = null;
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion;
        ResultSet results = null;
        try {
            sqlRequest = "SELECT * FROM articlecommande WHERE idCommande = ?";
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idCommande);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                acToAdd = new ArticleCommande();
                acToAdd = fillArticleCommande(results, acToAdd);
                if (acToAdd != null) {
                    articleCommandeList.add(acToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("erreur lors du findAllArticleCommandes", 1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return articleCommandeList;
    }

    @Override
    public List<ArticleCommande> findArticleCommandeByAdresseId(int idAdresse) {
        List<ArticleCommande> articleCommandeList = new ArrayList();
        ArticleCommande acToAdd = null;
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion;
        ResultSet results = null;
        try {
            sqlRequest = "SELECT * FROM articlecommande WHERE idAdresse= ?";
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idAdresse);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                acToAdd = new ArticleCommande();
                acToAdd = fillArticleCommande(results, acToAdd);
                if (acToAdd != null) {
                    articleCommandeList.add(acToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("erreur lors du findAllArticleCommandesByidAdresse", 1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return articleCommandeList;
    }
    
    @Override
    public List<ArticleCommande> findArticleCommandeByProduitId(int idProduit) {
        List<ArticleCommande> articleCommandeList = new ArrayList();
        ArticleCommande acToAdd = null;
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion;
        ResultSet results = null;
        try {
            sqlRequest = "SELECT * FROM articlecommande WHERE idProduit = ?";
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idProduit);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                acToAdd = new ArticleCommande();
                acToAdd = fillArticleCommande(results, acToAdd);
                if (acToAdd != null) {
                    articleCommandeList.add(acToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("erreur lors du findAllArticleCommandesByProduitId", 1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return articleCommandeList;
    }

    private boolean articleCommandeHaveId(ArticleCommande articleCommande) {
        boolean haveId = false;
        if (articleCommande != null
                && articleCommande.getIdArticleCommande() != null
                && articleCommande.getIdArticleCommande() > 0) {
            haveId = true;
        }
        return haveId;
    }
    
    @Override
    public ArticleCommande createArticleCommande(ArticleCommande articleCommande) {
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion = null;
        ResultSet results = null;
        if (!articleCommandeHaveId(articleCommande)) {
            sqlRequest = "INSERT INTO articlecommande (idCommande, idUtilisateur, idAdresse, idProduit, totalArticleCommande, reference, quantite, statut, dateModification)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            connexion = DriverManagerSingleton.getConnectionInstance();
            try {
                preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS);
                if (articleCommande != null) {
                    if (articleCommande.getCommande() != null) {
                        if (articleCommande.getCommande().getIdCommande()> 0) {
                            preparedStatement.setInt(1, articleCommande.getCommande().getIdCommande());
                        }
                    }
                    if (articleCommande.getUtilisateur() != null) {
                        if (articleCommande.getUtilisateur().getIdUtilisateur() > 0) {
                            preparedStatement.setInt(2, articleCommande.getUtilisateur().getIdUtilisateur());
                        }
                    }
                    if (articleCommande.getAdresse() != null) {
                        if (articleCommande.getAdresse().getIdAdresse() > 0) {
                            preparedStatement.setInt(3, articleCommande.getAdresse().getIdAdresse());
                        }
                    }
                    if (articleCommande.getProduit() != null) {
                        if (articleCommande.getProduit().getIdProduit() != null) {
                            preparedStatement.setInt(4, articleCommande.getProduit().getIdProduit());
                        }
                    }
                    if (articleCommande.getTotalArticleCommande() > 0.0) {
                        preparedStatement.setDouble(5, articleCommande.getTotalArticleCommande());
                    }
                    if (articleCommande.getReference() != null) {
                        preparedStatement.setString(6, articleCommande.getReference());
                    }
                    if (articleCommande.getQuantite() > 0) {
                        preparedStatement.setInt(7, articleCommande.getQuantite());
                    }
                    if (articleCommande.getStatus() != null) {
                        preparedStatement.setString(8, articleCommande.getStatus());
                    }
                    java.sql.Timestamp nowSql = new Timestamp(System.currentTimeMillis());
                    java.util.Date now = new java.util.Date(nowSql.getTime());
                    preparedStatement.setTimestamp(9, nowSql);
                    preparedStatement.executeUpdate();
                    results = preparedStatement.getGeneratedKeys(); //permet de récupérer l'idArticleCommande pour le return
                    if (results.next()) {
                        articleCommande.setIdArticleCommande((int) results.getLong(1));
                        articleCommande.setDateModification(now);
                    }
                }
            } catch (Exception e) {
                throw new EbenusException("l'ajout d'un articleCommande a échoué", e, -1);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, null);
            }
        }
        return articleCommande;
    }

    @Override
    public ArticleCommande updateArticleCommande(ArticleCommande articleCommande) {
        String sqlRequest = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        if (articleCommandeHaveId(articleCommande)) {
            try {
                connexion = DriverManagerSingleton.getConnectionInstance();
                sqlRequest = "UPDATE articlecommande SET idCommande = ?, idUtilisateur = ?, idProduit = ?, totalArticleCommande = ?, reference = ?, quantite = ?, statut = ?, dateModification = ? WHERE idArticleCommande = ?";
                System.err.println(articleCommande);
                preparedStatement = connexion.prepareStatement(sqlRequest);
                if (articleCommande.getCommande() != null && articleCommande.getCommande().getIdCommande() != null)
                    preparedStatement.setInt(1, articleCommande.getCommande().getIdCommande());
                if (articleCommande.getUtilisateur() != null && articleCommande.getUtilisateur().getIdUtilisateur() != null)
                    preparedStatement.setInt(2, articleCommande.getUtilisateur().getIdUtilisateur());
                if (articleCommande.getAdresse() != null && articleCommande.getAdresse().getIdAdresse() != null)
                    preparedStatement.setInt(3, articleCommande.getAdresse().getIdAdresse());
                if (articleCommande.getTotalArticleCommande() >= 0.0)
                    preparedStatement.setDouble(4, articleCommande.getTotalArticleCommande());
                if (articleCommande.getReference() != null)
                    preparedStatement.setString(5, articleCommande.getReference());
                if (articleCommande.getQuantite() != null)
                    preparedStatement.setInt(6, articleCommande.getQuantite());
                if (articleCommande.getStatus() != null) {
                    preparedStatement.setString(7, articleCommande.getStatus());
                }
                java.sql.Timestamp nowSql = new Timestamp(System.currentTimeMillis());
                java.util.Date now = new Date(nowSql.getTime());
                preparedStatement.setTimestamp(8, nowSql);
                preparedStatement.setInt(9, articleCommande.getIdArticleCommande());
                preparedStatement.executeUpdate();
                articleCommande.setDateModification(now);
            } catch (Exception e) {
                throw new EbenusException("la mise à jour de l'article commandé a échoué", e, 8);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, results);
            }
        }
        return articleCommande;
    }

    @Override
    public boolean deleteArticleCommande(ArticleCommande articleCommande) {
        Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "";
        boolean acDeleted = true;

        try {
            if (articleCommandeHaveId(articleCommande)) {
                sqlRequest = "DELETE FROM articlecommande WHERE idArticleCommande=?";
                connexion = DriverManagerSingleton.getConnectionInstance();
                preparedStatement = connexion.prepareStatement(sqlRequest);
                preparedStatement.setInt(1, articleCommande.getIdArticleCommande());
                preparedStatement.executeUpdate();
                if (findArticleCommandeByAdresseId(articleCommande.getIdArticleCommande()) != null) {
                    acDeleted = true;
                }
            }
        } catch (Exception e) {
            throw new EbenusException("la suppression de l'article commande a échoué", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return acDeleted;
    }


}
