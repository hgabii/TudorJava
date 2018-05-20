package elte.homework.controller;

import elte.homework.data.Question;
import elte.homework.data.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("question")
@Transactional
public class QuestionController {

    private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionRepository questionDao;

    @GetMapping("/list")
    public ResponseEntity<List<Question>> listQuestions() {

        List<Question> questions = questionDao.findAll();
        logger.debug("List questions - Count:" + questions.size());
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable("id") Integer id) {

        logger.debug("Get question - Id: " + id);
        Question question = questionDao.getOne(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {

        logger.debug("Create question - TextLenght: " + question.getText().length());
        questionDao.save(question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Delete question - Id: " + id);
        questionDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}