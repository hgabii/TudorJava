package elte.homework.controller;

import elte.homework.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("user")
@Transactional
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    //@Autowired
    //private UserRepository userDao;

    /*@GetMapping("/self")
    public String selfUser(Authentication auth) {
        User user = userDao.getOne(auth.getName());
        return user.toString();
    }*/

    @PostMapping("/new")
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        return null;
    }
}
