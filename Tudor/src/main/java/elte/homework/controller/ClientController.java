package elte.homework.controller;

import elte.homework.data.Client;
import elte.homework.data.ClientRepository;
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
@RequestMapping("client")
@Transactional
public class ClientController {

    private static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientRepository clientDao;

    @GetMapping("/list")
    public ResponseEntity<List<Client>> listClients(Authentication auth) {

        List<Client> clients = clientDao.findAll();
        logger.debug("List clients - Count: " + clients.size());
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Get client - Id: " + id);
        Optional<Client> client = clientDao.findById(id);
        if(client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public  ResponseEntity<Void> createClient(@RequestBody Client client, Authentication auth) {

        logger.debug("Create client - Name: " + client.getName());
        clientDao.save(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCliet(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Delete client - Id: " + id);
        clientDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<Client> modifyClient(@RequestBody Client client, Authentication auth) {

        logger.debug("Modify client - Id: " + client.getId());
        clientDao.save(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
