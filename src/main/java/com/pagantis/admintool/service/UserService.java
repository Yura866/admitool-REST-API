package com.pagantis.admintool.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.pagantis.admintool.dto.UserDTO;
import com.pagantis.admintool.model.User;
@Service
public interface UserService {		
	List findAllUsers();	
	List findByUserName(String userName);
	UserDTO findById(Long id);	
	UserDTO createUser(UserDTO user);
	void deleteUser(Long id);
}
