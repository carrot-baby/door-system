package com.example.shell.controller;

import com.example.shell.service.ShellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShellController {

    @Autowired
    ShellService shellService;

//    @GetMapping("/updateShell")
//    public boolean updateShell() {
//        boolean flag = shellService.updateShell();
//        return flag;
//    }
    @GetMapping("/start")
    public String start() {
       String flag = shellService.start();
        return flag;
    }
    @GetMapping("/stop")
    public String stop() {
        String flag = shellService.stop();
        return flag;
    }
    @GetMapping("/test")
    public String test() {
        String flag = shellService.test();
        return flag;
    }
}
