/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.main;

import com.daoebenus.ebenus.dao.DriverManagerSingleton;
import com.daoebenus.ebenus.dao.entities.Adresse;
import com.daoebenus.ebenus.dao.entities.ArticleCommande;
import com.daoebenus.ebenus.dao.entities.Commande;
import com.daoebenus.ebenus.dao.entities.Produit;
import com.daoebenus.ebenus.dao.entities.Role;
import com.daoebenus.ebenus.dao.entities.Utilisateur;
import com.daoebenus.ebenus.dao.impl.AdresseDao;
import com.daoebenus.ebenus.dao.impl.ArticleCommandeDao;
import com.daoebenus.ebenus.dao.impl.CommandeDao;
import com.daoebenus.ebenus.dao.impl.ProduitDao;
import com.daoebenus.ebenus.dao.impl.RoleDao;
import com.daoebenus.ebenus.dao.impl.UtilisateurDao;
import com.daoebenus.ebenus.service.ServiceFacade;
import static com.daoebenus.ebenus.utils.Constants.SQL_JUNIT_PATH_FILE;
import com.daoebenus.ebenus.utils.Fonctions;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        ServiceFacade serviceFacade = new ServiceFacade();
        RoleDao roleDao = new RoleDao();
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        AdresseDao adresseDao = new AdresseDao();
        CommandeDao commandeDao = new CommandeDao();
        ProduitDao produitDao = new ProduitDao();
        ArticleCommandeDao acDao = new ArticleCommandeDao();
        /*
        List<Commande> commandes = commandeDao.findCommandeByProduitNom("Canape en cuir");
        for (Commande commande : commandes ) {
            System.out.println(commande);
        }*/
        
        //findAllRoles
        /*
        List<Role> roles = roleDao.findAllRoles();
        for (Role role : roles) {
            System.out.println(role);
        }
        */
        //findAllUtilisateurs
        /*
        List<Utilisateur> utilisateurs = utilisateurDao.findAllUtilisateurs();
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println(utilisateur);
        }
        */
        //findAllAdresses
        /*
        List<Adresse> adresses = adresseDao.findAllAdresses();
        for (Adresse adresse : adresses) {
            System.out.println(adresse);
        }
        */
        
        //findAllCommandes
        /*
        List<Commande> commandes = commandeDao.findAllCommandes();
        for (Commande commande : commandes) {
            System.out.println(commande);
        }
        */
        
        //findAllProduits
        /*
        List<Produit> produits = produitDao.findAllProduits();
        for(Produit produit : produits){
            System.out.println(produit);
        }
        */
        //findAllArticleCommandes
        /*
        List<ArticleCommande> articleCommandes = acDao.findAllArticleCommandes();
        for(ArticleCommande ac : articleCommandes) {
            System.out.println(ac);
        }
        */
        //script d'import
        /*
        Fonctions.runSqlScript(SQL_JUNIT_PATH_FILE);
        DriverManagerSingleton test = DriverManagerSingleton.getInstance();
        */
    }
}
