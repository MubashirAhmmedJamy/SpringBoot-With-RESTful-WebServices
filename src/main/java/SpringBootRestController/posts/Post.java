package SpringBootRestController.posts;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import SpringBootRestController.users.User;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
