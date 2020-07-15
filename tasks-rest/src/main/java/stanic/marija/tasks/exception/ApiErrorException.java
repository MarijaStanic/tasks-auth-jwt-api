package stanic.marija.tasks.exception;

import org.springframework.http.HttpStatus;

public class ApiErrorException extends RuntimeException  {
	
	private final String message;
	private final HttpStatus httpStatus;
	
	public ApiErrorException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
