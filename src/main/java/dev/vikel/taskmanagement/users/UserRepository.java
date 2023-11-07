package dev.vikel.taskmanagement.users;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{
    
    //!When using repo in MUST include By
    Optional<UserModel> findByUserId(String userId);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByPassword(String password);
}