package stanic.marija.tasks.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

	 @ExceptionHandler(ApiErrorException.class)
	 public void handleCustomException(HttpServletResponse res, ApiErrorException ex) throws IOException {
	    res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	 }

}
