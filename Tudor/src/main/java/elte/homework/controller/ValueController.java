package elte.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("value")
@Transactional
public class ValueController {

    @GetMapping("list")
    public ResponseEntity<String> getValues() {

        return new ResponseEntity<>("Hello world!", HttpStatus.OK);
    }
}
