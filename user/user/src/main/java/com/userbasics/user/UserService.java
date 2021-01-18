package com.userbasics.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
    UserRepository repository;

	public List<UserModel> findAll() {
		List<UserModel> userList = repository.findAll();
        
        if(userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<UserModel>();
        }
	}

	public UserModel findOne(int id) throws RecordNotFoundException {
		Optional<UserModel> user = repository.findById(id);
        
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No User exist for given id");
        }
	}

	public UserModel save(@Valid UserModel user) {
		repository.save(user);
		return user;
	}
	
	
	
	/* STATIC DATA AND METHODS
	private static List<UserModel> users = new ArrayList<>();
	
	static {
		users.add(new UserModel(1, "Adam", new Date(), "User1Token"));
		users.add(new UserModel(2, "Eve", new Date(), "User2Token"));
		users.add(new UserModel(3, "Jack", new Date(), "User3Token"));
	}
	
	public List<UserModel> findAll() {
		return users;
	}
	
	public UserModel findOne(int id) {
		for (UserModel user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public UserModel deleteById(int id) {
		Iterator<UserModel> iterator = users.iterator();
		while (iterator.hasNext()) {
			UserModel user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	private static int usersCount = 3;
	
	public UserModel save(UserModel user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	*/
	
	
	
	
	

}
