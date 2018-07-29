package com.rovin.blog.service;

import com.rovin.blog.domain.Authority;
import com.rovin.blog.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public Optional<Authority> getAuthorityById(Long id) {
        return authorityRepository.findById(id);
    }
}
