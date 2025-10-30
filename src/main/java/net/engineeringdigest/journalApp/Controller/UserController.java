package net.engineeringdigest.journalApp.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.User;
import net.engineeringdigest.journalApp.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers()
	{
		return service.getAll();
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user)
	{
		service.addEntry(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}

	@PutMapping("/updateUser/{username}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username)
	{
		
		User userInDB =service.findByUsername(username);
				
		if (userInDB.getUsername()!=null)
		{
			userInDB.setUsername(user.getUsername());
			userInDB.setPassword(user.getPassword());
			service.addEntry(userInDB);
		}
		
		return new ResponseEntity<User>(userInDB ,HttpStatus.ACCEPTED);
		
	}
	
}
