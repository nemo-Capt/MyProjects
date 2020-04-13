package com.restproject.controller;
import com.restproject.entity.User;
import com.restproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{surname}")
    public User find(@PathVariable("surname") String surname) {
        return repository.findOne(surname);
    }

    @PostMapping(consumes = "application/json")
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping(path = "/{surname}")
    public void delete(@PathVariable("surname") String surname) {
        repository.delete(surname);
    }

    @PutMapping(path = "/{surname}")
    public User update(@PathVariable("surname") String surname, @RequestBody User user) throws BadHttpRequest {
        if (repository.exists(surname)) {
            user.setSurname(surname);
            return repository.save(user);
        } else {
            throw new BadHttpRequest();
        }
    }

}
