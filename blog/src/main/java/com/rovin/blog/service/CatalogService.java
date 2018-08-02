package com.rovin.blog.service;

import com.rovin.blog.domain.Catalog;
import com.rovin.blog.domain.User;
import com.rovin.blog.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    public Catalog saveCatalog(Catalog catalog) {

        List<Catalog> list = catalogRepository.findByUserAndName(catalog.getUser(), catalog.getName());

        if (list != null && list.size() > 0) {
            throw new IllegalArgumentException("This catalog has already existed");
        }

        return catalogRepository.save(catalog);
    }

    public void removeCatalog(Long id) {
        catalogRepository.deleteById(id);
    }

    public Optional<Catalog> getCatalogById(Long id) {
        return catalogRepository.findById(id);
    }

    public List<Catalog> listCatalogs(User user) {
        return catalogRepository.findByUser(user);
    }

    public List<Catalog> listCatalogbyName(User user, String name) {
        return catalogRepository.findByUserAndName(user, name);
    }

}
