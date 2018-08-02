package com.rovin.blog.repository;

import com.rovin.blog.domain.Catalog;
import com.rovin.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    List<Catalog> findByUser(User user);

    List<Catalog> findByUserAndName(User user, String name);
}
