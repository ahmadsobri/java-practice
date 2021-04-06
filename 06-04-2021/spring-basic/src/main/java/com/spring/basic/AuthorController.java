package com.spring.basic;

import com.spring.basic.model.Author;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthorController {
    private List<Author> authors = new ArrayList<>();

    @PostMapping(value = "/post-author")
    public String addAuthor(@RequestBody Author author){
        this.authors.add(author);
        return "add author successfully";
    }

    @PutMapping(value = "/put-author")
    public Object updateAuthor(@RequestBody Author author){
        Author result = findByIdAuthor(author.getId());
        result.setId(author.getId());
        result.setFull_name(author.getFull_name());
        result.setAge(author.getAge());

        return new Object(){
            public final String message = "put author successfully";
            public final Author author = result;
        };
    }

    @DeleteMapping("/delete-author")
    public String deleteAuthor(@RequestParam(value = "id") int id){
        Author author = findByIdAuthor(id);
        this.authors.remove(author);

        return "delete author successfully";
    }

    @RequestMapping(value = "/get-all-author", method = RequestMethod.GET)
    public List<Author> getAllAuthor(){
        return this.authors;
    }

    @GetMapping("/get-author/{id}")
    public Author findByIdAuthor(@RequestParam(value = "id") int id_author){
        Author result = this.authors.stream().filter(c -> id_author ==c.getId())
                .findAny()
                .orElse(null);
        return result;
    }
}
