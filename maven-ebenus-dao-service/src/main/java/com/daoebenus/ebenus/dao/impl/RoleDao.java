/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daoebenus.ebenus.dao.impl;

import com.daoebenus.ebenus.dao.ConnectionHelper;
import com.daoebenus.ebenus.dao.DriverManagerSingleton;
import com.daoebenus.ebenus.dao.IRoleDao;
import com.daoebenus.ebenus.dao.entities.Role;
import com.daoebenus.ebenus.dao.exception.EbenusException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Quentin
 */
public class RoleDao /*extends AbstractDao<Role>*/ implements IRoleDao {

    private static final Log log = LogFactory.getLog(RoleDao.class);

    //public RoleDao() {
    //    super(Role.class);
    //}
    private void fillRole(ResultSet results, Role role) {
        if (results != null && role != null) {
            try {
                role.setIdRole(results.getInt("idRole"));
                role.setVersion(results.getInt("version"));
                if (results.getString("identifiant") != null) {
                    role.setIdentifiant(results.getString("identifiant"));
                }
                if (results.getString("description") != null) {
                    role.setDescription(results.getString("description"));
                }
                /*if (role.getIdentifiant() != null) {
                    log.info("le role " + role.getIdentifiant() + " a été rempli avec succès");
                }
                */
            } catch (Exception e) {
                throw new EbenusException("échec lors de la récupération d'un role", e, 4);
            }
        }
    }
    @Override
    public List<Role> findAllRoles() {
        List<Role> rolesToFind = new ArrayList();
        Role roleToAdd = null;
        String sqlRequest = "SELECT * FROM role";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = null;
        try {
            connexion = DriverManagerSingleton.getConnectionInstance();
            preparedStatement = connexion.prepareStatement(sqlRequest); 
            results = preparedStatement.executeQuery();
            while (results.next()) {
                roleToAdd = new Role();
                fillRole(results, roleToAdd);
                if (roleToAdd != null) {
                    rolesToFind.add(roleToAdd);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllRole()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return rolesToFind;
    }

    @Override
    public Role findRoleById (int idRole) {
    Role roleToFind = null;
        String sqlRequest = "SELECT * FROM Role WHERE idRole = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = DriverManagerSingleton.getConnectionInstance();
        try {
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, idRole);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                roleToFind = new Role();
                fillRole(results, roleToFind);
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllRole()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return roleToFind;
    }

    @Override
    public List<Role> findRoleByIdentifiant (String identifiantRole) {
    List<Role> rolesToFind = new ArrayList();
        Role roleToFind = null;
        String sqlRequest = "SELECT * FROM role WHERE identifiant = ?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = DriverManagerSingleton.getConnectionInstance();
        try {
            preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setString(1, identifiantRole);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                roleToFind = new Role();
                fillRole(results, roleToFind);
                if (roleToFind != null) {
                    rolesToFind.add(roleToFind);
                }
            }
        } catch (Exception e) {
            throw new EbenusException("Echec lors du findAllRole()", e, 5);
        } finally {
            ConnectionHelper.closeSqlResources(preparedStatement, results);
        }
        return rolesToFind;
    }
    
    private boolean roleExistByIdentifiant(Role role) {
        boolean roleExist = false;
        if (role != null) {
            if (role.getIdentifiant() != null) {
                if (findRoleByIdentifiant(role.getIdentifiant()).size() > 0) {
                    roleExist = true;
                }
            }
        }
        return roleExist;
    }

    private boolean roleHaveId(Role role) {
        boolean roleHaveId = false;
        if (role != null) {
            if (role.getIdRole() != null) {
                if (role.getIdRole() > 0) {
                    roleHaveId = true;
                }
            }
        }
        return roleHaveId;
    }

    @Override
    public Role createRole(Role role) {
        String sqlRequest = "";
        PreparedStatement preparedStatement = null;
        Connection connexion = null;
        ResultSet results = null;
        if (!roleExistByIdentifiant(role)) {
            if (!roleHaveId(role)) {
                sqlRequest = "INSERT INTO Role (identifiant, description) VALUES(?, ?)";
                connexion = DriverManagerSingleton.getConnectionInstance();
                try {
                    preparedStatement = connexion.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS);
                    if (role != null) {
                        if (role.getIdentifiant() != null) {
                            preparedStatement.setString(1, role.getIdentifiant());
                        }
                        if (role.getDescription() != null) {
                            preparedStatement.setString(2, role.getDescription());
                        }
                        preparedStatement.executeUpdate();
                        results = preparedStatement.getGeneratedKeys(); //permet de récupérer l'idRole pour le return
                        if (results.next()) {
                            role.setIdRole((int) results.getLong(1));
                        }
                    }
                } catch (Exception e) {
                    throw new EbenusException("l'ajout d'un role a échoué", e, -1);
                } finally {
                    ConnectionHelper.closeSqlResources(preparedStatement, null);
                }
            }
        }
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        String sqlRequest = "";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        //if (!roleExistByIdentifiant(role)) {
            if (roleHaveId(role)) {
                try {
                    connexion = DriverManagerSingleton.getConnectionInstance();
                    sqlRequest = "UPDATE Role SET identifiant=?, description=? WHERE idRole=?";
                    preparedStatement = connexion.prepareStatement(sqlRequest);
                    if (role.getIdentifiant() != null) {
                        preparedStatement.setString(1, role.getIdentifiant());
                    }
                    if (role.getDescription() != null) {
                        preparedStatement.setString(2, role.getDescription());
                    }
                    preparedStatement.setInt(3, role.getIdRole());
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    throw new EbenusException("la mise à jour du role a échoué", e, 8);
                } finally {
                    ConnectionHelper.closeSqlResources(preparedStatement, results);
                }
        //    }
        }
        return role;
    }

    @Override
    public boolean deleteRole(Role role) {
        boolean roleDeleted = true;
        String sqlRequest = "DELETE FROM Role WHERE idRole=?";
        PreparedStatement preparedStatement = null;
        ResultSet results = null;
        Connection connexion = DriverManagerSingleton.getConnectionInstance();
        if (roleHaveId(role)) {
            try {
                preparedStatement = connexion.prepareCall(sqlRequest);
                preparedStatement.setInt(1, role.getIdRole()); //pas besoin de chehk nullité deja fait avec roleHaveId
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                throw new EbenusException("la suppression d'une role a échoué", e, 6);
            } finally {
                ConnectionHelper.closeSqlResources(preparedStatement, results);
            } 
            if (findRoleById(role.getIdRole()) != null) {
            roleDeleted = false;
        }
       
        }
        return roleDeleted;
    }
}
