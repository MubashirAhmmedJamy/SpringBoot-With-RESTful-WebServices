package SpringBootRestController.users;

import java.net.URI;
import java.util.List;
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
	
	@Autowired
	private UserDAOservice userService;
	
	
   @RequestMapping(method=RequestMethod.GET, path="/users")
	public List<User> retrieveAll(){
		return userService.findAll();
		
	}
   
   @RequestMapping(method=RequestMethod.GET, path="/users/{id}")
	public User retrieveOne(@PathVariable Integer id){
	   User user = userService.findOne(id);
	   if(user == null){
		   throw new UserNotFoundException("id: "+id);
	   }
	   
	   return user; 
		
	}
   
   
   @RequestMapping(method=RequestMethod.POST, path="/users")
   public ResponseEntity<User> addUser(@RequestBody User user){
	   System.out.println("Testing The Service POST");
	   
	   URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userService.saveOne(user).getId()).toUri();
	   
	   return ResponseEntity.created(location).build();
	    
   }
   
}
