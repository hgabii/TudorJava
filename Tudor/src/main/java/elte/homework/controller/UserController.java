package elte.homework.controller;

import elte.homework.data.User;
import elte.homework.data.UserRepository;
import elte.homework.model.PasswordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

@RestController
@RequestMapping("user")
@Transactional
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userDao;

    @GetMapping("/self")
    public String selfUser(Authentication auth) {

        logger.debug("Self user invoked - Name: " + auth.getName());
        User user = userDao.getOne(auth.getName());
        return user.toString();
    }

    @GetMapping("/get/{userid}")
    public String getUserById(@PathVariable("userid") String userName, Authentication auth) {

        logger.debug("Get user by id - UserName: " + userName);
        User user = userDao.getOne(userName);
        return user.toString();
    }

    @PostMapping("/password/self")
    public ResponseEntity<Void> setSelfpassword(@RequestBody PasswordDTO password, Authentication auth) {

        logger.debug("Set self password - Name: " + auth.getName());
        return setPassword(auth.getName(), password, auth);
    }

    @PostMapping("/password/{userId}")
    public ResponseEntity<Void> setPassword(@PathVariable("userid") String userName,
                                            @RequestBody PasswordDTO password, Authentication auth) {

        logger.debug("Set password - UserName: " + userName);
        User user = userDao.getOne(userName);
        if(!userName.equals( auth.getName() ) &&
                !auth.getAuthorities().contains( User.getRole(User.UserType.ADMIN) )) {
            throw new AccessDeniedException("Invalid access to password");
        }
        user.setPassword( "{noop}"+ password.getNewPassword() );
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createUser(@RequestBody User user, Authentication auth) {

        logger.debug("Create user - UserName: " + user.getUserName());
        if(userDao.existsById(user.getUserName())) {
            throw new EntityExistsException("User with this name alredy exist");
        }
        if(!user.getPassword().startsWith("{noop}")) {
            user.setPassword("{noop}" + user.getPassword());
        }
        userDao.save(user);
        logger.info("User created - UserName: " + user.getUserName() + " UserId: " + user.getUserId());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{userid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userid") String userName, Authentication auth) {

        logger.debug("Delete user - UserName: " + userName);
        User user = userDao.getOne(userName);
        userDao.delete(user);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
