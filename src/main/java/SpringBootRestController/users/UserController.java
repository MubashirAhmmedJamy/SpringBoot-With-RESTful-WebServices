package SpringBootRestController.users;

//User controller is the class that will take the incoming requests
//This will map the request uri and call specific method from DAO object
//The data handling operations will be taken care of by the DAO object itself
//Controller's responsibility is 2 things
//1. Call the right method for the right request URI
//2. Return the correct response
//3. Handle the exceptions

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	
	//To manage the backend data the controller must have an object of the DAO class
	@Autowired
	private UserDAOservice userService;
	
	
	//Whatever this method is returning, this is actually the response to the client
   @RequestMapping(method=RequestMethod.GET, path="/users")
	public List<User> retrieveAll(){
		return userService.findAll();
	}
   
	//Whatever this method is returning, this is actually the response to the client
   //We can directly return an object which will be auto converted to JSON by Springboot
   
   @RequestMapping(method=RequestMethod.GET, path="/users/{id}")
	public User retrieveOne(@PathVariable Integer id){
	   
	   User user = userService.findOne(id);
	   
	   if(user == null){
		   throw new UserNotFoundException("Get: There is no user with id "+id);
	   }
	   
	   return user; 
		
	}
   
	//Whatever this method is returning, this is actually the response to the client   
   
   
   @RequestMapping(method=RequestMethod.POST, path="/users")
   
   public ResponseEntity<User> addUser(@Valid @RequestBody User user){
	   System.out.println("Testing The Service POST");
	   
	   URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userService.saveOne(user).getId()).toUri();
	   
	   return ResponseEntity.created(location).build();
	    
   }
   
   
   @RequestMapping(method=RequestMethod.DELETE, path="/users/{id}")
	public User deleteOne(@PathVariable Integer id){
	   System.out.println("Got a Delete Request");
	   User user = userService.deleteOne(id);
	   
	   if(user == null){
		   throw new UserNotFoundException("Delete: There is no user with id: "+id);
	   }
	   
	   return user;
	}
   
}
