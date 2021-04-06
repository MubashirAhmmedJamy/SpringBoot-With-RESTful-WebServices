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
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import SpringBootRestController.exceptions.UserNotFoundException;

@RestController
public class UserControllerJPA {

// To manage the backend data we need to have a JpaRepository interface and that interface methods will be implemented by spring boot
//	itself

	@Autowired
	private UserJPARepository userRepository;
	
	
// 	This api will fetch all users from in-memory database
	
	@RequestMapping(method = RequestMethod.GET, path = "/jpa/users")
	public List<User> retrieveAll() {
		System.out.println("\n\nJPA: Getting all users from database\n\n");
		return userRepository.findAll();
	}
	
	
//	This API will fetch user by id from the H2 database

	@RequestMapping(method = RequestMethod.GET, path = "/jpa/users/{id}")
	public EntityModel<Optional<User>> retrieveOne(@PathVariable Integer id) {
		
		System.out.println("Getting User with id: "+id);
		
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("Get: There is no user with id " + id);
		}
		
		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserControllerJPA.class)
				.retrieveAll()).withRel("all-users");
		
		System.out.println("Link: " + link);
		
		return EntityModel.of(user).add(link);
	}


	@RequestMapping(method = RequestMethod.POST, path = "/jpa/users")

	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		System.out.println("Working with The Service POST");

		URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userRepository.save(user).getId()).toUri();		
		return ResponseEntity.created(loc).body(user);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/jpa/users/{id}")
	public Optional<User> deleteOne(@PathVariable Integer id) {
		System.out.println("Got a Delete Request for JPA\n");
		
		Optional<User> user = userRepository.findById(id);

		userRepository.deleteById(id);

		return user;
	}

}
