package elte.homework.controller;

import elte.homework.data.Answer;
import elte.homework.data.AnswerRepository;
import elte.homework.model.AnswerRateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("answer")
@Transactional
public class AnswerController {

    private static Logger logger = LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    private AnswerRepository answerDao;

    @GetMapping("/list")
    public ResponseEntity<List<Answer>> listAnswers() {

        List<Answer> answers = answerDao.findAll();
        logger.debug("List answers - Count:" + answers.size());
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable("id") Integer id) {

        logger.debug("Get answer - Id: " + id);
        Answer answer = answerDao.getOne(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {

        logger.debug("Create answer - QuestionId: " + answer.getQuestion().getId());
        answerDao.save(answer);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Delete answer - Id: " + id);
        answerDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/rate")
    public ResponseEntity<Void> rateAnswer(@RequestBody AnswerRateDTO rate, Authentication auth) {

        Answer answer = answerDao.getOne(rate.answerId);
        answer.setRate(rate.rate);
        answerDao.save(answer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
