/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.test;

import com.daoebenus.ebenus.dao.IArticleCommandeDao;
import com.daoebenus.ebenus.dao.ICommandeDao;
import com.daoebenus.ebenus.dao.entities.Adresse;
import com.daoebenus.ebenus.dao.entities.Commande;
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
public class JUnitCommandeDao {
    
    private static final Log log = LogFactory.getLog(JUnitDao.class);
    private static IServiceFacade serviceFacade = null;
    
    private static List<Commande> commandes = null;

    @BeforeClass
    public static void init() throws Exception {
        log.debug("Initialisation des test");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        
        ICommandeDao commandeDao = serviceFacade.getCommandeDao();
        commandes = commandeDao.findAllCommandes();
        IArticleCommandeDao articleCommandeDao = serviceFacade.getArticleCommandeDao();
    }
    
        @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        //uniquement pour les vraies tests sinon c'est long
        Fonctions.runSqlScript(scriptSqlPath);
    }
    
        public static void verifyCommandeDatas(Commande commande) {
        log.debug("Entree de la mehode verifyCommandeData");
        if (commande != null) {
            log.debug("idCommande: " + commande.getIdCommande());
            Assert.assertNotNull(commande.getTotalCommande());
            Assert.assertNotNull(commande.getUtilisateur());
            Assert.assertNotNull(commande.getUtilisateur().getCivilite());
            Assert.assertNotNull(commande.getUtilisateur().getIdUtilisateur());
            Assert.assertNotNull(commande.getUtilisateur().getIdentifiant());
            Assert.assertNotNull(commande.getUtilisateur().getMotPasse());
            Assert.assertNotNull(commande.getUtilisateur().getDateCreation());
            Assert.assertNotNull(commande.getUtilisateur().getDateModification());
            Assert.assertNotNull(commande.getUtilisateur().getDateNaissance());
            Assert.assertNotNull(commande.getUtilisateur().getNom());
            Assert.assertNotNull(commande.getUtilisateur().getPrenom());
            Assert.assertNotNull(commande.getUtilisateur().getRole());
            Assert.assertNotNull(commande.getUtilisateur().getRole().getIdRole());
            Assert.assertNotNull(commande.getUtilisateur().getRole().getIdentifiant());
            Assert.assertNotNull(commande.getUtilisateur().getRole().getDescription());
            Assert.assertNotNull(commande.getAdresse());
            Assert.assertNotNull(commande.getAdresse().getIdAdresse());
            Assert.assertNotNull(commande.getAdresse().getCodePostal());
            Assert.assertNotNull(commande.getAdresse().getPays());
            Assert.assertNotNull(commande.getAdresse().getPrincipale());
            Assert.assertNotNull(commande.getAdresse().getRue());
            Assert.assertNotNull(commande.getAdresse().getStatus());
            Assert.assertNotNull(commande.getAdresse().getTypeAdresse());
            Assert.assertNotNull(commande.getAdresse().getVille());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getCivilite());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getDateCreation());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getDateModification());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getDateNaissance());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getIdUtilisateur());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getIdentifiant());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getMotPasse());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getNom());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getPrenom());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getRole());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getRole().getIdRole());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getRole().getIdentifiant());
            Assert.assertNotNull(commande.getAdresse().getUtilisateur().getRole().getDescription());            
            Assert.assertNotNull(commande.getStatut());
            Assert.assertNotNull(commande.getDateCommande());
            Assert.assertNotNull(commande.getDateModification());
        } else if (commande == null) {
            Assert.fail("Commande null");
        }
        log.debug("Sortie de la mehode verifyCommandeCommandeData");
    }

    public static void verifyCommandesDatas(List<Commande> commandes) {
        log.debug("Entree de la methode verifyCommandesDatas");
        if (commandes != null) {
            log.debug("commandes.size(): " + commandes.size());
            for (Commande commande : commandes) {
                verifyCommandeDatas(commande);
            }
        } else if (commandes == null || commandes.isEmpty()) {
            Assert.fail("Aucune commande n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode verifyCommandesDatas");
    }
    
        @Test
    public void testFindAllCommandes() {
        log.debug("Entree de la methode testFindAllCommandes");
        if (commandes != null) {
            log.debug("NB_COMMANDES_LIST :" + Constants.NB_COMMANDES_LIST + " , commandes.size(): " + commandes.size());
            Assert.assertEquals(Constants.NB_COMMANDES_LIST, commandes.size());
        } else {
            Assert.fail("Aucune Commande n'a ete trouves dans votre base de donnÃ©es");
        }
        log.debug("Sortie de la methode testFindAllCommandes");
    }
    
        @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode testFindByCriteria");
        
        List<Commande> commandeByProduitNomCanape = serviceFacade.getCommandeDao().findCommandeByProduitNom(Constants.COMMANDE_FIND_BY_PRODUIT_NOM);
        List<Commande> commandeByProduitReferenceCanape = serviceFacade.getCommandeDao().findCommandeByProduitReference(Constants.COMMANDE_FIND_BY_PRODUIT_REFERENCE);
        List<Commande> commandeByUtilisateurIdentifiantThomas = serviceFacade.getCommandeDao().findCommmandeByUtilisateurIdentifiant(Constants.COMMANDE_FIND_BY_UTILISATEUR_IDENTIFIANT);
        
        //commandeList
        if (commandeByProduitNomCanape != null && !commandeByProduitNomCanape.isEmpty()) {
            log.debug("NB_COMMANDE_FIND_BY_PRODUIT_NOM : " + Constants.NB_COMMANDE_FIND_BY_PRODUIT_NOM + " , commandeByProduitNomCanape.size() : " + commandeByProduitNomCanape.size() );
            Assert.assertEquals(Constants.NB_COMMANDE_FIND_BY_PRODUIT_NOM, commandeByProduitNomCanape.size());
            verifyCommandesDatas(commandeByProduitNomCanape);
        } else {
            Assert.fail("Aucune commande avec le nom du produit '" + Constants.COMMANDE_FIND_BY_PRODUIT_NOM + "' n'a ete trouvee danv votre base de donnees");
        }
        if (commandeByProduitReferenceCanape != null && !commandeByProduitReferenceCanape.isEmpty()) {
            log.debug("NB_COMMANDE_FIND_BY_PRODUIT_REFERENCE : " + Constants.NB_COMMANDE_FIND_BY_PRODUIT_REFERENCE + " , commandeByProduitReferenceCanape.size() : " + commandeByProduitReferenceCanape.size() );
            Assert.assertEquals(Constants.NB_COMMANDE_FIND_BY_PRODUIT_REFERENCE, commandeByProduitReferenceCanape.size());
            verifyCommandesDatas(commandeByProduitReferenceCanape);
        } else {
            Assert.fail("Aucune commande avec le nom du produit '" + Constants.COMMANDE_FIND_BY_PRODUIT_REFERENCE + "' n'a ete trouvee danv votre base de donnees");
        }
        if (commandeByUtilisateurIdentifiantThomas != null && !commandeByUtilisateurIdentifiantThomas.isEmpty()) {
            log.debug("NB_COMMANDE_FIND_BY_UTILISATEUR_IDENTIFIANT :" + Constants.NB_COMMANDE_FIND_BY_UTILISATEUR_IDENTIFIANT + " , commandeByUtilisateurIdentifiantThomas.size(): " + commandeByUtilisateurIdentifiantThomas.size());
            Assert.assertEquals(Constants.NB_COMMANDE_FIND_BY_UTILISATEUR_IDENTIFIANT, commandeByUtilisateurIdentifiantThomas.size());
            verifyCommandesDatas(commandeByUtilisateurIdentifiantThomas);
        } else {
            Assert.fail("Aucune commande avec l'identifiant de l'urilisateur : " + Constants.COMMANDE_FIND_BY_UTILISATEUR_IDENTIFIANT + " n'a ete trouve dans votre base de donnees");
        }
    }
    
    @Test
    public void testCreateUpdateDeleteCommande() {
        log.debug("Entree de la methode testCreateUpdateDeleteCommande");
        //role de l'utilisateur de la commande
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        JUnitRoleDao.verifyRoleData(roleCRUD);
        log.debug("Created roleCRUD : " + roleCRUD);
        log.debug("Created roleCRUD.getIdRole : " + roleCRUD.getIdRole());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNotNull(roleCRUD);
        //utilisateur de la commande
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
        //create commandeCRUD
        Commande commandeCRUD = new Commande(1000.0, userCRUD, adresseCRUD, "T", new Date(System.currentTimeMillis())); //T pour temporaire
        commandeCRUD = serviceFacade.getCommandeDao().createCommande(commandeCRUD);
        log.debug("Created commandeCRUD : " + commandeCRUD);
        commandeCRUD = serviceFacade.getCommandeDao().findCommandeById(commandeCRUD.getIdCommande());
        Assert.assertNotNull(commandeCRUD);
        Assert.assertNotNull(commandeCRUD.getAdresse());
        Assert.assertNotNull(commandeCRUD.getDateCommande());
        Assert.assertNotNull(commandeCRUD.getDateModification());
        Assert.assertNotNull(commandeCRUD.getTotalCommande());
        Assert.assertNotNull(commandeCRUD.getUtilisateur());
        Assert.assertNotNull(commandeCRUD.getUtilisateur().getIdUtilisateur());
        //update commandeCRUD
        commandeCRUD.setStatut("V");
        commandeCRUD = serviceFacade.getCommandeDao().updateCommande(commandeCRUD);
        Assert.assertNotNull(commandeCRUD);
        commandeCRUD = serviceFacade.getCommandeDao().findCommandeById(commandeCRUD.getIdCommande());
        log.debug("Updated Commande : " + commandeCRUD);
        Assert.assertEquals(commandeCRUD.getStatut(), "V");
        //destruction de la commande, du role , de l'utilisateur et de l'adresse
        Assert.assertTrue(serviceFacade.getCommandeDao().deleteCommande(commandeCRUD));
        commandeCRUD = serviceFacade.getCommandeDao().findCommandeById(commandeCRUD.getIdCommande());
        Assert.assertNull(commandeCRUD);
        
        Assert.assertTrue(serviceFacade.getAdresseDao().deleteAdresse(adresseCRUD));
        adresseCRUD = serviceFacade.getAdresseDao().findAdresseById(adresseCRUD.getIdAdresse());
        Assert.assertNull(adresseCRUD);
        
        Assert.assertTrue(serviceFacade.getUtilisateurDao().deleteUtilisateur(userCRUD));
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        Assert.assertNull(userCRUD);
        
        Assert.assertTrue(serviceFacade.getRoleDao().deleteRole(roleCRUD));
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNull(roleCRUD);

        List<Commande> commandesFinal = serviceFacade.getCommandeDao().findAllCommandes();
        if (commandesFinal != null) {
            Assert.assertEquals(Constants.NB_COMMANDES_LIST, commandesFinal.size());
            log.debug("commandesFinal.size() : " + commandesFinal.size() + "NB_COMMANDES_LIST : " + Constants.NB_COMMANDES_LIST);
        }
        log.debug("Sortie de la methode testCreateUpdateDeleteCommande");
    }
    
        @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode terminate");
        serviceFacade = null;
        commandes = null;
        log.debug("Sortie de la methode terminate");
    }
    
}
