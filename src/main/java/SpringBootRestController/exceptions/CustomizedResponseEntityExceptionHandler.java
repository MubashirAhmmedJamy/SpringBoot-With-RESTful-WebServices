package SpringBootRestController.exceptions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
		
		System.out.println("Generic Exception Handler working - All Exceptions");
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(UserNotFoundException ex, WebRequest request){
		
		System.out.println("Generic Exception Handler working - Not found Exceptions");
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		Iterator<FieldError> iterator = fieldErrors.iterator();
		String errorDetails = "";
		
		int x = 0;
		
		while (iterator.hasNext()) {
			
			if(x != 0){
				errorDetails += " | ";
			}
			
			errorDetails += iterator.next().getDefaultMessage();
			x++;
		}
		
		
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", 
				errorDetails);
		
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
}
