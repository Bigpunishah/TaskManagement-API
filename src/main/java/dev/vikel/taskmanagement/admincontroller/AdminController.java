package dev.vikel.taskmanagement.admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vikel.taskmanagement.tasks.Task;
import dev.vikel.taskmanagement.tasks.TaskService;
import dev.vikel.taskmanagement.users.UserModel;
import dev.vikel.taskmanagement.users.UserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController{


    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/all-tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.allTasks());
    }


    @GetMapping("/all-users")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.ok(userService.allUsers());
    }
}