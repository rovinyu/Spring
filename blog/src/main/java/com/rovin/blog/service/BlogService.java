package com.rovin.blog.service;

import com.rovin.blog.domain.Blog;
import com.rovin.blog.domain.Catalog;
import com.rovin.blog.domain.EsBlog;
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

    @Autowired
    private EsBlogService esBlogService;

    @Transactional
    public Blog saveBlog(Blog blog) {

        boolean isNew = (blog.getId() == null);
        EsBlog esBlog = null;

        Blog returnBlog = blogRepository.save(blog);

        if (isNew) {
            esBlog = new EsBlog(returnBlog);
        } else {
            esBlog = esBlogService.getEsBlogByBlogId(blog.getId());
            esBlog.update(returnBlog);
        }

        try {
            esBlogService.updateEsBlog(esBlog);
        } catch (Exception e) {
            //Don't throw the exception to the upper layer
            e.printStackTrace();
        }

        return returnBlog;
    }

    @Transactional
    public void removeBlog(Long id) {

        blogRepository.deleteById(id);
        try {
            EsBlog esBlog = esBlogService.getEsBlogByBlogId(id);
            esBlogService.removeEsBlog(esBlog.getId());
        } catch (Exception e) {
            // Don't throw the exception to the upper layer
            e.printStackTrace();
        }
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Page<Blog> listBlogsByTitle(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user,title, pageable);
        return blogs;
    }

    public Page<Blog> listBlogsByCategory(Catalog catalog, Pageable pageable) {

        Page<Blog> blogs = blogRepository.findByCatalog(catalog,pageable);
        return blogs;
    }

}
