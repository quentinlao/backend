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
import com.daoebenus.ebenus.dao.impl.AdresseDao;
import com.daoebenus.ebenus.dao.impl.ArticleCommandeDao;
import com.daoebenus.ebenus.dao.impl.CommandeDao;
import com.daoebenus.ebenus.dao.impl.ProduitDao;
import com.daoebenus.ebenus.dao.impl.RoleDao;
import com.daoebenus.ebenus.dao.impl.UtilisateurDao;

/**
 *
 * @author Quentin
 */
public class DaoFactory extends AbstractDaoFactory {

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return new UtilisateurDao();
    }

    @Override
    public IRoleDao getRoleDao() {
        return new RoleDao();
    }

    @Override
    public IAdresseDao getAdresseDao() {
        return new AdresseDao();
    }

    @Override
    public ICommandeDao getCommandeDao() {
        return new CommandeDao();
    }

    @Override
    public IArticleCommandeDao getArticleCommandeDao() {
        return new ArticleCommandeDao();
    }

    @Override
    public IProduitDao getProduitDao() {
        return new ProduitDao();
    }

}
