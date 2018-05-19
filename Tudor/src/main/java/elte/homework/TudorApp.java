package elte.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TudorApp {

    private static Logger logger = LoggerFactory.getLogger(TudorApp.class);

    public static void main(String[] args) {

        SpringApplication.run(TudorApp.class, args);
        logger.info("Tudor application started");
    }
}
