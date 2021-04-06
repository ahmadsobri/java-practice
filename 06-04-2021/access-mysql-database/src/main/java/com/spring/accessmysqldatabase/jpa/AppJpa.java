package com.spring.accessmysqldatabase.jpa;

import com.spring.accessmysqldatabase.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppJpa {
    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/jpa-get-all")
    public List<Author> getAll(){
        return authorRepository.findAll();
    }
}
