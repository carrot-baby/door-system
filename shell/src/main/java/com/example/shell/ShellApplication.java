package com.example.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RibbonClient(name = "door-energy-checkout", configuration = RestConfig.class)
public class ShellApplication {

    public static void main(String[] args) {
         SpringApplication.run(ShellApplication.class, args);
    }

}
