package elte.homework.controller;

import elte.homework.data.Admin;
import elte.homework.data.AdminRepository;
import elte.homework.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin")
@Transactional
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminRepository adminDao;

    @GetMapping("/list")
    public ResponseEntity<List<Admin>> listAdmins(Authentication auth) {

        List<Admin> admins = adminDao.findAll();
        logger.debug("List admins - Count: " + admins.size());
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Get admin - Id: " + id);
        Optional<Admin> admin = adminDao.findById(id);
        if (admin.isPresent()) {
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public  ResponseEntity<Void> createAdmin(@RequestBody Admin admin, Authentication auth) {

        logger.debug("Create admin - Name: " + admin.getName());
        adminDao.save(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Delete admin - Id: " + id);
        adminDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<Admin> modifyAdmin(@RequestBody Admin admin, Authentication auth) {

        logger.debug("Modify admin - Id: " + admin.getId());
        adminDao.save(admin);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

}
