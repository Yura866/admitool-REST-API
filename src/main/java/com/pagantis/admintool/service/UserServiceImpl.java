package com.pagantis.admintool.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagantis.admintool.dao.UserRepository;
import com.pagantis.admintool.dto.UserDTO;
import com.pagantis.admintool.exception.ResourceNotFoundException;
import com.pagantis.admintool.model.User;

@Service   
public class UserServiceImpl implements UserService{
	      
	@Autowired 
	UserRepository userRepo;	


	@Override
	public List findAllUsers() {		
	    List<User> users = new ArrayList<User>();
        userRepo.findAll().forEach(users::add);       

        List<UserDTO> userList = new ArrayList<>();
        for (User userModel : users) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userModel, userDTO);
            userList.add(userDTO);
        }
        return userList;
	}

	@Override
	public List findByUserName(String userName) {
	    List<User> users = new ArrayList<User>();
        userRepo.findByUserNameContainingIgnoreCase(userName).forEach(users::add);
        List<UserDTO> userList = new ArrayList<>();
        for (User userModel : users) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userModel, userDTO);
            userList.add(userDTO);
        }
        return userList;        
	}

	@Override
	public UserDTO findById(Long id) {	
		
		if(!userRepo.existsById(id)) 
			throw new ResourceNotFoundException("User with ID [" + id + "] not found");		
		
		Optional<User> user = userRepo.findById(id);		
		UserDTO userDTO= new UserDTO();			
        BeanUtils.copyProperties(user.get(), userDTO);		 
		return userDTO;
	}

	@Override
	public void deleteUser(Long id) {		
		if(!userRepo.existsById(id)) 
			throw new ResourceNotFoundException("User with ID [" + id + "] not exsist");	
		
		userRepo.deleteById(id);		
	}

	@Override
	public UserDTO createUser(final UserDTO userDTO) {		
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		user = userRepo.save(user);
		UserDTO userCreated = new UserDTO();
		BeanUtils.copyProperties(user, userCreated);
		return userCreated;
	}		
}
