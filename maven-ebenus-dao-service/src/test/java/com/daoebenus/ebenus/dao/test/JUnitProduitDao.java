/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.test;

import com.daoebenus.ebenus.dao.IProduitDao;
import com.daoebenus.ebenus.dao.entities.Produit;
import com.daoebenus.ebenus.factory.AbstractDaoFactory;
import com.daoebenus.ebenus.service.IServiceFacade;
import com.daoebenus.ebenus.service.ServiceFacade;
import com.daoebenus.ebenus.utils.Constants;
import com.daoebenus.ebenus.utils.Fonctions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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
public class JUnitProduitDao {
    
    private static final Log log = LogFactory.getLog(JUnitDao.class);
    private static IServiceFacade serviceFacade = null;
    private static List<Produit> produits = null;

        @BeforeClass
    public static void init() throws Exception {
        log.debug("Initialisation des test");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        IProduitDao produitDao = serviceFacade.getProduitDao();
        produits = produitDao.findAllProduits();
    }
    
        @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        //uniquement pour les vraies tests sinon c'est long
        Fonctions.runSqlScript(scriptSqlPath);
    }
    
    public static void verifyProduitDatas(Produit produit) {
        log.debug("Entree de la mehode verifyProduitData");
        if (produit != null) {
            log.debug("idProduit : " + produit.getIdProduit());
            Assert.assertNotNull(produit.getNom());
            Assert.assertNotNull(produit.getReference());
            Assert.assertNotNull(produit.getPrix());
            Assert.assertNotNull(produit.getDescription());
            Assert.assertNotNull(produit.getStock());
            Assert.assertNotNull(produit.getActive());
            Assert.assertNotNull(produit.getMarquerEffacer());
        } else if (produit == null) {
            Assert.fail("Produit null");
        }
        log.debug("Sortie de la mehode verifyProduitCommandeData");
    }
   
    public static void verifyProduitsDatas(List<Produit> produits) {
        log.debug("Entree de la methode verifyProduitsDatas");
        if (produits != null) {
            log.debug("produits.size(): " + produits.size());
            for (Produit produit : produits) {
                verifyProduitDatas(produit);
            }
        } else if (produits == null || produits.isEmpty()) {
            Assert.fail("Aucun produit n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode verifyProduitsDatas");
    }
    
   @Test
    public void testFindAllProduits() {
        log.debug("Entree de la methode testFindAllProduits");
        if (produits != null) {
            log.debug("NB_PRODUITS_LIST :" + Constants.NB_PRODUITS_LIST + " , produits.size(): " + produits.size());
            Assert.assertEquals(Constants.NB_PRODUITS_LIST, produits.size());
        } else {
            Assert.fail("Aucun Produit n'a ete trouves dans votre base de donnÃ©es");
        }
        log.debug("Sortie de la methode testFindAllProduits");
    }
    
    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode testFindByCriteria");
    
        List<Produit> produitByNomIphone4 = serviceFacade.getProduitDao().findProduitByNom(Constants.PRODUIT_FIND_BY_NOM_IPHONE_4);
        Produit produitByReferenceIphone4 = serviceFacade.getProduitDao().findProduitByReference(Constants.PRODUIT_FIND_BY_REFERENCE_REF_IPHONE_4);
        
        //produitList
                if (produitByNomIphone4 != null && !produitByNomIphone4.isEmpty()) {
            log.debug("NB_PRODUIT_FIND_BY_NOM_IPHONE_4 :" + Constants.NB_PRODUIT_FIND_BY_NOM_IPHONE_4 + " , produitByNomIphone4.size(): " +produitByNomIphone4.size() );
            Assert.assertEquals(Constants.NB_PRODUIT_FIND_BY_NOM_IPHONE_4, produitByNomIphone4.size());
            verifyProduitsDatas(produitByNomIphone4);
        } else {
            Assert.fail("Aucun Produit avec le nom '" + Constants.PRODUIT_FIND_BY_NOM_IPHONE_4 + "' n'a ete trouve dans votre base de donnees");
        }
        if (produitByReferenceIphone4 != null) {
            verifyProduitDatas(produitByReferenceIphone4);
        } else {
            Assert.fail("Aucun Produit avec la reference '" + Constants.PRODUIT_FIND_BY_REFERENCE_REF_IPHONE_4 + "' n'a ete trouve dans votre base de donnees");
        }
    
    }
    
    @Test
    public void testCreateUpdateDeleteProduit() {
        log.debug("Entree de la methode testCreateUpdateDeleteProduit");
        Produit produitCRUD = new Produit("Produit reference", 1000.0, "nom produit", "description produit", 20,(byte) 1); 
        produitCRUD = serviceFacade.getProduitDao().createProduit(produitCRUD);
        verifyProduitDatas(produitCRUD);
        log.debug("Created produitCRUD : " + produitCRUD);
        log.debug("Created produitCRUD.getIdProduit : " + produitCRUD.getIdProduit());
        produitCRUD = serviceFacade.getProduitDao().findProduitById(produitCRUD.getIdProduit());
        Assert.assertNotNull(produitCRUD);
        produitCRUD.setNom("nom produit bis");
        produitCRUD.setDescription("description produit bis");
        produitCRUD.setReference("reference produit bis");
        produitCRUD = serviceFacade.getProduitDao().updateProduit(produitCRUD);
        Assert.assertNotNull(produitCRUD);
        produitCRUD = serviceFacade.getProduitDao().findProduitById(produitCRUD.getIdProduit());
        log.debug("Updated produitCRUD : " + produitCRUD);
        Assert.assertEquals("nom produit bis", produitCRUD.getNom());
        Assert.assertEquals("reference produit bis", produitCRUD.getReference());
        Assert.assertEquals("description produit bis", produitCRUD.getDescription());
        Assert.assertTrue(serviceFacade.getProduitDao().deleteProduit(produitCRUD));
        produitCRUD = serviceFacade.getProduitDao().findProduitById(produitCRUD.getIdProduit());
        Assert.assertNull(produitCRUD);
        List<Produit> produitsFinal = serviceFacade.getProduitDao().findAllProduits();
        if (produitsFinal != null) {
            Assert.assertEquals(Constants.NB_PRODUITS_LIST, produitsFinal.size());
            log.debug("produitsFinal.size() : " + produitsFinal.size() + " , NB_PRODUITS_LIST: " + Constants.NB_PRODUITS_LIST);
        }
        log.debug("Sortie de la methode testCreateUpdateDeleteProduit");
    }
    
    
    
        @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode terminate");
        serviceFacade = null;
        produits = null;
        log.debug("Sortie de la methode terminate");
    }
}
