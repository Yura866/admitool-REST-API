package com.pagantis.admintool.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pagantis.admintool.model.User;

public interface UserRepository extends JpaRepository<User,Long> {	
	List <User> findByFirstName(String firstName);	
	List<User> findByUserNameContainingIgnoreCase(String userName);	
}
