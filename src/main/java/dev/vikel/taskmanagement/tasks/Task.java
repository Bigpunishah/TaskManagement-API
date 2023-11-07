package dev.vikel.taskmanagement.tasks;

import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
     @Id
     private String taskId;

    private String taskName;
    
    private String taskDescription;

    private String priority;

    //@DBRef No need for the @DBRef need to figure out what I acutally need it for.
    private String userId;


}
