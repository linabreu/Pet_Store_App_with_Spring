package petStore.controller.error;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalControllerErrorHandler {
	//log entire stack trace or message only, usual is message only
	private enum LogStatus {
		STACK_TRACE, MESSAGE_ONLY
	}
	
	//use @data for getters setters and no args constructor
	@Data
	private class ExceptionMessage {
		private String message;
		private String statusReason;
		private int statusCode;
		private String timeStamp;
		private String uri;
	}
	
	//takes no such element exception and a web request as the arguments
	//do not have the uri but spring will inject the variable of the right type - web request
	
	@ExceptionHandler(NoSuchElementException.class) //no such element is exception we want
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionMessage handleNoSuchElementException(NoSuchElementException e,  WebRequest webRequest)
	{
		//pass in exception, status that we want which is 404 not found, logstatus which is entire stack trace or not
		return buildExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
		
	}

	private ExceptionMessage buildExceptionMessage(Exception e, HttpStatus status, WebRequest webRequest,
			LogStatus logStatus) 
	{
		String message = e.toString();
		String statusReason = status.getReasonPhrase(); //return reason phrase of the status code
		int statusCode = status.value();
		String uri = null;
		String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

		//if web request is a servelet web request then get the url of the web request
		if (webRequest instanceof ServletWebRequest servWebReq)
		{
			uri = servWebReq.getRequest().getRequestURI();
		}
		//if message only just log the to string
		if (logStatus == LogStatus.MESSAGE_ONLY)
		{
			log.error("Exception: {}", e.toString());
		}
		else
		{
			log.error("Exception: ", e);
		}
		
		//create new exception message to return from this method
		ExceptionMessage exceptionMessage = new ExceptionMessage();
		
		//set the exception message attributes as the args of this method
		exceptionMessage.setMessage(message);
		exceptionMessage.setStatusCode(statusCode);
		exceptionMessage.setTimeStamp(timeStamp);
		exceptionMessage.setStatusReason(statusReason);
		exceptionMessage.setUri(uri);

		return exceptionMessage;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionMessage handleException(Exception e, WebRequest webRequest)
	{
		return buildExceptionMessage(e, HttpStatus.INTERNAL_SERVER_ERROR, webRequest, LogStatus.STACK_TRACE);
	}
	
	
	//unsupported operation exception
	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus (code = HttpStatus.METHOD_NOT_ALLOWED)
	public ExceptionMessage handleUnsupportedOperationException (UnsupportedOperationException e, WebRequest webRequest )
	{
		return buildExceptionMessage(e, HttpStatus.METHOD_NOT_ALLOWED, webRequest, LogStatus.MESSAGE_ONLY);

		
	}
	
	
	
	
	

}
