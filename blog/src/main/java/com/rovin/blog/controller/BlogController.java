package com.rovin.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final static Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping
    public String listBlogs(@RequestParam(value="order", required = false, defaultValue = "new") String order,
                            @RequestParam(value="keyword", required = false, defaultValue = "") String keyword) {
        logger.info("order=[{}], keyword=[{}]", order, keyword);
        return "redirect:/index?order="+order+"&keyword="+keyword;
    }
}
