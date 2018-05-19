package elte.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("values")
@Transactional
public class ValuesController {

    @GetMapping("get")
    public ResponseEntity<String> getValue() {

        return  new ResponseEntity<String>("Hello world", HttpStatus.OK);
    }
}
