/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.factory;

import com.daoebenus.ebenus.dao.IAdresseDao;
import com.daoebenus.ebenus.dao.IArticleCommandeDao;
import com.daoebenus.ebenus.dao.ICommandeDao;
import com.daoebenus.ebenus.dao.IProduitDao;
import com.daoebenus.ebenus.dao.IRoleDao;
import com.daoebenus.ebenus.dao.IUtilisateurDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Quentin
 */
public abstract class AbstractDaoFactory {

    public static String className = AbstractDaoFactory.class.getName();
    private static final Log log = LogFactory.getLog(AbstractDaoFactory.class);

    public enum FactoryDaoType {

        JDBC_DAO_FACTORY;
    }

    public abstract IUtilisateurDao getUtilisateurDao();

    public abstract IRoleDao getRoleDao();
    
    public abstract IAdresseDao getAdresseDao();
    
    public abstract ICommandeDao getCommandeDao();
    
    public abstract IProduitDao getProduitDao();
    
    public abstract IArticleCommandeDao getArticleCommandeDao();

    /**
     * Méthode pour récupérer une factory de DAO
     *
     * @param daoType
     * @return AbstractDaoFactory
     */
    public static AbstractDaoFactory getFactory(FactoryDaoType daoType) {
        AbstractDaoFactory daoFactoryToReturn = null;
        if (FactoryDaoType.JDBC_DAO_FACTORY.equals(daoType)) {
            daoFactoryToReturn = new DaoFactory();
        }
        return daoFactoryToReturn;
    }
}
