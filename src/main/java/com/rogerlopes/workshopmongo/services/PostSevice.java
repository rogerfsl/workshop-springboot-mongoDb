package com.rogerlopes.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerlopes.workshopmongo.domain.Post;
import com.rogerlopes.workshopmongo.repository.PostRepository;
import com.rogerlopes.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostSevice {

	@Autowired
	private PostRepository repository;
	
	
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
		
	}
	
	public List<Post> findByTitle (String text){
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	
}
