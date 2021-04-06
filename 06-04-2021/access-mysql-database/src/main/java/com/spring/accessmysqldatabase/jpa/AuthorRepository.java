package com.spring.accessmysqldatabase.jpa;

import com.spring.accessmysqldatabase.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
