package com.usermanagement.usermanagement.repository;

import com.usermanagement.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.emailId = :emailId OR u.mobileNumber = :mobileNumber")
    public User findUserByEmailOrMobile(String emailId, Long mobileNumber);
    @Query("SELECT u FROM User u WHERE u.emailId = :emailId OR u.mobileNumber = :mobileNumber AND u.password = :password")
    public User login(String emailId, Long mobileNumber, String password);

}
