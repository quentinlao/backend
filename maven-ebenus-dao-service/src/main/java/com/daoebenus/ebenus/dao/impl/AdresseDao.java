/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.impl;

import com.daoebenus.ebenus.dao.ConnectionHelper;
import com.daoebenus.ebenus.dao.DriverManagerSingleton;
import com.daoebenus.ebenus.dao.IAdresseDao;
import com.daoebenus.ebenus.dao.entities.Adresse;
import com.daoebenus.ebenus.dao.entities.Utilisateur;
import com.daoebenus.ebenus.dao.exception.EbenusException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quentin
 */
public class AdresseDao implements IAdresseDao {

    UtilisateurDao utilisateurDao = new UtilisateurDao();

    private Adresse fillAdresse(ResultSet results, Adresse adresse) {
        if (results != null && adresse != null) {
            try {
                if (results.getInt("idAdresse") > 0) {
                    adresse.setIdAdresse(results.getInt("idAdresse"));
                }
                if (results.getInt("idUtilisateur") > 0) {
                    int idUtilisateur = results.getInt("idUtilisateur");
                    Utilisateur utilisateur = utilisateurDao.findUtilisateurById(idUtilisateur);
                    adresse.setUtilisateur(utilisateur);
                }
                if (results.getString("rue") != null) {
                    adresse.setRue(results.getString("rue"));
                }
                if (results.getString("codePostal") != null) {
                    adresse.setCodePostal(results.getString("codePostal"));
                }
                if (results.getString("ville") != null) {
                    adresse.setVille(results.getString("ville"));
                }
                if (results.getString("pays") != null) {
                    adresse.setPays(results.getString("pays"));
                }
                if (results.getString("statut") != null) {
                    adresse.setStatus(results.getString("statut"));
                }
                if (results.getString("typeAdresse") != null) {
                    adresse.setTypeAdresse(results.getString("typeAdresse"));
                }
                if (results.getByte("principale") >= 0) {
                    adresse.setPrincipale(results.getByte("principale"));
                }
                if (results.getInt("version") >= 0) {
                    adresse.setVersion(results.getInt("version"));
                }
            } catch (Exception e) {
                throw new EbenusException("échec lors de la récupération d'une adresse", e, 4);
            }
        }
        return adresse;
    }

