/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.test;

import com.daoebenus.ebenus.dao.IUtilisateurDao;
import com.daoebenus.ebenus.dao.entities.Role;
import com.daoebenus.ebenus.dao.entities.Utilisateur;
import com.daoebenus.ebenus.dao.exception.EbenusException;
import com.daoebenus.ebenus.factory.AbstractDaoFactory;
import com.daoebenus.ebenus.service.IServiceFacade;
import com.daoebenus.ebenus.service.ServiceFacade;
import com.daoebenus.ebenus.utils.Constants;
import com.daoebenus.ebenus.utils.Fonctions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Quentin
 */
public class JUnitUtilisateurDao {
 
    private static final Log log = LogFactory.getLog(JUnitDao.class);
    private static IServiceFacade serviceFacade = null;
    
    private static List<Utilisateur> utilisateurs = null;
    
    @BeforeClass
    public static void init() throws Exception {
        log.debug("Initialisation des test");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        IUtilisateurDao utilisateurDao = serviceFacade.getUtilisateurDao();
        utilisateurs = utilisateurDao.findAllUtilisateurs();
    }
    
    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        //uniquement pour les vraies tests sinon c'est long
        Fonctions.runSqlScript(scriptSqlPath);
    }
    
    public static void verifyUserDatas(Utilisateur user) {
        log.debug("Entree de la methode verifyUserData");
        if (user != null) {
            log.debug("idUtilisateur : " + user.getIdUtilisateur());
            Assert.assertNotNull(user.getIdUtilisateur());
            Assert.assertNotNull(user.getPrenom());
            Assert.assertNotNull(user.getNom());
            Assert.assertNotNull(user.getRole());
            Assert.assertNotNull(user.getRole().getIdRole());
            Assert.assertNotNull(user.getRole().getIdentifiant());
            Assert.assertNotNull(user.getRole().getDescription());
        } else if (user == null) {
            Assert.fail("Utilisateur null");
        }
        log.debug("Sortie de la methode verifyUserData");
    }
    
    public static void verifyUsersDatas(List<Utilisateur> utilisateurs) {
        log.debug("Entree de la methode verifyUsersDatas");
        if (utilisateurs != null) {
            log.debug("utilisateurs.size(): " + utilisateurs.size());
            for (Utilisateur user : utilisateurs) {
                verifyUserDatas(user);
            }
        } else if (utilisateurs == null || utilisateurs.isEmpty()) {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode verifyUsersDatas");
    }
    
    @Test
    public void testFindAllUtilisateurs() {
        log.debug("Entree de la methode testFindAllUtilisateurs");
        if (utilisateurs != null) {
            log.debug("NB_UTILISATEURS_LIST: " + Constants.NB_UTILISATEURS_LIST + " , utilisateurs.size(): " + utilisateurs.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_LIST, utilisateurs.size());
            verifyUsersDatas(utilisateurs);
        } else {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre base de donnÃ©es");
        }
        log.debug("Sortie de la methode testFindAllUtilisateurs");
    }
    
        @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode testFindByCriteria");
        List<Utilisateur> utilisateursByPrenom = serviceFacade.getUtilisateurDao().findUtilisateursByPrenom(Constants.UTILISATEUR_FIND_BY_PRENOM);
        List<Utilisateur> utilisateursByNom = serviceFacade.getUtilisateurDao().findUtilisateursByNom(Constants.UTILISATEUR_FIND_BY_NOM);
        List<Utilisateur> utilisateursByIdentifiantRoleAdmin = serviceFacade.getUtilisateurDao().findUtilisateursByIdentifiantRole(Constants.UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ADMIN);
        List<Utilisateur> utilisateursByIdentifiantRoleAcheteur = serviceFacade.getUtilisateurDao().findUtilisateursByIdentifiantRole(Constants.UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR);
        List<Utilisateur> utilisateursByIdentifiantRoleStandard = serviceFacade.getUtilisateurDao().findUtilisateursByIdentifiantRole(Constants.UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_STANDARD);
        
        //utilisateursList
        if (utilisateursByPrenom != null && !utilisateursByPrenom.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_PRENOM: " + Constants.NB_UTILISATEURS_FIND_BY_PRENOM + " , utilisateursByPrenom.size(): " + utilisateursByPrenom.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_FIND_BY_PRENOM, utilisateursByPrenom.size());
            verifyUsersDatas(utilisateursByPrenom);
        } else {
            Assert.fail("Aucun utilisateur avec le prenom '" + Constants.UTILISATEUR_FIND_BY_PRENOM + "' n'a ete trouve dans votre base de donnÃ©es");
        }
        if (utilisateursByNom != null && !utilisateursByNom.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_NOM: " + Constants.NB_UTILISATEURS_FIND_BY_NOM + " , utilisateursByNom.size(): " + utilisateursByNom.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_FIND_BY_NOM, utilisateursByNom.size());
            verifyUsersDatas(utilisateursByNom);
        } else {
            Assert.fail("Aucun utilisateur avec le nom '" + Constants.UTILISATEUR_FIND_BY_NOM + "' n'a ete trouve dans votre base de donnÃ©es");
        }
        if (utilisateursByIdentifiantRoleStandard != null && !utilisateursByIdentifiantRoleStandard.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD: " + Constants.NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD + " , utilisateursByIdentifiantRoleStandard.size(): " + utilisateursByIdentifiantRoleStandard.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD, utilisateursByIdentifiantRoleStandard.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleStandard);
        } else {
            Assert.fail("Aucun utilisateur avec le rÃ´le '" + Constants.UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_STANDARD + "' n'a ete trouve dans votre base de donnÃ©es");
        }
        if (utilisateursByIdentifiantRoleAcheteur != null && !utilisateursByIdentifiantRoleAcheteur.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR: " + Constants.NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR + " , utilisateursByIdentifiantRoleAcheteur.size(): " + utilisateursByIdentifiantRoleAcheteur.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR, utilisateursByIdentifiantRoleAcheteur.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleAcheteur);
        } else {
            Assert.fail("Aucun utilisateur avec le rÃ´le '" + Constants.UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR + "' n'a ete trouve dans votre base de donnÃ©es");
        }
        if (utilisateursByIdentifiantRoleAdmin != null && !utilisateursByIdentifiantRoleAdmin.isEmpty()) {
            log.debug("NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN: " + Constants.NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN + " , utilisateursByIdentifiantRoleAdmin.size(): " + utilisateursByIdentifiantRoleAdmin.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN, utilisateursByIdentifiantRoleAdmin.size());
            verifyUsersDatas(utilisateursByIdentifiantRoleAdmin);
        } else {
            Assert.fail("Aucun utilisateur avec le rÃ´le '" + Constants.UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ADMIN + "' n'a ete trouve dans votre base de donnÃ©es");
        }
    }
    
    @Test
    public void testCreateUpdateDeleteUtilisateur() {
        log.debug("Entree de la methode testCreateUpdateDeleteUtilisateur");
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        JUnitRoleDao.verifyRoleData(roleCRUD);
        log.debug("Created roleCRUD : " + roleCRUD);
        log.debug("Created roleCRUD.getIdRole : " + roleCRUD.getIdRole());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNotNull(roleCRUD);
        Utilisateur userCRUD = new Utilisateur("Mme", "Nicole", "Valentine", "nicole.valentine@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), roleCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
        Assert.assertNotNull(userCRUD);
        Assert.assertNotNull(userCRUD.getIdUtilisateur());
        Assert.assertNotNull(userCRUD.getPrenom());
        Assert.assertNotNull(userCRUD.getNom());
        Assert.assertNotNull(userCRUD.getDateCreation());
        Assert.assertNotNull(userCRUD.getDateModification());
        log.debug("Created userCRUD : " + userCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        Assert.assertNotNull(userCRUD);
        userCRUD.setPrenom("Nicole Bis");
        userCRUD.setNom("Valentine Bis");
        userCRUD = serviceFacade.getUtilisateurDao().updateUtilisateur(userCRUD);
        Assert.assertNotNull(userCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        log.debug("Updated userCRUD : " + userCRUD);
        Assert.assertEquals("Nicole Bis", userCRUD.getPrenom());
        Assert.assertEquals("Valentine Bis", userCRUD.getNom());
        Assert.assertTrue(serviceFacade.getUtilisateurDao().deleteUtilisateur(userCRUD));
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        Assert.assertNull(userCRUD);
        Assert.assertTrue(serviceFacade.getRoleDao().deleteRole(roleCRUD));
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNull(roleCRUD);
        // Cas des gestions des doublons d'identifiant (mail).
        userCRUD = new Utilisateur("Mr", "Admin", "Admin", "admin@gmail.com", "passw0rd", new Date(System.currentTimeMillis()));
        try {
            userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
            log.debug("Duplicate userCRUD : " + userCRUD.getIdentifiant());
        } catch (EbenusException e) {
            log.debug("Bravo la gestion des doublons d'identifiant marche parfaitement");
            Assert.assertEquals(Constants.EXCEPTION_CODE_USER_ALREADY_EXIST, e.getCode());
        }
        List<Utilisateur> utilisateursFinal = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        if (utilisateursFinal != null) {
            Assert.assertEquals(Constants.NB_UTILISATEURS_LIST, utilisateursFinal.size());
            log.debug("utilisateursFinal.size() : " + utilisateursFinal.size() + " , NB_UTILISATEURS_LIST: " + Constants.NB_UTILISATEURS_LIST);
        }
        log.debug("Sortie de la methode testCreateUpdateDeleteUtilisateur");
    }
    
        @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode terminate");
        serviceFacade = null;
        utilisateurs = null;
        log.debug("Sortie de la methode terminate");
    }
    
}
