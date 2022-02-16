/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.impl;

import com.daoebenus.ebenus.dao.ConnectionHelper;
import com.daoebenus.ebenus.dao.DriverManagerSingleton;
import com.daoebenus.ebenus.dao.IUtilisateurDao;
import com.daoebenus.ebenus.dao.entities.Role;
import com.daoebenus.ebenus.dao.entities.Utilisateur;
import com.daoebenus.ebenus.dao.exception.EbenusException;
import com.daoebenus.ebenus.dao.impl.RoleDao;
import com.daoebenus.ebenus.utils.Fonctions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class UtilisateurDao /*extends AbstractDao<Utilisateur>*/ implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);
    private RoleDao roleDao = new RoleDao();
    //public UtilisateurDao() {
    //    super(Utilisateur.class);
    //}

    public Utilisateur fillUtilisateur(ResultSet results, Utilisateur user) {

        if (results != null) {
            try {
                user.setIdUtilisateur(results.getInt("idUtilisateur"));
                if (results.getString("civilite") != null) {
                    user.setCivilite(results.getString("civilite"));
                }
                if (results.getString("prenom") != null) {
                    user.setPrenom(results.getString("prenom"));
                }
                if (results.getString("nom") != null) {
                    user.setNom(results.getString("nom"));
                }
                if (results.getString("identifiant") != null) {
                    user.setIdentifiant(results.getString("identifiant"));
                }
                if (results.getString("motPasse") != null) {
                    user.setMotPasse(results.getString("motPasse"));
                }
                if (results.getDate("dateCreation") != null) {
                    user.setDateCreation(results.getTimestamp("dateCreation"));
                }
                if (results.getDate("dateNaissance") != null) {
                    user.setDateNaissance(results.getTimestamp("dateNaissance"));
                }
                if (results.getDate("dateModification") != null) {
                    user.setDateModification(results.getTimestamp("dateModification"));
                }
                user.setActif(results.getBoolean("actif"));
                user.setMarquerEffacer(results.getBoolean("marquerEffacer"));
                user.setVersion(results.getInt("version"));
                /* pas sur que ca soit bien */
                Role role = roleDao.findRoleById(results.getInt("idRole"));
                user.setRole(role);
            } catch (Exception e) {
                throw new EbenusException("l'un des utilisateurs est corrompu", e, 10);
            }
        }
        return user;
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        List<Utilisateur> usersToFind = new ArrayList();
        Utilisateur userToAdd = null;
        String sqlRequest = "SELECT * FROM Utilisateur";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                userToAdd = new Utilisateur();
                fillUtilisateur(results, userToAdd);
                if (userToAdd != null) {
                    usersToFind.add(userToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les utilisateurs n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return usersToFind;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        Utilisateur userToFind = null;
        String sqlRequest = "SELECT * FROM Utilisateur WHERE idUtilisateur = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setInt(1, idUtilisateur);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                userToFind = new Utilisateur();
                fillUtilisateur(results, userToFind);
            }
        } catch (Exception e) {
            throw new EbenusException("tous les utilisateurs n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return userToFind;
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        List<Utilisateur> usersToFind = new ArrayList();
        Utilisateur userToAdd = null;
        String sqlRequest = "SELECT * FROM Utilisateur WHERE prenom = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setString(1, prenom);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                userToAdd = new Utilisateur();
                fillUtilisateur(results, userToAdd);
                if (userToAdd != null) {
                    usersToFind.add(userToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les utilisateurs n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return usersToFind;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        List<Utilisateur> usersToFind = new ArrayList();
        Utilisateur userToAdd = null;
        String sqlRequest = "SELECT * FROM Utilisateur WHERE nom = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setString(1, nom);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                userToAdd = new Utilisateur();
                fillUtilisateur(results, userToAdd);
                if (userToAdd != null) {
                    usersToFind.add(userToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les utilisateurs n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return usersToFind;
    }

    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
        List<Utilisateur> usersToFind = new ArrayList();
        Utilisateur userToAdd = null;
        String sqlRequest = "SELECT * FROM Utilisateur WHERE identifiant = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setString(1, identifiant);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                userToAdd = new Utilisateur();
                fillUtilisateur(results, userToAdd);
                if (userToAdd != null) {
                    usersToFind.add(userToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les utilisateurs n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return usersToFind;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
        List<Utilisateur> usersToFind = new ArrayList();
        Utilisateur userToAdd = null;
        String sqlRequest = "SELECT * FROM Utilisateur WHERE idRole = ?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setInt(1, idRole);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                userToAdd = new Utilisateur();
                fillUtilisateur(results, userToAdd);
                if (userToAdd != null) {
                    usersToFind.add(userToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les utilisateurs n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return usersToFind;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
        List<Utilisateur> usersToFind = new ArrayList();
        Utilisateur userToAdd = null;
        String sqlRequest = "SELECT * FROM Utilisateur INNER JOIN Role ON Utilisateur.idRole = Role.idRole WHERE Role.identifiant=?";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareCall(sqlRequest);
            preparedStatement.setString(1, identifiantRole);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                userToAdd = new Utilisateur();
                fillUtilisateur(results, userToAdd);
                if (userToAdd != null) {
                    usersToFind.add(userToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("tous les utilisateurs n'ont pas pu être trouvés", e, 9);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return usersToFind;
    }

    private boolean userExistByIdentifiant(Utilisateur user) {
        boolean userExist = false;
        if (user != null) {
            if (user.getIdentifiant() != null) {
                if (findUtilisateurByIdentifiant(user.getIdentifiant()).size() > 0) {
                    List<Utilisateur> userl = findUtilisateurByIdentifiant(user.getIdentifiant());
                    if (!userl.contains(user)) 
                    userExist = true;
                }
            }
        }
        return userExist;
    }

    private boolean userHaveId(Utilisateur user) {
        boolean userHaveId = false;
        if (user != null) {
            if (user.getIdUtilisateur() > 0) {
                userHaveId = true;
            }
        }
        return userHaveId;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "INSERT INTO Utilisateur (idRole, civilite, prenom, nom, identifiant, motPasse, dateNaissance, dateCreation, dateModification) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS);
            if (!userExistByIdentifiant(user)) {
                if (user.getRole() != null && user.getRole().getIdRole() != null) {
                    preparedStatement.setInt(1, user.getRole().getIdRole());
                }
                if (user.getCivilite() != null) {
                    preparedStatement.setString(2, user.getCivilite());
                }
                if (user.getPrenom() != null) {
                    preparedStatement.setString(3, user.getPrenom());
                }
                if (user.getNom() != null) {
                    preparedStatement.setString(4, user.getNom());
                }
                if (user.getIdentifiant() != null) {
                    preparedStatement.setString(5, user.getIdentifiant());
                }
                if (user.getMotPasse() != null) {
                    preparedStatement.setString(6, user.getMotPasse());
                }
                if (user.getDateNaissance() != null) {
                    java.sql.Timestamp dateNaissance = new Timestamp(user.getDateNaissance().getTime());
                    preparedStatement.setTimestamp(7, dateNaissance);
                }
                //java.sql.Date nowSql = new java.sql.Date(System.currentTimeMillis());
                //java.util.Date now = Fonctions.convertSqlToUtilDate(nowSql);
                //java.sql.Timestamp nowTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
                //java.util.Date now = Fonctions.convertTimestampToUtilDate(nowTimestamp);
                //Calendar cet = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                java.util.Date now = new Date();
                String nowStr = df.format(now);
                java.sql.Timestamp nowSql = new java.sql.Timestamp(System.currentTimeMillis());
                preparedStatement.setTimestamp(8, nowSql); // dateCreation
                preparedStatement.setTimestamp(9, nowSql); //dateModification
                preparedStatement.executeUpdate();
                results = preparedStatement.getGeneratedKeys(); //permet de récupérer l'idUtilisateur pour le return
                if (results.next()) {
                    user.setIdUtilisateur((int) results.getLong(1)); //récupère le premier int (cad idUtilisateur)
                }
                user.setDateCreation(now);
                user.setDateModification(now);
            } else {
                throw new EbenusException("l'identifiant de cet utilisateur est déjà pris", -1);
            }
        } catch (Exception e) {
            throw new EbenusException("la création de l'utilisateur a échoué", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return user;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "";

        try {
            if (userHaveId(user)) {
                sqlRequest = "UPDATE Utilisateur SET civilite=?, prenom=?, nom=?, identifiant=?, motPasse=?, dateNaissance=?, dateModification=? WHERE idUtilisateur = ?";
                connexion = DriverManagerSingleton.getConnectionInstance();

                preparedStatement = connexion.prepareStatement(sqlRequest);
                if (user.getCivilite() != null) {
                    preparedStatement.setString(1, user.getCivilite());
                }
                if (user.getPrenom() != null) {
                    preparedStatement.setString(2, user.getPrenom());
                }
                if (user.getNom() != null) {
                    preparedStatement.setString(3, user.getNom());
                }
                if (user.getIdentifiant() != null) {
                    preparedStatement.setString(4, user.getIdentifiant());
                }
                if (user.getMotPasse() != null) {
                    preparedStatement.setString(5, user.getMotPasse());
                }
                if (user.getDateNaissance() != null) {
                    java.sql.Timestamp dateNaissance = new java.sql.Timestamp(user.getDateNaissance().getTime());
                    preparedStatement.setTimestamp(6, dateNaissance);
                }
                //java.sql.Date nowSql = new java.sql.Date(System.currentTimeMillis());
                Calendar cet = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                java.sql.Timestamp nowTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
                java.util.Date now = Fonctions.convertTimestampToUtilDate(nowTimestamp);
                preparedStatement.setTimestamp(7, nowTimestamp, cet);
                preparedStatement.setInt(8, user.getIdUtilisateur());
                preparedStatement.executeUpdate();
                user.setDateModification(now);
            }
        } catch (Exception e) {
            throw new EbenusException("la mise à jour de l'utilisateur a échoué", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return user;
    }

    @Override 
    public Utilisateur updateUtilisateurSansMotPasse(Utilisateur user) {
    Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "";

        try {
            if (userHaveId(user)) {
                sqlRequest = "UPDATE Utilisateur SET civilite=?, prenom=?, nom=?, identifiant=?, dateNaissance=?, dateModification=? WHERE idUtilisateur = ?";
                connexion = DriverManagerSingleton.getConnectionInstance();

                preparedStatement = connexion.prepareStatement(sqlRequest);
                if (user.getCivilite() != null) {
                    preparedStatement.setString(1, user.getCivilite());
                }
                if (user.getPrenom() != null) {
                    preparedStatement.setString(2, user.getPrenom());
                }
                if (user.getNom() != null) {
                    preparedStatement.setString(3, user.getNom());
                }
                if (user.getIdentifiant() != null) {
                    preparedStatement.setString(4, user.getIdentifiant());
                }
                if (user.getDateNaissance() != null) {
                    java.sql.Timestamp dateNaissance = new Timestamp(user.getDateNaissance().getTime());
                    preparedStatement.setTimestamp(5, dateNaissance);
                }
                java.sql.Timestamp nowSql = new java.sql.Timestamp(System.currentTimeMillis());
                java.util.Date now = new java.util.Date(nowSql.getTime());
                preparedStatement.setTimestamp(6, nowSql);
                preparedStatement.setInt(7, user.getIdUtilisateur());
                preparedStatement.executeUpdate();
                user.setDateModification(now);
            }
        } catch (Exception e) {
            throw new EbenusException("la mise à jour de l'utilisateur sans le mot de passe a échoué", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return user;
    }
    
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "";
        boolean userDeleted = true;

        try {
            if (userHaveId(user)) {
                sqlRequest = "DELETE FROM Utilisateur WHERE idUtilisateur=?";
                connexion = DriverManagerSingleton.getConnectionInstance();
                preparedStatement = connexion.prepareStatement(sqlRequest);
                preparedStatement.setInt(1, user.getIdUtilisateur());
                preparedStatement.executeUpdate();
                if (findUtilisateurById(user.getIdUtilisateur()) != null) {
                    userDeleted = true;
                }
            }
        } catch (Exception e) {
            throw new EbenusException("la suppression de l'utilisateur a échoué", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }

        return userDeleted;
    }

    /**
     * Méthode qui vérifie les logs email / password d'un utilisateur dans la
     * base de données
     *
     * @param email L'email de l'utilisateur = identifiant
     * @param password Le password de l'utilisateur = motPasse
     * @return L'utilisateur qui tente de se logger si trouvé, null sinon
     */
    @Override
    public Utilisateur authenticate(String email, String password) {
        Utilisateur userToLog = null;
        Connection connexion = null;
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        String sqlRequest = "";
        try {
            sqlRequest = "SELECT * FROM Utilisateur WHERE identifiant=? AND motPasse=?";
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                userToLog = new Utilisateur();
                userToLog = fillUtilisateur(results, userToLog);
            } else {
                userToLog = null;
            }
        } catch (Exception e) {
            throw new EbenusException("l'authentification de l'utilisateur a échoué", e, -1);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return userToLog;
    }
}
