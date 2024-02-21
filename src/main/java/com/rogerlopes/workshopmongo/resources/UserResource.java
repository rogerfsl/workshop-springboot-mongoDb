package com.rogerlopes.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rogerlopes.workshopmongo.domain.User;
import com.rogerlopes.workshopmongo.dto.UserDTO;
import com.rogerlopes.workshopmongo.services.UserSevice;

import jakarta.servlet.Servlet;

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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert (@RequestBody UserDTO objDto){
		
		User obj = service.fromDto(objDto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// vai pegar o endereço do novo objeto que foi inserido.
		return ResponseEntity.created(uri).build();// vai retornar uma resposta vazia com o código 201 e com o cabeçalho do novo recurso criado.
	}
}
