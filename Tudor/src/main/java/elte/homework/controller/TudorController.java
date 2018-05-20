package elte.homework.controller;

import elte.homework.data.Tudor;
import elte.homework.data.TudorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tudor")
@Transactional
public class TudorController {

    private static Logger logger = LoggerFactory.getLogger(TudorController.class);

    @Autowired
    private TudorRepository tudorDao;

    @GetMapping("/list")
    public ResponseEntity<List<Tudor>> listTudors(Authentication auth) {

        List<Tudor> tudors = tudorDao.findAll();
        logger.debug("List tudors - Count: " + tudors.size());
        return  new ResponseEntity<>(tudors, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Tudor> getTudor(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Get tudor - Id: " + id);
        Optional<Tudor> tudor = tudorDao.findById(id);
        if(tudor.isPresent()) {
            return new ResponseEntity<>(tudor.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public  ResponseEntity<Void> createTudor(@RequestBody Tudor tudor, Authentication auth) {

        logger.debug("Create tudor - Name: " + tudor.getName());
        tudorDao.save(tudor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTudor(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Delete tudor - Id: " + id);
        tudorDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<Tudor> modifyClient(@RequestBody Tudor tudor, Authentication auth) {

        logger.debug("Modify tudor - Id: " + tudor.getId());
        tudorDao.save(tudor);
        return new ResponseEntity<>(tudor, HttpStatus.OK);
    }
}
