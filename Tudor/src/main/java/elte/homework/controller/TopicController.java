package elte.homework.controller;

import elte.homework.data.Topic;
import elte.homework.data.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("topic")
@Transactional
public class TopicController {

    private static Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicRepository topicDao;

    @GetMapping("/list")
    public ResponseEntity<List<Topic>> listTopics() {

        List<Topic> topics = topicDao.findAll();
        logger.debug("List topics - Count: " + topics.size());
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") Integer id) {

        logger.debug("Get topic - TopicId: " + id);
        Topic topic = topicDao.getOne(id);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {

        logger.debug("Create topic - Subject: " + topic.getSubject());
        topicDao.save(topic);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }
}