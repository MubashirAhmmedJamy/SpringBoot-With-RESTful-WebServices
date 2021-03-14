package SpringBootRestController.users;

//UserDAO service class is only for managing the List of users.
//To add a new user
//To get a user
//To get all users
//To delete all users
//To delete specific user


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOservice {
	private static List<User> users = new ArrayList<>();
	
	private static Integer userID = 3;
	
	
	static{
		users.add(new User(1,"Mubashir",new Date()));
		users.add(new User(2,"Ahmmed",new Date()));
		users.add(new User(3,"Jamy",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	
	public User findOne(Integer ID){
		for(User user : users){
			
			if(user.getId()==ID){
			return user;
			}
		}
		
		return null;
	}
	
	
	public User saveOne(User user){
		if(user.getId() == null){
			user.setId(++userID);
		}
		
		users.add(user);
		
		return user;
	}
	
	
	public User deleteOne(Integer id){
		Iterator<User> iterator = users.iterator();
		
		User user;
		
		while(iterator.hasNext()){
			user = iterator.next();
			
			if(user.getId() == id){
				iterator.remove();
				return user;
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
}
