package com.example.producer;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerController {

    private ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Value("${app.message}")
    private String message;
    @RequestMapping(value = "/send-message", method = RequestMethod.POST)
    public String sendMessage(@RequestBody User user) {

        producerService.send(user);
        return message;
    }
}
