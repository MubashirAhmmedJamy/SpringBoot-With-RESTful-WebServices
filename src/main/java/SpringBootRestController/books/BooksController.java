package SpringBootRestController.books;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
	
	@GetMapping("/books")
	public List<Book> allBooks(){
		
		return Arrays.asList(new Book(123,"Mastering Spring Boot", "Mubashir Ahmmed Jamy"));
		
	}
}
