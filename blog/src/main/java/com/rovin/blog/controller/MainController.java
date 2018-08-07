package com.rovin.blog.controller;

import com.rovin.blog.domain.Authority;
import com.rovin.blog.domain.User;
import com.rovin.blog.service.AuthorityService;
import com.rovin.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {

        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                .toString().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return "redirect:/u/" + principal.getUsername() + "/blogs";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "Login Error: User name or password Error!");
        return "login";
    }

    @GetMapping("/register-error")
    public String registerError(Model model) {
        model.addAttribute("registerError", true);
        model.addAttribute("errorMsg", "Register Error: Username or email violation Error!");
        return "register";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID)
        .get());
        user.setAuthorities(authorities);
        try {
            userService.registerUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register-error";
        }
        return "redirect:/login";
    }

}
