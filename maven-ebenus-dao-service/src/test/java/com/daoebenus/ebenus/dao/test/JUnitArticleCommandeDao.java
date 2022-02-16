/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.test;

import com.daoebenus.ebenus.dao.IArticleCommandeDao;
import com.daoebenus.ebenus.dao.entities.Adresse;
import com.daoebenus.ebenus.dao.entities.ArticleCommande;
import com.daoebenus.ebenus.dao.entities.Commande;
import com.daoebenus.ebenus.dao.entities.Produit;
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
public class JUnitArticleCommandeDao {

    private static final Log log = LogFactory.getLog(JUnitDao.class);
    private static IServiceFacade serviceFacade = null;
    private static List<ArticleCommande> articleCommandes = null;

    @BeforeClass
    public static void init() throws Exception {
        log.debug("Initialisation des test");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);

        IArticleCommandeDao articleCommandeDao = serviceFacade.getArticleCommandeDao();
        articleCommandes = articleCommandeDao.findAllArticleCommandes();

    }
    
    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        //uniquement pour les vraies tests sinon c'est long
        Fonctions.runSqlScript(scriptSqlPath);
    }
    
    
    public void verifyArticleCommandeDatas(ArticleCommande ac) {
        log.debug("Entree de la mehode verifyArticleCommandeData");
        if (ac != null) {
            log.debug("idArticleCommande : " + ac.getIdArticleCommande());
            Assert.assertNotNull(ac.getIdArticleCommande());
            Assert.assertNotNull(ac.getCommande());
            JUnitCommandeDao.verifyCommandeDatas(ac.getCommande());
            Assert.assertNotNull(ac.getUtilisateur());
            JUnitUtilisateurDao.verifyUserDatas(ac.getUtilisateur());
            Assert.assertNotNull(ac.getAdresse());
            JUnitAdresseDao.verifyAdresseDatas(ac.getAdresse());
            Assert.assertNotNull(ac.getProduit());
            JUnitProduitDao.verifyProduitDatas(ac.getProduit());
            Assert.assertNotNull(ac.getTotalArticleCommande());
            Assert.assertNotNull(ac.getReference());
            Assert.assertNotNull(ac.getQuantite());
            Assert.assertNotNull(ac.getStatus());
            Assert.assertNotNull(ac.getDateModification());
        } else if (ac == null) {
            Assert.fail("ArticleCommande null");
        }
        log.debug("Sortie de la mehode verifyArticleCommandeData");
    }

    public void verifyArticleCommandesDatas(List<ArticleCommande> articleCommandes) {
        log.debug("Entree de la methode verifyArticleCommandesDatas");
        if (articleCommandes != null) {
            log.debug("articleCommandes.size(): " + articleCommandes.size());
            for (ArticleCommande ac : articleCommandes) {
                verifyArticleCommandeDatas(ac);
            }
        } else if (articleCommandes == null || articleCommandes.isEmpty()) {
            Assert.fail("Aucun article commande n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode verifyArticleCommandesDatas");
    }
    
    @Test
    public void testFindAllArticleCommandes() {
        log.debug("Entree de la methode testFindAllArticleCommandes");
        if (articleCommandes != null) {
            log.debug("NB_ARTICLECOMMANDES_LIST :" + Constants.NB_ARTICLECOMMANDES_LIST + " , articleCommandes.size(): " + articleCommandes.size());
            Assert.assertEquals(Constants.NB_ARTICLECOMMANDES_LIST, articleCommandes.size());
        } else {
            Assert.fail("Aucun ArticleCommande n'a ete trouves dans votre base de donnees");
        }
        log.debug("Sortie de la methode testFindAllArticleCommandes");
    }
    
    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode testFindByCriteria");
    
        List<ArticleCommande> acByUtilisateurIdentifiantNicolas = serviceFacade.getArticleCommandeDao().findArticleCommandeByUtilisateurId(Constants.ARTICLECOMMANDE_FIND_BY_UTILISATEUR_ID);
        List<ArticleCommande> acByAdresse6RueDeLaPudeur = serviceFacade.getArticleCommandeDao().findArticleCommandeByAdresseId(Constants.ARTICLECOMMANDE_FIND_BY_ADRESSE_ID);

        //articleCommandesList
        if (acByUtilisateurIdentifiantNicolas != null && !acByUtilisateurIdentifiantNicolas.isEmpty()) {
            log.debug("NB_ARTICLECOMMANDE_FIND_BY_UTILISATEUR_ID: " + Constants.NB_ARTICLECOMMANDE_FIND_BY_UTILISATEUR_ID + " , acByUtilisateurIdentifiantNicolas.size(): " + acByUtilisateurIdentifiantNicolas.size() );
            Assert.assertEquals(Constants.NB_ARTICLECOMMANDE_FIND_BY_UTILISATEUR_ID, acByUtilisateurIdentifiantNicolas.size());
            verifyArticleCommandesDatas(acByUtilisateurIdentifiantNicolas);
        } else {
            Assert.fail("Aucun article commande avec l'id utilisateur : '" + Constants.ARTICLECOMMANDE_FIND_BY_UTILISATEUR_ID + "' n'a ete triyve dans votre base de données");
        }
        if (acByAdresse6RueDeLaPudeur != null && !acByAdresse6RueDeLaPudeur.isEmpty()) {
            log.debug("NB_ARTICLECOMMANDE_FIND_BY_ADRESSE_ID: " + Constants.NB_ARTICLECOMMANDE_FIND_BY_ADRESSE_ID + " , acByAdresse6RueDeLaPudeur.size(): " + acByAdresse6RueDeLaPudeur.size());
            Assert.assertEquals(Constants.NB_ARTICLECOMMANDE_FIND_BY_ADRESSE_ID, acByAdresse6RueDeLaPudeur.size());
            verifyArticleCommandesDatas(acByAdresse6RueDeLaPudeur);
        } else {
            Assert.fail("Aucun article commande avec l'id adresse : '" + Constants.ARTICLECOMMANDE_FIND_BY_ADRESSE_ID + " n'a ete trouve dans votre base de données");
        }
        
        log.debug("Sortie de la methode testFindByCriteria");
    
    }
    
    
      @Test
    public void testCreateUpdateDeleteArticleCommande() {
        log.debug("Entree de la methode testCreateUpdateDeleteArticleCommande");
        //role de l'utilisateur de l'article commande
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        JUnitRoleDao.verifyRoleData(roleCRUD);
        log.debug("Created roleCRUD : " + roleCRUD);
        log.debug("Created roleCRUD.getIdRole : " + roleCRUD.getIdRole());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNotNull(roleCRUD);
        //utilisateur de l'article commande
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
        //produit de l'article commande
        Produit produitCRUD = new Produit("Produit reference", 1000.0, "nom produit", "description produit", 20,(byte) 1); 
        produitCRUD = serviceFacade.getProduitDao().createProduit(produitCRUD);
        JUnitProduitDao.verifyProduitDatas(produitCRUD);
        log.debug("Created produitCRUD : " + produitCRUD);
        //adresse de l'article commande
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
        //create articleCommandeCRUD
        ArticleCommande articleCommandeCRUD = new ArticleCommande(commandeCRUD, userCRUD, adresseCRUD, produitCRUD, commandeCRUD.getTotalCommande() + 100.0, "reference articlecommande", 1, "T", new Date(System.currentTimeMillis()));
        articleCommandeCRUD = serviceFacade.getArticleCommandeDao().createArticleCommande(articleCommandeCRUD);
        Assert.assertNotNull(articleCommandeCRUD);
        Assert.assertNotNull(articleCommandeCRUD.getAdresse());
        Assert.assertNotNull(articleCommandeCRUD.getCommande());
        Assert.assertNotNull(articleCommandeCRUD.getDateModification());
        Assert.assertNotNull(articleCommandeCRUD.getProduit());
        Assert.assertNotNull(articleCommandeCRUD.getStatus());
        Assert.assertNotNull(articleCommandeCRUD.getUtilisateur());
        Assert.assertNotNull(articleCommandeCRUD.getTotalArticleCommande());
        log.debug("Created articleCommandeCRUD : " + articleCommandeCRUD);
        articleCommandeCRUD = serviceFacade.getArticleCommandeDao().findArticleCommandeById(articleCommandeCRUD.getIdArticleCommande());
        Assert.assertNotNull(articleCommandeCRUD);
        //update articleCommandeCRUD
        articleCommandeCRUD.setStatus("V");
        articleCommandeCRUD = serviceFacade.getArticleCommandeDao().updateArticleCommande(articleCommandeCRUD);
        Assert.assertNotNull(articleCommandeCRUD);
        log.debug("Updated articleCommandeCRUD : " + articleCommandeCRUD );
        Assert.assertEquals(articleCommandeCRUD.getStatus(), "V");
        //destruction de la commande, du role , de l'utilisateur et de l'adresse
        //delete articleCommandeCRUD    
        Assert.assertTrue(serviceFacade.getArticleCommandeDao().deleteArticleCommande(articleCommandeCRUD));
        articleCommandeCRUD = serviceFacade.getArticleCommandeDao().findArticleCommandeById(articleCommandeCRUD.getIdArticleCommande());
        Assert.assertNull(articleCommandeCRUD);
        
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

        List<ArticleCommande> articleCommandesFinal = serviceFacade.getArticleCommandeDao().findAllArticleCommandes();
        if (articleCommandesFinal != null) {
            Assert.assertEquals(Constants.NB_ARTICLECOMMANDES_LIST, articleCommandesFinal.size());
            log.debug("articleCommandesFinal.size() : " + articleCommandesFinal.size() + "NB_ARTICLECOMMANDES_LIST : " + Constants.NB_ARTICLECOMMANDES_LIST);
        }
        log.debug("Sortie de la methode testCreateUpdateDeleteArticleCommande");
    }
    
        @AfterClass
        public static void terminate() throws Exception {
            log.debug("Entree de la methode terminate");
            serviceFacade = null;
            articleCommandes = null;
            log.debug("Sortie de la methode terminate");
        }
    }
