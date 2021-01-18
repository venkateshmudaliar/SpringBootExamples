package com.userbasics.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/users")
	public List<UserModel> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<UserModel> retrieveUserById(@PathVariable int id) throws RecordNotFoundException
	{
		UserModel user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id not found-"+ id);
		
		// EntityModel - represents a model with single entity and related links
		EntityModel<UserModel> resource = EntityModel.of(user);
		
		// Adds additional uris to resources pointing to other controller methods
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
		
		//return user;	
	}
	
	// added @valid validation
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserModel user) {
		UserModel savedUser = service.save(user);
		
		//Getting the location of newly added User
		//This adds return code of 201 created 
		//This adds a entry in header - location with the URI of newly added user
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		// Return Created status back
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	/*
	// COMMENTING BECAUSE ITS MAKING PROJECT COMPLICATED
	@GetMapping("/users_filtered_values")
	public MappingJacksonValue retrieveAllUsersWithFilter() {
		List<UserModel> users = service.findAll();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("birthDate", "userFieldToBeFiltered");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserModelFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		return mapping;
	}
	*/

}
