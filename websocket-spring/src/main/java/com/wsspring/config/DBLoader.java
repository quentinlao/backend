package com.wsspring.config;

import com.wsspring.model.Privilege;
import com.wsspring.model.Role;
import com.wsspring.model.User;
import com.wsspring.repository.PrivilegeRepository;
import com.wsspring.repository.RoleRepository;
import com.wsspring.repository.UserRepository;
import com.wsspring.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class DBLoader implements CommandLineRunner {
    UserRepository repository;
    RoleRepository roleRepository;
    PrivilegeRepository privilegeRepository;

    public DBLoader(UserRepository repository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        repository.deleteAll();
        roleRepository.deleteAll();
        privilegeRepository.deleteAll();

    }

    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    public void run(String... strings) throws Exception {
        // Encode password
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

        // Create PRIVILEGE
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);

        // Create ROLE
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role useRole = roleRepository.findByName("ROLE_USER");

        // Create USER
        User user = new User("User 1",
                "user1",
                "user1@example.com",
                "user1",
                true,
                Arrays.asList(adminRole)
        );
        User user2 = new User("User 2",
                "user2",
                "user2@example.com",
                "user2",
                true,
                Arrays.asList(useRole)
        );

        List<User> userList = new ArrayList() {
            {
                add(user);
                add(user2);
            }
        };

        userList.forEach(u -> {
            String hashedPassword = passEncoder.encode(u.getPassword());
            u.setPassword(hashedPassword);
        });

        repository.save(userList);
    }
}
