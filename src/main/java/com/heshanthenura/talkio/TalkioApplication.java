package com.heshanthenura.talkio;

import com.heshanthenura.talkio.Database.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class TalkioApplication implements CommandLineRunner {

    Logger infoLogger = Logger.getLogger("Information");

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TalkioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        infoLogger.info("userRepository.findAll().forEach(entity -> System.out.println(entity.getClass().getSimpleName()));");
    }

}
