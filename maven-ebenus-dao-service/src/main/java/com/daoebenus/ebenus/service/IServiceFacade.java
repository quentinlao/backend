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

/**
 *
 * @author Quentin
 */
public interface IServiceFacade {

    public IUtilisateurDao getUtilisateurDao();

    public IRoleDao getRoleDao();
    
    public ICommandeDao getCommandeDao();
    
    public IProduitDao getProduitDao();
    
    public IArticleCommandeDao getArticleCommandeDao();
    
    public IAdresseDao getAdresseDao();
}
