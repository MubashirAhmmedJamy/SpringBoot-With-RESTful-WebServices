package SpringBootRestController;

public class HelloWorldBean {
	String Message;

	public HelloWorldBean(String message) {
		super();
		Message = message;
	}

	public String getMessage() {
		return Message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [Message=" + Message + "]";
	}

//	public void setMessage(String message) {
//		Message = message;
//	}
	
	
}
