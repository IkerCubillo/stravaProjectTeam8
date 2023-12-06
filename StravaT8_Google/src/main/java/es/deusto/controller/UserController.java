package es.deusto.controller;

import es.deusto.model.User;
import es.deusto.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*Use @Controllers ONLY for Routing: providing end-points (get requests & provide JSON responses)
* They are STATELESS & SINGLETON
*/
@RestController
public class UserController {
	private static final Logger log= LoggerFactory.getLogger(UserController.class);


    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    //Strava methods
    @GetMapping("/user/details/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("/user/email/{email}")
    public boolean userValidation(@PathVariable String email) {
    	log.info(email);
        return userService.checkUserEmail(email);
    }
    
    @GetMapping("/user/verify/{email}/{password}")
    public boolean passwordValidation(@PathVariable String email, @PathVariable String password) {
        return userService.checkUserPassword(email, password);
    }
    
    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
       
    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
    	log.info("Creating a new User ...");
       	switch (userService.createUser(user)) {
       		case FAIL:
    	    	return ResponseEntity.unprocessableEntity().body("User Creation Failed; User exists and/or Operation aborted");

      	    default:
      	    	return ResponseEntity.ok("Successfully created User");	
    	}
    }
   
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
    	log.info("Deleting one User: " + id);
    	
    	switch (userService.deleteUser(id)) {
    		case FAIL:
    			return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
  	        
    		default:
    			return ResponseEntity.ok("Successfully deleted the specified user");
    	}
    }
    
    @DeleteMapping("/user/delete/all")
    public ResponseEntity<Object> deleteUsers() {
    	log.info("Deleting ALL users");
    	
    	switch (userService.deleteAllUsers()) {
			case FAIL:
				return ResponseEntity.unprocessableEntity().body("Deletion of all users fails");
			default:
				return ResponseEntity.ok("All users has been deleted.");
    	}   
   
    }
}
