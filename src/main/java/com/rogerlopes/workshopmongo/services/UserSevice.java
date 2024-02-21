package com.rogerlopes.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerlopes.workshopmongo.domain.User;
import com.rogerlopes.workshopmongo.repository.UserRepository;

@Service
public class UserSevice {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
}
