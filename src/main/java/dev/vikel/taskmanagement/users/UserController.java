package dev.vikel.taskmanagement.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  

    @GetMapping("/find")
    public ResponseEntity<UserModel> getUserByEmail(@RequestParam String email){
        Optional<UserModel> user = userService.findUserByEmail(email);
        System.out.println("All users");

        if(user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(user.get());
    }


    //Create new user
    @PostMapping("/newuser")
    public ResponseEntity<UserModel> createNewUser(@RequestBody UserModel newUser) {
        // Get the email from the User object or modify the request JSON accordingly
        String email = newUser.getEmail();

        // Check if a user with the provided email already exists
        Optional<UserModel> existingUser = userService.findUserByEmail(email);

        // If the user already exists, return a Bad Request response
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.valueOf("This user already exists")).build();
        }

        //Saves user password encrypted
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));


        // Save the new user
        userService.registerUser(newUser);

        // Return a successful response with the created user
        return ResponseEntity.ok(newUser);
    }

    //User authenticaztion
    @PostMapping("/login/authenticate")     //Using ReqBody for JSON payloads
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto login) {
        // Authenticate the user based on email and password
        boolean isAuthenticated = userService.authenticateIncomingUser(login.getEmail(), login.getPassword());
        if (isAuthenticated) {
            // User is authenticated, return a success response
            return ResponseEntity.ok("Authentication successful");
        } else {
            // Authentication failed, return a bad request response
            return ResponseEntity.badRequest().body("Authentication failed");
        }
    }

    // todo - May need to tweak for easier access.
    @GetMapping("/getUserId")
    public ResponseEntity<String> getUserId(@RequestParam String email){
        Optional<UserModel> user = userService.findUserByEmail(email);
        System.out.println("\nSearching for userId...\n");
        System.out.println(user.get().getUserId());

        if(user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(user.get().getUserId());

    }

    



   

}
