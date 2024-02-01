package org.logistics.data.repository;

import org.logistics.data.model.Admin;
import org.logistics.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,String> {
    Admin findUserByUserName(String username);
}
