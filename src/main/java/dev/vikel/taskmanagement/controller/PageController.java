package dev.vikel.taskmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//This controller helps direct lage location
@Controller
public class PageController {
        @GetMapping("/")
    public String home() {
        return "index"; // returns index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // returns login.html
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // returns about.html
    }

    @GetMapping("/task")
    public String task() {
        return "task"; // returns task.html
    }  
    
    @GetMapping("/createaccount")
    public String createAccount() {
        return "createaccount"; // returns createAccount.html
    }
}
