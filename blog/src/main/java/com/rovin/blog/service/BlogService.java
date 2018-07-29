package com.rovin.blog.service;

import com.rovin.blog.domain.Blog;
import com.rovin.blog.domain.User;
import com.rovin.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    public Blog saveBlog(Blog blog) {
        Blog returnBlog = blogRepository.save(blog);
        return returnBlog;
    }

    @Transactional
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Page<Blog> listBlogsByTitle(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user,title, pageable);
        return blogs;
    }

    public Page<Blog> listBlogsByCategory(User user, String category, Pageable pageable) {
        category = "%"+category+"%";
        Page<Blog> blogs = blogRepository.findByCategoryLikeAndUserOrderByCreateTimeDesc(category, user, pageable);
        return blogs;
    }

}
