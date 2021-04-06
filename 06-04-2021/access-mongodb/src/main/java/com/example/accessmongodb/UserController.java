package com.example.accessmongodb;

import com.example.accessmongodb.document.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}.", userId);
        return userRepository.findById(userId).orElse(null);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Saving user.");
        return userRepository.save(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User updateUsers(@RequestBody User user) {
        LOG.info("Updating user.");
        User current = userRepository.findById(user.getUserId()).orElse(null);
        current.setName(user.getName());
        current.setUserSettings(user.getUserSettings());
        return userRepository.save(current);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUsers(@PathVariable String id) {
        LOG.info("Deleting user.");
        userRepository.deleteById(id);
        return "Successfully Deleted user";
    }

    @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getUserSettings();
        } else {
            return "User not found.";
        }
    }
}
