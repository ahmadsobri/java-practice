package com.spring.springbasic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringBasicApplicationTests {

    @Test
    void contextLoads() {
        Optional<String> opt = Optional.of("Baeldung");
        assertFalse(opt.isEmpty());
        //assertTrue(opt.isEmpty());

        opt = Optional.ofNullable(null);
        assertTrue(opt.isEmpty());
    }

    @Test
    public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
        String name = null;
        //Optional.of(name);

        name = "baeldung";
        Optional<String> opt = Optional.of(name);
        assertTrue(opt.isPresent());
    }

}
