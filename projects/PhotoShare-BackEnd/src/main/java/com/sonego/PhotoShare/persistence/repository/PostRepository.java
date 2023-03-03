package com.sonego.PhotoShare.persistence.repository;

import com.sonego.PhotoShare.business.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("select p from Post p order by RAND()")
    public Page<Post> getRandomPosts(Pageable pageable);
}
