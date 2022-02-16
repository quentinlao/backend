/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.impl;

import com.daoebenus.ebenus.dao.ConnectionHelper;
import com.daoebenus.ebenus.dao.DriverManagerSingleton;
import com.daoebenus.ebenus.dao.ICommandeDao;
import com.daoebenus.ebenus.dao.entities.Adresse;
import com.daoebenus.ebenus.dao.entities.Commande;
import com.daoebenus.ebenus.dao.entities.Utilisateur;
import com.daoebenus.ebenus.dao.exception.EbenusException;
import com.daoebenus.ebenus.utils.Fonctions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Quentin
 */
public class CommandeDao implements ICommandeDao {

    private static final Log log = LogFactory.getLog(CommandeDao.class);
    private UtilisateurDao utilisateurDao = new UtilisateurDao();
    private AdresseDao adresseDao = new AdresseDao();

    private void fillCommande(ResultSet results, Commande commande) {
        if (results != null && commande != null) {

            try {
                if (results.getInt("idCommande") > 0) {
                    commande.setIdCommande(results.getInt("idCommande"));
                }
                if (results.getDouble("totalCommande") > 0.0) {
                    commande.setTotalCommande(results.getDouble("totalCommande"));
                }
                if (results.getString("statut") != null) {
                    commande.setStatut(results.getString("statut"));
                }
                if (results.getTimestamp("dateCommande") != null) {
                    commande.setDateCommande(results.getTimestamp("dateCommande"));
                }
                if (results.getTimestamp("dateModification") != null) {
                    commande.setDateModification(results.getTimestamp("dateModification"));
                }
                if (results.getInt("idUtilisateur") > 0 ) {
                    int idUtilisateur = results.getInt("idUtilisateur");
                    Utilisateur user = utilisateurDao.findUtilisateurById(idUtilisateur);
                    commande.setUtilisateur(user);
                }
                if (results.getInt("idAdresse") > 0) {
                    int idAdresse = results.getInt("idAdresse");
                    Adresse adresse = adresseDao.findAdresseById(idAdresse);
                    commande.setAdresse(adresse);
                }
                if (results.getInt("version") > 0) {
                    commande.setVersion(results.getInt("version"));
                }
            } catch (Exception e) {
                throw new EbenusException("échec lors de la récupération d'une commande", e, 4);
            }
        }
    }

    @Override
    public List<Commande> findAllCommandes() {
        List<Commande> commandesToFind = new ArrayList();
        Commande commandeToAdd = null;
        String sqlRequest = "SELECT * FROM commande";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                commandeToAdd = new Commande();
                fillCommande(results, commandeToAdd);
                if (commandeToAdd != null) {
                    commandesToFind.add(commandeToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllCommande()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return commandesToFind;
    }

    @Override
    public Commande findCommandeById(int idCommande) {
        Commande commandeToFind = null;
        String sqlRequest = "SELECT * FROM commande WHERE idCommande = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = DriverManagerSingleton.getConnectionInstance();
        try {
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idCommande);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                commandeToFind = new Commande();
                fillCommande(results, commandeToFind);
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findCommandeById()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return commandeToFind;
    }

    private boolean commandeHaveId(Commande commande) {
        boolean commandeHaveId = false;
        if (commande != null) {
            if (commande.getIdCommande() != null) {
                if (commande.getIdCommande() > 0) {
                    commandeHaveId = true;
                }
            }
        }
        return commandeHaveId;
    }

    @Override
    public Commande createCommande(Commande commande) {
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion = null;
        ResultSet results = null;
        if (!commandeHaveId(commande)) {
            sqlRequest = "INSERT INTO commande (totalCommande, idUtilisateur, idAdresse, statut, dateCommande, dateModification) VALUES(?, ?, ?, ?, ?, ?)";
            connexion = DriverManagerSingleton.getConnectionInstance();
            try {
                preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS);
                if (commande != null) {
                    if (commande.getTotalCommande() > 0.0) {
                        preparedStatement.setDouble(1, commande.getTotalCommande());
                    }
                    if (commande.getUtilisateur() != null && commande.getUtilisateur().getIdUtilisateur() != null) {
                        preparedStatement.setInt(2, commande.getUtilisateur().getIdUtilisateur());
                    }
                    if (commande.getAdresse() != null && commande.getAdresse().getIdAdresse() > 0) {
                        preparedStatement.setInt(3, commande.getAdresse().getIdAdresse());
                    }
                    if (commande.getStatut() != null) {
                        preparedStatement.setString(4, commande.getStatut());
                    }
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    java.sql.Timestamp nowSql = new java.sql.Timestamp(System.currentTimeMillis());
                    java.util.Date now = new Date(nowSql.getTime());
                    preparedStatement.setTimestamp(5, nowSql); // dateCommande
                    preparedStatement.setTimestamp(6, nowSql); //dateModification
                    preparedStatement.executeUpdate();
                    results = preparedStatement.getGeneratedKeys(); //permet de récupérer l'idCommande pour le return
                    if (results.next()) {
                        commande.setIdCommande((int) results.getLong(1));
                        commande.setDateCommande(now);
                        commande.setDateModification(now);
                    }
                }
            } catch (Exception e) {
                throw new EbenusException("l'ajout d'une commande a échoué", e, -1);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, null);
            }

        }
        return commande;
    }

