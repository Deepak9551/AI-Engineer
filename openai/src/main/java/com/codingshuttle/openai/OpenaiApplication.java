package com.codingshuttle.openai;

import com.codingshuttle.openai.controller.ChatController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenaiApplication{



    public static void main(String[] args) {
        SpringApplication.run(OpenaiApplication.class, args);
    }


}
