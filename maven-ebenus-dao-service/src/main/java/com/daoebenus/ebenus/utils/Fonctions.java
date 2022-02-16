/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.utils;

import com.daoebenus.ebenus.dao.DriverManagerSingleton;
import com.daoebenus.ebenus.dao.exception.EbenusException;
import com.ibatis.common.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Statement;



/**
 *
 * @author Quentin
 */
public class Fonctions {
    
    public static java.sql.Date convertUtilToSqlDate(java.util.Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
    public static java.sql.Timestamp convertUtilToSqlTimestamp(java.util.Date date) {
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(date.getTime());
        return sqlTimestamp;
    }
    
    public static java.util.Date convertSqlToUtilDate(java.sql.Date date) {
        java.util.Date utilDate = new java.util.Date(date.getTime());
        return utilDate;
    }
    
    public static java.util.Date convertTimestampToUtilDate(java.sql.Timestamp date) {
        java.util.Date utilDate = new java.util.Date(date.getTime());
        return utilDate;
    }
    public static void runSqlScript (String path){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connexion = DriverManagerSingleton.getConnectionInstance();
            ScriptRunner sr = new ScriptRunner(connexion, false, false);
            Reader reader = new BufferedReader(new FileReader(path));
            sr.runScript(reader);
        } catch (Exception e) {
            throw new EbenusException("erreur lors de l'éxécution du script", e, -2);
        }
    }
    
}
