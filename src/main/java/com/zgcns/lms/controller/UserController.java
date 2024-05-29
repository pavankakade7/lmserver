package com.zgcns.lms.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zgcns.lms.model.Employee;
import com.zgcns.lms.model.User;
import com.zgcns.lms.repositories.UserRepository;
import com.zgcns.lms.services.UserService;

import lombok.AllArgsConstructor;



//@CrossOrigin("*")
@RestController
// @AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	public final UserService userService;
	
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	
	
	@PostMapping("/adduser")
	public String addUser(@RequestBody User user) {
		return userService.addUser(user);
	
	}	
	
	@PostMapping("/authuser")
	public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody User user) {
		return userService.authenticateUser(user);
	}
	
	@PutMapping("/updateuser/{userId}")
	public User UpdatedUser(@PathVariable("userId") long userId, @RequestBody User user) {
		return userService.updateUser(userId, user);
	}
	
//	 @GetMapping("/by-email")
//	    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
//	        try {
//	            User user = userService.getUserByEmail(email);
//	            return ResponseEntity.ok(user); // Return user data with HTTP 200
//	        } catch (UserNotFoundException e) {
//	            return ResponseEntity.status(404).body(null); // Return HTTP 404 if user not found
//	        }
//	    }
	 
	@GetMapping
	 public User getUserByEmail(@RequestParam String email) {
		 return userService.getUserByEmail(email);
	 }
	
	@GetMapping("/{userId}")
	public User getUserByUserId(@PathVariable long userId) {
		return userService.getUserByUserId(userId);
	}
	
	@GetMapping("/firstname")
	public User getUserByFirstName (@RequestParam String firstName) {
		return  userService.getUserByFirstName(firstName);

	}
	
	  @GetMapping("/allusers")
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }
	    
}