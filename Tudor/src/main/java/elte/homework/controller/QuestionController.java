package elte.homework.controller;

import elte.homework.data.*;
import elte.homework.model.QuestionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("question")
@Transactional
public class QuestionController {

    private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionRepository questionDao;

    @Autowired
    private ClientRepository clientDao;

    @Autowired
    private UserRepository userDao;

     @Autowired
     private TopicRepository topicDao;

     @Autowired
     private TudorRepository tudorDao;

    @GetMapping("/list")
    public ResponseEntity<List<Question>> listQuestions(Authentication auth) {

        User user = userDao.getOne(auth.getName());

        List<Question> questions = new ArrayList<>();

        if(user.getType() == User.UserType.ADMIN) {
            questions = questionDao.findAll();
        }
        else if(user.getType() == User.UserType.CLIENT) {
            Client client = clientDao.getOne(user.getUserId());
            questions = questionDao.findQuestionsByClient(client);
        }
        else if(user.getType() == User.UserType.TUDOR) {
            Tudor tudor = tudorDao.getOne(user.getUserId());
            questions = questionDao.findQuestionsByTopic(tudor.getTopic());
        }

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
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionDTO question, Authentication auth) {

        User user = userDao.getOne(auth.getName());
        if(user.getType() != User.UserType.CLIENT) {
            throw new AccessDeniedException("Only client can create questions");
        }
        Client client = clientDao.getOne(user.getUserId());
        Topic topic = topicDao.getOne(question.topicId);
        logger.debug("Create question - TextLenght: " + question.text.length());
        Question questionEntity = new Question();
        questionEntity.setClient(client);
        questionEntity.setText(question.text);
        questionEntity.setCreationDate(new Date());
        questionEntity.setTopic(topic);
        questionDao.save(questionEntity);
        return new ResponseEntity<>(questionEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Integer id, Authentication auth) {

        logger.debug("Delete question - Id: " + id);
        questionDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}