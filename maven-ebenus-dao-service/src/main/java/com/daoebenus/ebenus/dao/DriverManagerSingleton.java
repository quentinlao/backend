/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao;

import com.daoebenus.ebenus.dao.exception.EbenusException;
import com.daoebenus.ebenus.utils.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Quentin
 */
public class DriverManagerSingleton {

    private static final Log log = LogFactory.getLog(DriverManagerSingleton.class);

    public final static String className = DriverManagerSingleton.class.getName();
    // Url de connexion en base de donnée
    private static final String url = Constants.DATABASE_URL;

    // Utilisateur de la base de données
    private static final String user = Constants.DATABASE_USER;

    // Mot de passe de la base de données
    private static final String password = Constants.DATABASE_PASSWORD;

    // Permet d'effectuer la connection
    private static Connection connection;
    
    // Drivers Jdbc
    private static final String jdbcDriver = Constants.JDBC_DRIVER;
    
    // pour effectuer la connexion pour la jdbc
    private static Connection connexion = null;
    
    private DriverManagerSingleton() {
    }
    
    private static class DriverManagerSingletonHolder {
        private final static DriverManagerSingleton instance = new DriverManagerSingleton();
    }
    
    public static Connection getConnectionInstance() {
        if (connexion == null) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                //Class.forName("com.mysql.jdbc.Driver");
                connexion = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                throw new EbenusException("[ERROR] La connection à la base de donnée a échoué", e, 2);
            }
        }
       return connexion;
    }
    
    public static DriverManagerSingleton getInstance() {
       return DriverManagerSingletonHolder.instance ;
    }
}
