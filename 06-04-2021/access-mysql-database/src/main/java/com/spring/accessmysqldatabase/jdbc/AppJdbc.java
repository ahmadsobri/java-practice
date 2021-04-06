package com.spring.accessmysqldatabase.jdbc;

import com.spring.accessmysqldatabase.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class AppJdbc {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/jdbc-get-all", method = RequestMethod.GET)
    public List<Author> getAll(){
        String sql = "SELECT * FROM author";

        // using builtin method BeanPropertyRowMapper
        List<Author> results = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Author.class));

        // using custom Mapper
        List<Author> authors = jdbcTemplate.query(sql,new AuthorRowMapper());

        return authors;
    }

    class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setAge(rs.getInt("age"));
            author.setFull_name(rs.getString("full_name"));

            return author;
        }
    }
}
