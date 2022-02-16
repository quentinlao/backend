/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao;

import com.daoebenus.ebenus.dao.exception.EbenusException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Quentin
 */
public class ConnectionHelper {

    private static final Log log = LogFactory.getLog(ConnectionHelper.class);
    public final static String className = ConnectionHelper.class.getName();

    /* les ressources sont fermées pour éviter les fuites mémoires */
    public static void closeSqlResources(PreparedStatement preparedStatement, ResultSet result) {
        
        if (preparedStatement != null ){
            try {
                preparedStatement.close();
            } catch (Exception e) {
                throw new EbenusException("la ressource SQL n'a pas pu etre fermé correctment", e, 3);
            }
        }
        
        if (result != null) {
            try {
                result.close();
            } catch (Exception e) {
                throw new EbenusException("le resultSet ne s'est pas fermé correstement", e, 4);
            }
        } 
        
    }
}
