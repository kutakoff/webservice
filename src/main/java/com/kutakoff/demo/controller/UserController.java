package com.kutakoff.demo.controller;

import com.kutakoff.demo.User;
import com.kutakoff.demo.repos.UserDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    public static UserDAO userDAO = new UserDAO();

    @GetMapping("/user")
    public List<User> getUsers() {
        return userDAO.getAll();
    }
}
