/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.utils;

/**
 *
 * @author Quentin
 */
public class Constants {

    // Url de connexion en base de donnée
    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/base_site_commerce_ebenus?useSSL=false";
    // Utilisateur de la base de données
    public static String DATABASE_USER = "application";
    // Mot de passe de la base de données
    public static String DATABASE_PASSWORD = "passw0rd";

    // Drivers Jdbc
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static String SQL_JUNIT_PATH_FILE = "script_base_site_commerce_ebenus.sql";

    public static int EXCEPTION_CODE_USER_ALREADY_EXIST = -1;
    
    // Compter le nombre d'utilisateurs,roles, adresses, articlecommandes,commades et produits dans votre base de donnÃ©es.
    public static final int NB_UTILISATEURS_LIST = 18;
    public static final int NB_ROLES_LIST = 6;
    public static final int NB_ADRESSES_LIST = 40;
    public static final int NB_ARTICLECOMMANDES_LIST = 1647;
    public static final int NB_COMMANDES_LIST = 621;
    public static final int NB_PRODUITS_LIST = 35;
    //FINDBY UTILISATEUR
    public static final String UTILISATEUR_FIND_BY_PRENOM = "Nicolas";
    public static final int NB_UTILISATEURS_FIND_BY_PRENOM = 1;

    public static final String UTILISATEUR_FIND_BY_NOM = "Petit";
    public static final int NB_UTILISATEURS_FIND_BY_NOM = 1;

    public static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_STANDARD = "Standard";
    public static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_STANDARD = 7;

    public static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR = "Acheteur";
    public static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ACHETEUR = 8;

    public static final String UTILISATEUR_FIND_BY_IDENTIFIANT_ROLE_ADMIN = "Administrateur";
    public static final int NB_UTILISATEURS_FIND_BY_IDENTIFIANT_ROLE_ADMIN = 1;
    //FINDBY ROLE
    public static final int NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN = 1;
    public static final String ROLES_FIND_BY_IDENTIFIANT_ADMIN = "Administrateur";

    public static final int NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR = 1;
    public static final String ROLES_FIND_BY_IDENTIFIANT_ACHETEUR = "Acheteur";

    public static final int NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD = 1;
    public static final String ROLES_FIND_BY_IDENTIFIANT_STANDARD = "Standard";

    //FINDBY ADRESSE
    public static final int NB_ADRESSES_FIND_BY_RUE_33_RUE_DE_LA_CONSCIENCE = 3;
    public static final String ADRESSES_FIND_BY_RUE_33_RUE_DE_LA_CONSCIENCE = "33 rue de la conscience";

    public static final int NB_ADRESSES_FIND_BY_1_RUE_DE_GASTON = 1;
    public static final String ADRESSES_FIND_BY_1_RUE_DE_GASTON = "1 rue de Gaston";

    public static final String ADRESSES_FIND_BY_UTILISATEUR_IDENTIFIANT = "admin@gmail.com";
    public static final int NB_ADRESSES_FIND_BY_UTILISATEUR_IDENTIFIANT = 5;

    public static final String ADRESSES_FIND_BY_UTILISATEUR_IDENTIFIANT2 = "laurent.bordet@gmail.com";
    public static final int NB_ADRESSES_FIND_BY_UTILISATEUR_IDENTIFIANT2 = 2;

    //FINDBY ARTICLECOMMANDE
    public static final int ARTICLECOMMANDE_FIND_BY_COMMANDE_ID = 5;
    public static final int NB_ARTICLECOMMANDE_FIND_BY_COMMANDE_ID = 3;

    public static final int ARTICLECOMMANDE_FIND_BY_UTILISATEUR_ID = 4;
    public static final int NB_ARTICLECOMMANDE_FIND_BY_UTILISATEUR_ID = 486;

    public static final int ARTICLECOMMANDE_FIND_BY_ADRESSE_ID = 23;
    public static final int NB_ARTICLECOMMANDE_FIND_BY_ADRESSE_ID = 108;

    //FINDBY PRODUIT
    public static final String PRODUIT_FIND_BY_NOM_IPHONE_4 = "Iphone 4";
    public static final int NB_PRODUIT_FIND_BY_NOM_IPHONE_4 = 1;
    
    public static final String PRODUIT_FIND_BY_REFERENCE_REF_IPHONE_4 = "REF-IPHONE-4";
    
    //FINDBY COMMANDE    
   
    public static final String COMMANDE_FIND_BY_PRODUIT_NOM = "Canape en cuir";
    public static final int NB_COMMANDE_FIND_BY_PRODUIT_NOM = 189 ;
    
    public static final String COMMANDE_FIND_BY_PRODUIT_REFERENCE = "REF-CANAPE-ANGLE-CUIR";
    public static final int NB_COMMANDE_FIND_BY_PRODUIT_REFERENCE = 189;
    
    public static final String COMMANDE_FIND_BY_UTILISATEUR_IDENTIFIANT = "thomas.corgnet@gmail.com";
    public static final int NB_COMMANDE_FIND_BY_UTILISATEUR_IDENTIFIANT = 108;
}