    @Override
    public List<Adresse> findAllAdresses() {
        List<Adresse> adressesToFind = new ArrayList();
        Adresse adresseToAdd = null;
        String sqlRequest = "SELECT * FROM adresse";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                adresseToAdd = new Adresse();
                fillAdresse(results, adresseToAdd);
                if (adresseToAdd != null) {
                    adressesToFind.add(adresseToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllAdresses()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return adressesToFind;
    }

    @Override
    public Adresse findAdresseById(int idAdresse) {
        Adresse adresseToFind = null;
        String sqlRequest = "SELECT * FROM adresse WHERE idAdresse = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idAdresse);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                adresseToFind = new Adresse();
                fillAdresse(results, adresseToFind);
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllAdresses()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return adresseToFind;
    }

    @Override
    public List<Adresse> findAdresseByRue(String rueAdresse) {
        List<Adresse> adressesToFind = new ArrayList();
        Adresse adresseToAdd = null;
        String sqlRequest = "SELECT * FROM adresse WHERE rue = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setString(1, rueAdresse);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                adresseToAdd = new Adresse();
                fillAdresse(results, adresseToAdd);
                if (adresseToAdd != null) {
                    adressesToFind.add(adresseToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllAdresses()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return adressesToFind;
    }

    private boolean adresseHaveId(Adresse adresse) {
        boolean exists = false;
        if (adresse != null
                && adresse.getIdAdresse() != null
                && adresse.getIdAdresse() > 0) {
            exists = true;
        }
        return exists;
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion = null;
        ResultSet results = null;
        if (!adresseHaveId(adresse)) {
            sqlRequest = "INSERT INTO Adresse(idUtilisateur, rue, codePostal, ville, pays) VALUES(?, ?, ?, ?, ?)";
            connexion = DriverManagerSingleton.getConnectionInstance();
            try {
                preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS);
                if (adresse != null) {
                    if (adresse.getUtilisateur() != null) {
                        if (adresse.getUtilisateur().getIdUtilisateur() > 0) {
                            preparedStatement.setInt(1, adresse.getUtilisateur().getIdUtilisateur());
                        }
                    }
                    if (adresse.getRue() != null) {
                        preparedStatement.setString(2, adresse.getRue());
                    }
                    if (adresse.getCodePostal() != null) {
                        preparedStatement.setString(3, adresse.getCodePostal());
                    }
                    if (adresse.getVille() != null) {
                        preparedStatement.setString(4, adresse.getVille());
                    }
                    if (adresse.getPays() != null) {
                        preparedStatement.setString(5, adresse.getPays());
                    }
                    preparedStatement.executeUpdate();
                    results = preparedStatement.getGeneratedKeys(); //permet de récupérer l'idAdresse pour le return
                    if (results.next()) {
                        adresse.setIdAdresse((int) results.getLong(1));
                    }
                }
            } catch (Exception e) {
                throw new EbenusException("l'ajout d'un role a échoué", e, -1);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, null);
            }
        }
        return adresse;
    }

    @Override
    public Adresse updateAdresse(Adresse adresse) {
        String sqlRequest = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        if (adresseHaveId(adresse)) {
            try {
                connexion = DriverManagerSingleton.getConnectionInstance();
                sqlRequest = "UPDATE Adresse SET idUtilisateur=?, rue=?, codePostal=?, ville=?, pays=?, statut=?, typeAdresse=?, principale=? WHERE idAdresse=?";
                preparedStatement = connexion.prepareStatement(sqlRequest);
                if (adresse.getUtilisateur() != null) {
                    if (adresse.getUtilisateur().getIdUtilisateur() > 0) {
                        preparedStatement.setInt(1, adresse.getUtilisateur().getIdUtilisateur());
                    }
                }
                if (adresse.getRue() != null) {
                    preparedStatement.setString(2, adresse.getRue());
                }
                if (adresse.getCodePostal() != null) {
                    preparedStatement.setString(3, adresse.getCodePostal());
                }
                if (adresse.getVille() != null) {
                    preparedStatement.setString(4, adresse.getVille());
                }
                if (adresse.getPays() != null) {
                    preparedStatement.setString(5, adresse.getPays());
                }
                if (adresse.getStatus() != null) {
                    preparedStatement.setString(6, adresse.getStatus());
                }
                if (adresse.getTypeAdresse() != null) {
                    preparedStatement.setString(7, adresse.getTypeAdresse());
                }
                if (adresse.getPrincipale() >= 0) {
                    preparedStatement.setByte(8, adresse.getPrincipale());
                }
                if (adresse.getIdAdresse() > 0) {
                    preparedStatement.setInt(9, adresse.getIdAdresse());
                }
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                throw new EbenusException("la mise à jour de l'adresse a échoué", e, 8);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, results);
            }
        }
        return adresse;
    }

    @Override
    public boolean deleteAdresse(Adresse adresse) {
        boolean adresseDeleted = true;
        String sqlRequest = "DELETE FROM Adresse WHERE idAdresse=?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = DriverManagerSingleton.getConnectionInstance();
        if (adresseHaveId(adresse)) {
            try {
                preparedStatement = connexion.prepareCall(sqlRequest);
                preparedStatement.setInt(1, adresse.getIdAdresse()); //pas besoin de chehk nullité deja fait avec adresseHaveId
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                throw new EbenusException("la suppression d'une role a échoué", e, 6);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, results);
            }
            if (findAdresseById(adresse.getIdAdresse()) != null) {
                adresseDeleted = false;
            }
        }
        return adresseDeleted;
    }

    @Override
    public List<Adresse> findAdreseByIdUtilisateur(int idUtilisateur) {
        List<Adresse> adressesToFind = new ArrayList();
        Adresse adresseToAdd = null;
        String sqlRequest = "SELECT * FROM adresse WHERE idUtilisateur = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idUtilisateur);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                adresseToAdd = new Adresse();
                fillAdresse(results, adresseToAdd);
                if (adresseToAdd != null) {
                    adressesToFind.add(adresseToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAdreseByIdUtilisateur()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return adressesToFind;
    }

    @Override
    public List<Adresse> findAdresseByIdentifiantUtilisateur(String identifiantUtilisateur) {
        List<Adresse> adressesToFind = new ArrayList();
        Adresse adresseToAdd = null;
        String sqlRequest = "SELECT * FROM adresse "
                + "INNER JOIN utilisateur "
                + "ON adresse.idUtilisateur = utilisateur.idUtilisateur "
                + "WHERE identifiant = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setString(1, identifiantUtilisateur);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                adresseToAdd = new Adresse();
                fillAdresse(results, adresseToAdd);
                if (adresseToAdd != null) {
                    adressesToFind.add(adresseToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAdresseByIdentifiantUtilisateur()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return adressesToFind;
    }

}
