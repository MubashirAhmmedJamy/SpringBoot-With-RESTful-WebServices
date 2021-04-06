package SpringBootRestController.users;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import SpringBootRestController.posts.Post;


@Entity
public class User{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(max=10,min=4,message="Name size must be between 4 and 10 characters")
	private String name;
	
	@Past(message="Birthdate must be of the past.")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy = "user")
	private List<Post> post;
	
	protected User(){
		
	}
	
	
	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", getId()=" + getId()
//				+ ", getName()=" + getName() + ", getDateOfBirth()=" + getDateOfBirth() + ", getClass()=" + getClass()
//				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
//	}
//	
	

}
