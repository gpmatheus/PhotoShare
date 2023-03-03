package com.sonego.PhotoShare.persistence.repository;

import com.sonego.PhotoShare.business.model.ERole;
import com.sonego.PhotoShare.business.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(ERole name);
}
