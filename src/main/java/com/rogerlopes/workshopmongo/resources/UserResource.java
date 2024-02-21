package com.rogerlopes.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rogerlopes.workshopmongo.domain.User;
import com.rogerlopes.workshopmongo.dto.UserDTO;
import com.rogerlopes.workshopmongo.services.UserSevice;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserSevice service;


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity< List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
}
