package com.rovin.blog.controller;

import com.rovin.blog.domain.EsBlog;
import com.rovin.blog.domain.User;
import com.rovin.blog.service.EsBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private EsBlogService esBlogService;

    private final static Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping
    public String listEsBlogs(@RequestParam(value="order", required = false, defaultValue = "new") String order,
                              @RequestParam(value="keyword", required = false, defaultValue = "") String keyword,
                              @RequestParam(value="async", required = false) boolean async,
                              @RequestParam(value="pageIndex", required = false, defaultValue = "0") int pageIndex,
                              @RequestParam(value="pageSize", required = false,defaultValue = "10") int pageSize,
                              Model model) {

        Page<EsBlog> page = null;
        List<EsBlog> list = null;
        boolean isEmpty = true;

        try {
            Sort sort = new Sort(Sort.Direction.DESC, "createTime");
            Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
            if (keyword != null && !keyword.equals("")) {
                page = esBlogService.listNewestEsBlogs(keyword, pageable);
            } else {
                page = esBlogService.listNewestEsBlogs("*", pageable);
            }
            isEmpty = false;
        } catch (Exception e) {
            try {
                Pageable pageable = PageRequest.of(pageIndex, pageSize);
                page = esBlogService.listEsBlogs(pageable);
                isEmpty = false;
            } catch (Exception e1) {
                page = null;
                isEmpty = true;
            }
        }

        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);

        if (page != null) {
            list = page.getContent();
            model.addAttribute("page", page);
            model.addAttribute("blogList", list);
        }

        if(!async && !isEmpty) {
            List<EsBlog> newest = esBlogService.listTop5NewestEsBlogs();
            model.addAttribute("newest", newest);
            List<User> users = esBlogService.listTop12Users();
            model.addAttribute("users",users);
        }

        logger.info("order=[{}], keyword=[{}]", order, keyword);
        return (async==true?"/index :: #mainContainerReplace":"/index");
    }
}
