package com.wsspring.service;

import com.wsspring.model.Privilege;
import com.wsspring.model.Role;
import com.wsspring.repository.PrivilegeRepository;
import com.wsspring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PrivilegeService {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    public Privilege create(Privilege entity) {
        return privilegeRepository.save(entity);
    }

    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

}
