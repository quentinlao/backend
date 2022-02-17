package com.wsspring.repository;

import com.wsspring.model.Privilege;
import com.wsspring.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {
    @Query("SELECT p FROM Privilege p WHERE p.name = :name")
    Privilege findByName(@Param("name") String name);
}
