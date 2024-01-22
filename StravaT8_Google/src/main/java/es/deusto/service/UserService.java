package es.deusto.service;

import es.deusto.dao.UserRepository;
import es.deusto.model.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Use @Service annotation for BUSINESS LOGIC and access to the @REPOSITORY
@Service
public class UserService {

    private UserRepository userRepository;
    public enum UserServiceResult {
		SUCCESS,
		FAIL;
	}
        
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    //strava methods
    public boolean checkUserEmail(String email) {
    	Optional<User> result = userRepository.findByEmail(email);
    	if (result != null) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean checkUserPassword(String email, String password) {
    	Optional<User> result = userRepository.findByEmail(email);
    	if (result != null) {
    		return password.equals(result.get().getPassword());
    	} else {
    		return false;
    	}
    }
    
    
    /**  Returning User information */
    public User getUserById(Long id) {
    	Optional<User> result = userRepository.findById(id);
    	return result.orElse(null);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
     
    /** Creating a New User */
	public UserServiceResult createUser(User user) {
		Optional<User> result = userRepository.findByEmail(user.getEmail());
		
		if (result.isEmpty()) {
			User savedUser = userRepository.save(user);
			
			if (savedUser != null) {
				return UserServiceResult.SUCCESS;
			}
		}
		
		return UserServiceResult.FAIL;
	}    
  
    /** Delete one User*/
    public UserServiceResult deleteUser(Long id) {
    	Optional<User> result = userRepository.findById(id);
		
		if (!result.isEmpty()) {
			userRepository.delete(result.get());

			if (userRepository.findById(id).isEmpty()) {
				return UserServiceResult.SUCCESS;
			}
		}
		
		return UserServiceResult.FAIL;
    }
    
    /** Delete all Users in the database  */
    public UserServiceResult deleteAllUsers() {
    	UserServiceResult result = UserServiceResult.SUCCESS;
		
		for (User u : userRepository.findAll()) {
			userRepository.deleteById(u.getId());

			if (!userRepository.findById(u.getId()).isEmpty()) {
				result = UserServiceResult.FAIL;
			}
		}

		return result;
    }
	
}
























