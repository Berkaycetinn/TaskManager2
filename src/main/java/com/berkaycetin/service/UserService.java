package com.berkaycetin.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.berkaycetin.entities.Comment;
import com.berkaycetin.entities.CommentImage;
import com.berkaycetin.entities.Task;
import com.berkaycetin.entities.TaskImage;
import com.berkaycetin.entities.User;
import com.berkaycetin.repository.UserRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserService  {
	
	@Autowired
    UserRepository repository;




	public User newUser(User user) {
		    User newUser = new User();
            BeanUtils.copyProperties(user, newUser);

        return repository.save(newUser);
		}
		








	
	public List<User> users() {
		List<User> users = repository.findAll();
		
		return users;
		
		
		
		
		
	}

}