    @Override
    public Commande updateCommande(Commande commande) {
        Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "";

        try {
            if (commandeHaveId(commande)) {
                sqlRequest = "UPDATE commande SET totalCommande=?, idUtilisateur=?, idAdresse=?, statut=?, dateCommande=?, dateModification=? WHERE idCommande= ?";
                connexion = DriverManagerSingleton.getConnectionInstance();

                preparedStatement = connexion.prepareStatement(sqlRequest);
                if (commande.getTotalCommande() > 0.0) {
                    preparedStatement.setDouble(1, commande.getTotalCommande());
                }
                if (commande.getUtilisateur() != null && commande.getUtilisateur().getIdUtilisateur() != null) {
                    preparedStatement.setInt(2, commande.getUtilisateur().getIdUtilisateur());
                }
                if (commande.getAdresse() != null && commande.getAdresse().getIdAdresse() > 0) {
                    preparedStatement.setInt(3, commande.getAdresse().getIdAdresse());
                }
                if (commande.getStatut() != null) {
                    preparedStatement.setString(4, commande.getStatut());
                }
                if (commande.getDateCommande() != null) {
                    java.sql.Timestamp dateCommande = new java.sql.Timestamp(commande.getDateCommande().getTime());
                    preparedStatement.setTimestamp(5, dateCommande);
                }
                //java.sql.Date nowSql = new java.sql.Date(System.currentTimeMillis());
  //              Calendar cet = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                java.sql.Timestamp nowTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
                java.util.Date now = new Date(nowTimestamp.getTime());
                preparedStatement.setTimestamp(6, nowTimestamp);
                preparedStatement.setInt(7, commande.getIdCommande());
                preparedStatement.executeUpdate();
                commande.setDateModification(now);
            }
        } catch (Exception e) {
            throw new EbenusException("la mise à jour de la commande a échoué", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return commande;
    }

    @Override
    public boolean deleteCommande(Commande commande) {
        Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "";
        boolean commandeDeleted = true;

        try {
            if (commandeHaveId(commande)) {
                sqlRequest = "DELETE FROM commande WHERE idCommande=?";
                connexion = DriverManagerSingleton.getConnectionInstance();
                preparedStatement = connexion.prepareStatement(sqlRequest);
                preparedStatement.setInt(1, commande.getIdCommande());
                preparedStatement.executeUpdate();
                if (findCommandeById(commande.getIdCommande()) != null) {
                    commandeDeleted = true;
                }
            }
        } catch (Exception e) {
            throw new EbenusException("la suppression de la commande a échoué", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }

        return commandeDeleted;
    }

    @Override
    public List<Commande> findCommandeByProduitId(int idProduit) {
        List<Commande> commandesToFind = new ArrayList();
        Commande commandeToAdd = null;
        String sqlRequest = "SELECT * FROM commande " + 
                            " INNER JOIN articlecommande ON commande.idCommande = articlecommande.idCommande " + 
                            " WHERE idProduit = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setInt(1, idProduit);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                commandeToAdd = new Commande();
                fillCommande(results, commandeToAdd);
                if (commandeToAdd != null) {
                    commandesToFind.add(commandeToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("toutes les commandes avec l'id " + idProduit + " n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return commandesToFind;
    }

    @Override
    public List<Commande> findCommandeByProduitNom(String nomProduit) {
        List<Commande> commandesToFind = new ArrayList();
        Commande commandeToAdd = null;
        String sqlRequest = "SELECT * FROM commande "
                         + " INNER JOIN articlecommande "
                         + " ON articlecommande.idCommande = commande.idCommande "
                         + " INNER JOIN produit "
                         + " ON articlecommande.idProduit = produit.idProduit "
                         + "WHERE produit.nom = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setString(1, nomProduit);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                commandeToAdd = new Commande();
                fillCommande(results, commandeToAdd);
                if (commandeToAdd != null) {
                    commandesToFind.add(commandeToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("toutes les commandes dont le nom des produits sont '" + nomProduit + "' n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return commandesToFind;
    }

    @Override
    public List<Commande> findCommandeByProduitReference(String referenceProduit) {
        List<Commande> commandesToFind = new ArrayList();
        Commande commandeToAdd = null;
        String sqlRequest = "SELECT * FROM commande "
                         + " INNER JOIN articlecommande "
                         + " ON articlecommande.idCommande = commande.idCommande "
                         + " INNER JOIN produit "
                         + " ON articlecommande.idProduit = produit.idProduit "
                         + "WHERE produit.reference = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setString(1, referenceProduit);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                commandeToAdd = new Commande();
                fillCommande(results, commandeToAdd);
                if (commandeToAdd != null) {
                    commandesToFind.add(commandeToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("toutes les commandes dont le nom des produits sont '" + referenceProduit + "' n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return commandesToFind;
    }

    @Override
    public List<Commande> findCommandeByUtilisateurId(int idUtilisateur) {
        List<Commande> commandesToFind = new ArrayList();
        Commande commandeToAdd = null;
//        String sqlRequest = "SELECT * FROM articlecommande WHERE idUtilisateur = ?";
//        String sqlRequest = "SELECT * FROM Commande INNER JOIN Utilisateur ON Commande.idUtilisateur = Utilisateur.idUtilisateur WHERE idUtilisateur = ?";
        String sqlRequest = "SELECT * FROM Commande WHERE idUtilisateur = ?"; //fillCommande recherche deja l'utilisateur
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setInt(1, idUtilisateur);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                commandeToAdd = new Commande();
                fillCommande(results, commandeToAdd);
                if (commandeToAdd != null) {
                    commandesToFind.add(commandeToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les commandes de l'utilisateur n°" + idUtilisateur +" n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return commandesToFind;
    }

    @Override
    public List<Commande> findCommmandeByUtilisateurIdentifiant(String identifiantUtilisateur) {
        List<Commande> commandesToFind = new ArrayList();
        Commande commandeToAdd = null;
        String sqlRequest = "SELECT * FROM Commande INNER JOIN Utilisateur ON Commande.idUtilisateur = Utilisateur.idUtilisateur WHERE Utilisateur.identifiant = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setString(1, identifiantUtilisateur);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                commandeToAdd = new Commande();
                fillCommande(results, commandeToAdd);
                if (commandeToAdd != null) {
                    commandesToFind.add(commandeToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les commandes de " + identifiantUtilisateur +  "  n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return commandesToFind;
    }

}
