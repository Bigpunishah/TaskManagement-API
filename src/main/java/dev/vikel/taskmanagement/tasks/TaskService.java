package dev.vikel.taskmanagement.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    // All tasks from all users
    public List<Task> allTasks() {
        return taskRepository.findAll();
    }

    // Tasks from specific users
    public List<Task> userTasks(String userId) {
        return taskRepository.findByUserId(userId);
    }

    // Find task by id
    public Optional<Task> specificTask(String taskId) {
        return taskRepository.findByTaskId(taskId);
    }

    // Create a new task
    public Task createTask(Task task) {
        // You can add any additional logic here, if needed
        return taskRepository.save(task);
    }

    // Update an existing task
    public Task updateTask(Task updatedTask) {
        // can add validation or additional logic here before updating
        return taskRepository.save(updatedTask);
    }

    //Delete existing task
    public void deleteTask(Task taskToDelete){
        taskRepository.delete(taskToDelete);
    }

}
