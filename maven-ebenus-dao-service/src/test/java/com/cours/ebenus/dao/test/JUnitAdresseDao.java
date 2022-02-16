/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.test;

import com.daoebenus.ebenus.dao.IAdresseDao;
import com.daoebenus.ebenus.dao.entities.Adresse;
import com.daoebenus.ebenus.dao.entities.Role;
import com.daoebenus.ebenus.dao.entities.Utilisateur;
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
public class JUnitAdresseDao {
    
    private static final Log log = LogFactory.getLog(JUnitDao.class);
    private static IServiceFacade serviceFacade = null;
    private static List<Adresse> adresses = null;

    @BeforeClass
    public static void init() throws Exception {
        log.debug("Initialisation des test");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        IAdresseDao adresseDao = serviceFacade.getAdresseDao();
        adresses = adresseDao.findAllAdresses();
    }
    
        @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        //uniquement pour les vraies tests sinon c'est long
        Fonctions.runSqlScript(scriptSqlPath);
    }
    
    
    public static void verifyAdresseDatas(Adresse adresse) {
        log.debug("Entree de la methode verifyAdresseData");
        if (adresse != null) {
            log.debug("idAdresse : " + adresse.getIdAdresse());
            Assert.assertNotNull(adresse.getIdAdresse());
            Assert.assertNotNull(adresse.getUtilisateur());
            Assert.assertNotNull(adresse.getUtilisateur().getCivilite());
            Assert.assertNotNull(adresse.getUtilisateur().getIdUtilisateur());
            Assert.assertNotNull(adresse.getUtilisateur().getNom());
            Assert.assertNotNull(adresse.getUtilisateur().getPrenom());
            Assert.assertNotNull(adresse.getUtilisateur().getDateCreation());
            Assert.assertNotNull(adresse.getUtilisateur().getDateModification());
            Assert.assertNotNull(adresse.getUtilisateur().getDateNaissance());
            Assert.assertNotNull(adresse.getUtilisateur().getRole());
            Assert.assertNotNull(adresse.getUtilisateur().getRole().getIdRole());
            Assert.assertNotNull(adresse.getUtilisateur().getRole().getIdentifiant());
            Assert.assertNotNull(adresse.getUtilisateur().getRole().getDescription());
            Assert.assertNotNull(adresse.getRue());
            Assert.assertNotNull(adresse.getCodePostal());
            Assert.assertNotNull(adresse.getVille());
            Assert.assertNotNull(adresse.getPays());
            Assert.assertNotNull(adresse.getStatus());
            Assert.assertNotNull(adresse.getTypeAdresse());
            Assert.assertNotNull(adresse.getPrincipale());
        } else if (adresse == null) {
            Assert.fail("Adresse null");
        }
        log.debug("Sortie de la methode verifyAdresseData");
    }

    public static void verifyAdressesDatas(List<Adresse> adresses) {
        log.debug("Entree de la methode verifyAdressesDatas");
        if (adresses != null) {
            log.debug("adresses.size(): " + adresses.size());
            for (Adresse adresse : adresses) {
                verifyAdresseDatas(adresse);
            }
        } else if (adresses == null || adresses.isEmpty()) {
            Assert.fail("Aucune adresse n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode verifyAdressesDatas");
    }
    
        @Test
    public void testFindAllAdresses() {
        log.debug("Entree de la methode testFindAllAdresses");
        if (adresses != null) {
            log.debug("NB_ADRESSES_LIST :" + Constants.NB_ADRESSES_LIST + " , adresses.size(): " + adresses.size());
            Assert.assertEquals(Constants.NB_ADRESSES_LIST, adresses.size());
        } else {
            Assert.fail("Aucune Adresse n'a ete trouves dans votre base de donnÃ©es");
        }
        log.debug("Sortie de la methode testFindAllAdresses");
    }
    
     @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode testFindByCriteria");
        
        List<Adresse> adressesByRueConscience = serviceFacade.getAdresseDao().findAdresseByRue(Constants.ADRESSES_FIND_BY_RUE_33_RUE_DE_LA_CONSCIENCE);
        List<Adresse> adressesByRueGaston = serviceFacade.getAdresseDao().findAdresseByRue(Constants.ADRESSES_FIND_BY_1_RUE_DE_GASTON);
        
        //adressesList
        if (adressesByRueConscience != null && !adressesByRueConscience.isEmpty()) {
            log.debug("NB_ADRESSES_FIND_BY_RUE_33_RUE_DE_LA_CONSCIENCE: " + Constants.NB_ADRESSES_FIND_BY_RUE_33_RUE_DE_LA_CONSCIENCE + ", adressesByRueConscience.size(): " + adressesByRueConscience.size());
            Assert.assertEquals(Constants.NB_ADRESSES_FIND_BY_RUE_33_RUE_DE_LA_CONSCIENCE, adressesByRueConscience.size());
        } else {
            Assert.fail("Aucune adresse avec l'adresse '" + Constants.ADRESSES_FIND_BY_RUE_33_RUE_DE_LA_CONSCIENCE + "' n'a ete trouve dans votre base de donnees");
        }
        if (adressesByRueGaston != null && !adressesByRueGaston.isEmpty()) {
            log.debug("NB_ADRESSES_FIND_BY_1_RUE_DE_GASTON: " + Constants.NB_ADRESSES_FIND_BY_1_RUE_DE_GASTON + ", adressesByRueGaston.size(): " + adressesByRueGaston.size());
            Assert.assertEquals(Constants.NB_ADRESSES_FIND_BY_1_RUE_DE_GASTON, adressesByRueGaston.size());
        } else {
            Assert.fail("Aucune adresse avec l'adresse '" + Constants.ADRESSES_FIND_BY_1_RUE_DE_GASTON + "' n'a ete trouve dans votre base de donnees");
        }
    }
        
        @Test
    public void testCreateUpdateDeleteAdresse() {
        log.debug("Entree de la methode testCreateUpdateDeleteCommande");
        //role de l'utilisateur de l'adresse
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        JUnitRoleDao.verifyRoleData(roleCRUD);
        log.debug("Created roleCRUD : " + roleCRUD);
        log.debug("Created roleCRUD.getIdRole : " + roleCRUD.getIdRole());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNotNull(roleCRUD);
        //utilisateur de l'adresse
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
        Adresse adresseCRUD = new Adresse(userCRUD, "rue des tests", "99999", "TestVille", "PaysTest");
        adresseCRUD = serviceFacade.getAdresseDao().createAdresse(adresseCRUD);
        Assert.assertNotNull(adresseCRUD);
        Assert.assertNotNull(adresseCRUD.getCodePostal());
        Assert.assertNotNull(adresseCRUD.getIdAdresse());
        Assert.assertNotNull(adresseCRUD.getPays());
        Assert.assertNotNull(adresseCRUD.getPrincipale());
        Assert.assertNotNull(adresseCRUD.getRue());
        Assert.assertNotNull(adresseCRUD.getStatus());
        Assert.assertNotNull(adresseCRUD.getTypeAdresse());
        Assert.assertNotNull(adresseCRUD.getUtilisateur());
        log.debug("Created adresseCRUD : " + adresseCRUD);
        adresseCRUD = serviceFacade.getAdresseDao().findAdresseById(adresseCRUD.getIdAdresse());
        Assert.assertNotNull(adresseCRUD);
        adresseCRUD.setRue("rue des tests bis");
        adresseCRUD.setVille("TestVille bis");
        adresseCRUD.setPays("PaysTest bis");
        adresseCRUD = serviceFacade.getAdresseDao().updateAdresse(adresseCRUD);
        Assert.assertNotNull(adresseCRUD);
        adresseCRUD = serviceFacade.getAdresseDao().findAdresseById(adresseCRUD.getIdAdresse());
        log.debug("Updated adresseCRUD : " + adresseCRUD);
        Assert.assertEquals("rue des tests bis", adresseCRUD.getRue());
        Assert.assertEquals("TestVille bis", adresseCRUD.getVille());
        Assert.assertEquals("PaysTest bis", adresseCRUD.getPays());
        
        Assert.assertTrue(serviceFacade.getAdresseDao().deleteAdresse(adresseCRUD));
        adresseCRUD = serviceFacade.getAdresseDao().findAdresseById(adresseCRUD.getIdAdresse());
        Assert.assertNull(adresseCRUD);
        
        Assert.assertTrue(serviceFacade.getUtilisateurDao().deleteUtilisateur(userCRUD));
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        Assert.assertNull(userCRUD);
        
        Assert.assertTrue(serviceFacade.getRoleDao().deleteRole(roleCRUD));
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNull(roleCRUD);
        // Cas des gestions des doublons (il faut une clé unique pour adresse (autre que l'id)
        /* */
        
        List<Adresse> adressesFinal = serviceFacade.getAdresseDao().findAllAdresses();
        if (adressesFinal != null) {
            Assert.assertEquals(Constants.NB_ADRESSES_LIST, adressesFinal.size());
            log.debug("adressesFinal.size() : " + adressesFinal.size() + " , NB_ADRESSES_LIST : " + Constants.NB_ADRESSES_LIST);
        }
        log.debug("Sortie de la methode testCreateUpdateDeleteAdresse");
    }
        
    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode terminate");
        serviceFacade = null;
        adresses = null;
        log.debug("Sortie de la methode terminate");
    }
}
    
