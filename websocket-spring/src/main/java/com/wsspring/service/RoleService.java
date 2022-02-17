package com.wsspring.service;

import com.wsspring.model.Privilege;
import com.wsspring.model.Role;
import com.wsspring.repository.PrivilegeRepository;
import com.wsspring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public Role create(Role entity) {
        return roleRepository.save(entity);
    }

    public Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

}
