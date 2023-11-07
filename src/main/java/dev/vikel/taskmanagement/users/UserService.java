package dev.vikel.taskmanagement.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    //implements UserDetailsService

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> allUsers(){
        return userRepository.findAll(); 
    }

    public Optional<UserModel> findUser(String userId){
        return userRepository.findByUserId(userId);
    }

    public Optional<UserModel> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    
    public Optional<UserModel> findUserPassword(String password){
        return userRepository.findByPassword(password);
    }
    
    //Save user
     @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(UserModel user) {
        // Encode the user's password before storing it in the database
        //This will run into error unless .and().csrf().disable(); is added to Config file

        //Assign role to user
        List<String> userRole = new ArrayList<String>();
        userRole.add("USER");
        user.setRoles(userRole);
        
        // Save the user to the database
        userRepository.save(user);
    }

    public boolean authenticateIncomingUser(String email, String password) {
        Optional <UserModel> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Use the PasswordEncoder to verify the entered password
            //.get() allows me to go beyond the Optional statement
            return passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }

}
