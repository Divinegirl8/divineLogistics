package org.logistics.data.repository;

import org.logistics.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findUserByUserName(String username);
    User findByUserName(String username);
    User findUserByPassword(String password);
}
