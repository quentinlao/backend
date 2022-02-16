/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.service;

import com.daoebenus.ebenus.dao.IAdresseDao;
import com.daoebenus.ebenus.dao.IArticleCommandeDao;
import com.daoebenus.ebenus.dao.ICommandeDao;
import com.daoebenus.ebenus.dao.IProduitDao;
import com.daoebenus.ebenus.dao.IRoleDao;
import com.daoebenus.ebenus.dao.IUtilisateurDao;
import com.daoebenus.ebenus.dao.entities.Role;
import com.daoebenus.ebenus.factory.AbstractDaoFactory;
import com.daoebenus.ebenus.factory.AbstractDaoFactory.FactoryDaoType;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Quentin
 */
public class ServiceFacade implements IServiceFacade {

    private static final Log log = LogFactory.getLog(ServiceFacade.class);
    private final AbstractDaoFactory.FactoryDaoType DEFAULT_IMPLEMENTATION = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;

    // On liste toutes les DAO : un DAO pour chaque entité (Utilisateur,Role ect ....)
    private IUtilisateurDao utilisateurDao = null;

    private IRoleDao roleDao = null;

    private ICommandeDao commandeDao = null;
    
    private IProduitDao produitDao = null;
    
    private IArticleCommandeDao articleCommandeDao = null;
    
    private IAdresseDao adresseDao = null;
    
    public ServiceFacade() {
        // mettre tous les DAO
        utilisateurDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getUtilisateurDao();
        roleDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getRoleDao();
        commandeDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getCommandeDao();
        adresseDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getAdresseDao();
        produitDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getProduitDao();
        articleCommandeDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getArticleCommandeDao();
    }

    public ServiceFacade(FactoryDaoType daoType) {
        // mettre tous les DAO
        utilisateurDao = AbstractDaoFactory.getFactory(daoType).getUtilisateurDao();
        roleDao = AbstractDaoFactory.getFactory(daoType).getRoleDao();
        commandeDao = AbstractDaoFactory.getFactory(daoType).getCommandeDao();
        adresseDao = AbstractDaoFactory.getFactory(daoType).getAdresseDao();
        produitDao = AbstractDaoFactory.getFactory(daoType).getProduitDao();
        articleCommandeDao = AbstractDaoFactory.getFactory(daoType).getArticleCommandeDao();
    }

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    @Override
    public IRoleDao getRoleDao() {
        return roleDao;
    }

    @Override
    public ICommandeDao getCommandeDao() {
        return commandeDao;
    }

    @Override
    public IProduitDao getProduitDao() {
        return produitDao;
    }

    @Override
    public IArticleCommandeDao getArticleCommandeDao() {
        return articleCommandeDao;
    }

    @Override
    public IAdresseDao getAdresseDao() {
        return adresseDao;
    }
    
    
}
