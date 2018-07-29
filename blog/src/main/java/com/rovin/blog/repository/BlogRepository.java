package com.rovin.blog.repository;

import com.rovin.blog.domain.Blog;
import com.rovin.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    Page<Blog> findByCategoryLikeAndUserOrderByCreateTimeDesc(String category, User user, Pageable pageable);
}
