package com.sonego.PhotoShare.persistence.repository;

import com.sonego.PhotoShare.business.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    @Query("FROM Profile p " +
            "WHERE LOWER(p.name) like %:nameString%")
    public Page<Profile> search(String nameString, Pageable pageable);

    public Optional<Profile> findByName(String name);
}
