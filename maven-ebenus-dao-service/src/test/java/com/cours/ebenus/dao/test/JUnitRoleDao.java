/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.test;

import com.daoebenus.ebenus.dao.IRoleDao;
import com.daoebenus.ebenus.dao.entities.Role;
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
public class JUnitRoleDao {
    private static final Log log = LogFactory.getLog(JUnitDao.class);
    private static IServiceFacade serviceFacade = null;
    private static List<Role> roles = null;

    @BeforeClass
    public static void init() throws Exception {
        log.debug("Initialisation des test");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        IRoleDao roleDao = serviceFacade.getRoleDao();
        roles = roleDao.findAllRoles();
    }
    
    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        //uniquement pour les vraies tests sinon c'est long
        Fonctions.runSqlScript(scriptSqlPath);
    }
    
    public static void verifyRoleData(Role role) {
        log.debug("Entree de la methode verifyRoleData");
        if (role != null) {
            log.debug("idRole : " + role.getIdRole());
            Assert.assertNotNull(role.getIdRole());
            Assert.assertNotNull(role.getIdentifiant());
            Assert.assertNotNull(role.getDescription());
        } else if (role == null) {
            Assert.fail("Role null");
        }
        log.debug("Sortie de la methode verifyRoleData");
    }
        
    @Test
    public void testFindAllRoles() {
        log.debug("Entree de la methode testFindAllRoles");
        if (roles != null) {
            log.debug("NB_ROLES_LIST: " + Constants.NB_ROLES_LIST + " , roles.size(): " + roles.size());
            Assert.assertEquals(Constants.NB_ROLES_LIST, roles.size());
        } else {
            Assert.fail("Aucun Role n'a ete trouves dans votre base de donnÃ©es");
        }
        log.debug("Sortie de la methode testFindAllRoles");
    }
    
    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode testFindByCriteria");
        
        List<Role> rolesByIdentifiantAdmin = serviceFacade.getRoleDao().findRoleByIdentifiant(Constants.ROLES_FIND_BY_IDENTIFIANT_ADMIN);
        List<Role> rolesByIdentifiantAcheteur = serviceFacade.getRoleDao().findRoleByIdentifiant(Constants.ROLES_FIND_BY_IDENTIFIANT_ACHETEUR);
        List<Role> rolesByIdentifiantStandard = serviceFacade.getRoleDao().findRoleByIdentifiant(Constants.ROLES_FIND_BY_IDENTIFIANT_STANDARD);
        
        //rolesList
        if (rolesByIdentifiantAdmin != null) {
            log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN: " + Constants.NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN + " , rolesByIdentifiantAdmin.size(): " + rolesByIdentifiantAdmin.size());
            Assert.assertEquals(Constants.NB_ROLES_FIND_BY_IDENTIFIANT_ADMIN, rolesByIdentifiantAdmin.size());
        } else {
            Assert.fail("Aucun rÃ´le avec l'identifiant " + Constants.ROLES_FIND_BY_IDENTIFIANT_ADMIN + "' n'a ete trouve dans votre base de donnÃ©es");
        }
        if (rolesByIdentifiantAcheteur != null) {
            log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR: " + Constants.NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR + " , rolesByIdentifiantAcheteur.size(): " + rolesByIdentifiantAcheteur.size());
            Assert.assertEquals(Constants.NB_ROLES_FIND_BY_IDENTIFIANT_ACHETEUR, rolesByIdentifiantAcheteur.size());
        } else {
            Assert.fail("Aucun rÃ´le avec l'identifiant " + Constants.ROLES_FIND_BY_IDENTIFIANT_ACHETEUR + "' n'a ete trouve dans votre base de donnÃ©es");
        }
        if (rolesByIdentifiantStandard != null) {
            log.debug("NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD: " + Constants.NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD + " , rolesByIdentifiantStandard.size(): " + rolesByIdentifiantStandard.size());
            Assert.assertEquals(Constants.NB_ROLES_FIND_BY_IDENTIFIANT_STANDARD, rolesByIdentifiantStandard.size());
        } else {
            Assert.fail("Aucun rÃ´le avec l'identifiant " + Constants.ROLES_FIND_BY_IDENTIFIANT_STANDARD + "' n'a ete trouve dans votre base de donnÃ©es");
        }
    }
    
    @Test
    public void testCreateUpdateDeleteRole() {
        log.debug("Entree de la methode testCreateUpdateDeleteRole");
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        verifyRoleData(roleCRUD);
        log.debug("Created roleCRUD : " + roleCRUD);
        log.debug("Created roleCRUD.getIdRole : " + roleCRUD.getIdRole());
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNotNull(roleCRUD);
        roleCRUD.setIdentifiant("Superviseur Bis");
        roleCRUD.setDescription("Le rôle superviseur Bis");
        roleCRUD = serviceFacade.getRoleDao().updateRole(roleCRUD);
        Assert.assertNotNull(roleCRUD);
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        log.debug("Updated roleCRUD : " + roleCRUD);
        Assert.assertEquals("Superviseur Bis", roleCRUD.getIdentifiant());
        Assert.assertEquals("Le rôle superviseur Bis", roleCRUD.getDescription());
        Assert.assertTrue(serviceFacade.getRoleDao().deleteRole(roleCRUD));
        roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
        Assert.assertNull(roleCRUD);
        List<Role> rolesFinal = serviceFacade.getRoleDao().findAllRoles();
        if (rolesFinal != null) {
            Assert.assertEquals(Constants.NB_ROLES_LIST, rolesFinal.size());
            log.debug("rolesFinal.size() : " + rolesFinal.size() + " , NB_ROLES_LIST: " + Constants.NB_ROLES_LIST);
        }
        log.debug("Sortie de la methode testCreateUpdateDeleteRole");
    
    }
    
    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode terminate");
        serviceFacade = null;
        roles = null;
        log.debug("Sortie de la methode terminate");
    }

}
