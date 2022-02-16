/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.impl;
import com.daoebenus.ebenus.dao.ConnectionHelper;
import com.daoebenus.ebenus.dao.DriverManagerSingleton;
import com.daoebenus.ebenus.dao.IProduitDao;
import com.daoebenus.ebenus.dao.entities.Produit;
import java.util.List;
import com.daoebenus.ebenus.dao.exception.EbenusException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 * @author Quentin
 */
public class ProduitDao implements IProduitDao{
    
    public Produit fillProduit(ResultSet results, Produit produit) {
        if (results != null) {
            try {
                if (results.getInt("idProduit") > 0)
                    produit.setIdProduit(results.getInt("idProduit"));
                if (results.getString("reference") != null) {
                    produit.setReference(results.getString("reference"));
                }
                if (results.getDouble("prix") > 0.0) {
                    produit.setPrix(results.getDouble("prix"));
                }
                if (results.getString("nom") != null) {
                    produit.setNom(results.getString("nom"));
                }
                if (results.getString("description") != null) {
                    produit.setDescription(results.getString("description"));
                }
                if (results.getInt("stock") >= 0) {
                    produit.setStock(results.getInt("stock"));
                }
                if (results.getByte("active") >= 0) {
                    produit.setActive(results.getByte("active"));
                }
                if (results.getByte("marquerEffacer") >= 0) {
                    produit.setActive(results.getByte("marquerEffacer"));
                }
                if (results.getInt("version") >= 0)
                produit.setVersion(results.getInt("version"));
            } catch (Exception e) {
                throw new EbenusException("l'un des produits est corrompu : il ne possede pas le bon typage ou n'est pas complet", e, 10);
            }
        }
        return produit;
    }


    @Override
    public List<Produit> findAllProduits() {
        List<Produit> produitsToFind = new ArrayList();
        Produit produitToAdd = null;
        String sqlRequest = "SELECT * FROM produit";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                produitToAdd = new Produit();
                fillProduit(results, produitToAdd);
                if (produitToAdd != null) {
                    produitsToFind.add(produitToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les produits n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return produitsToFind;
    }

    @Override
    public Produit findProduitById(int idProduit) {
        Produit produitToFind = null;
        String sqlRequest = "SELECT * FROM produit WHERE idProduit = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idProduit);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                produitToFind = new Produit();
                fillProduit(results, produitToFind);
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllProduit()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return produitToFind;
    }
    
    @Override
    public List<Produit> findProduitByNom(String nomProduit) {
        List<Produit> produitsToFind = new ArrayList();
        Produit produitToFind = null;
        String sqlRequest = "SELECT * FROM produit WHERE nom = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setString(1, nomProduit);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                produitToFind = new Produit();
                fillProduit(results, produitToFind);
                if (produitToFind != null) {
                    produitsToFind.add(produitToFind);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllProduitByNom()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return produitsToFind;
    }

    @Override
    public Produit findProduitByReference(String reference) {
        Produit produitToFind = null;
        String sqlRequest = "SELECT * FROM produit WHERE reference = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setString(1, reference);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                produitToFind = new Produit();
                fillProduit(results, produitToFind);
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllProduit()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return produitToFind;
    }

    private boolean produitHaveId(Produit produit) {
        boolean exists = false;
        if (produit != null) {
            if (produit.getIdProduit() != null) {
                if (produit.getIdProduit() > 0) {
                    exists = true;
                }
            }
        }
        return exists;
    }


    @Override
    public Produit createProduit(Produit produit) {
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion = null;
        ResultSet results = null;
        if (!produitHaveId(produit)) {
            sqlRequest = "INSERT INTO produit(reference, prix, nom, description, stock, active, marquerEffacer) VALUES(?, ?, ?, ?, ?, ?, ?)";
            connexion = DriverManagerSingleton.getConnectionInstance();
            try {
                preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS);
                if (produit != null) {
                    if (produit.getReference() != null) {
                        preparedStatement.setString(1, produit.getReference());
                    }
                    if (produit.getPrix() != null) {
                        preparedStatement.setDouble(2, produit.getPrix());
                    }
                    if (produit.getNom() != null) {
                        preparedStatement.setString(3, produit.getNom());
                    }
                    if (produit.getDescription() != null) {
                        preparedStatement.setString(4, produit.getDescription());
                    }
                    if (produit.getStock() > 0) {
                        preparedStatement.setInt(5, produit.getStock());
                    }
                    if (produit.getActive() >= 0) {
                        preparedStatement.setByte(6, produit.getActive());
                    }
                    if (produit.getMarquerEffacer() >= 0) {
                        preparedStatement.setByte(7, produit.getMarquerEffacer());
                    }
                    preparedStatement.executeUpdate();
                    results = preparedStatement.getGeneratedKeys(); //permet de r�cup�rer l'idProduit pour le return
                    if (results.next()) {
                        produit.setIdProduit((int) results.getLong(1));
                    }
                }
            } catch (Exception e) {
                throw new EbenusException("l'ajout d'un produit a �chou�", e, -1);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, null);
            }
        }
        return produit;
    }

    @Override
    public Produit updateProduit(Produit produit) {
        String sqlRequest = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        if (produitHaveId(produit)) {
            try {
                connexion = DriverManagerSingleton.getConnectionInstance();
                sqlRequest = "UPDATE produit SET reference=?, prix=?, nom=?, description=?, stock=?, active=?, marquerEffacer=? WHERE idProduit=?";
                preparedStatement = connexion.prepareStatement(sqlRequest);
                if (produit.getReference() != null) {
                    preparedStatement.setString(1, produit.getReference());
                }
                if (produit.getPrix() != null) {
                    preparedStatement.setDouble(2, produit.getPrix());
                }
                if (produit.getNom() != null) {
                    preparedStatement.setString(3, produit.getNom());
                }
                if (produit.getDescription() != null) {
                    preparedStatement.setString(4, produit.getDescription());
                }
                if (produit.getStock() > 0) {
                    preparedStatement.setInt(5, produit.getStock());
                }
                if (produit.getActive() >= 0) {
                    preparedStatement.setByte(6, produit.getActive());
                }
                if (produit.getMarquerEffacer() >= 0) {
                    preparedStatement.setByte(7, produit.getMarquerEffacer());
                }
                if (produit.getIdProduit() >= 0) {
                    preparedStatement.setInt(8, produit.getIdProduit());
                }
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                throw new EbenusException("la mise � jour du produit a �chou�", e, 8);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, results);
            }
        }
        return produit;
    }

    @Override
    public boolean deleteProduit(Produit produit) {
        Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "";
        boolean produitDeleted = true;

        try {
            if (produitHaveId(produit)) {
                sqlRequest = "DELETE FROM produit WHERE idProduit=?";
                connexion = DriverManagerSingleton.getConnectionInstance();
                preparedStatement = connexion.prepareStatement(sqlRequest);
                preparedStatement.setInt(1, produit.getIdProduit());
                preparedStatement.executeUpdate();
                if (findProduitById(produit.getIdProduit()) != null) {
                    produitDeleted = true;
                }
            }
        } catch (Exception e) {
            throw new EbenusException("la suppression du produit a �chou�", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }

        return produitDeleted;
    }


    
}
