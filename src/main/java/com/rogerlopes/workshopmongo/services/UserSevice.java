package com.rogerlopes.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.stereotype.Service;

import com.rogerlopes.workshopmongo.domain.User;
import com.rogerlopes.workshopmongo.dto.UserDTO;
import com.rogerlopes.workshopmongo.repository.UserRepository;
import com.rogerlopes.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserSevice {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
		
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User update (User obj) {
		User newObj = findById(obj.getId());
		UpdateData(newObj, obj);
		return repository.save(newObj);
	}

	
	private void UpdateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDto (UserDTO objDto) {
		return new User (objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
