package com.example.quarantinefinder.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("serial")
public class VirtebResponseStatusException extends ResponseStatusException {

	@Nullable
	private final ErrorCode code;

	private final List<String> errorMessages = new ArrayList<String>();


	@Nullable
	private final List<FieldError> fieldErrors=new ArrayList<>();
	
	public VirtebResponseStatusException(ErrorCode code) {
		this(code.getHttpStatus(), null, null, code);
	}


	/**
	 * Constructor with a response status and a reason to add to the exception
	 * message as explanation.
	 * 
	 * @param status the HTTP status (required)
	 * @param reason the associated reason (optional)
	 */
	public VirtebResponseStatusException(ErrorCode code, @Nullable String reason) {
		this(code.getHttpStatus(), reason, null, code);
	}

	/**
	 * Constructor with a response status and a reason to add to the exception
	 * message as explanation, as well as a nested exception.
	 * 
	 * @param status the HTTP status (required)
	 * @param reason the associated reason (optional)
	 * @param cause a nested exception (optional)
	 */
	public VirtebResponseStatusException(ErrorCode code, @Nullable String reason, @Nullable Throwable cause) {
		super(code.getHttpStatus(), reason, cause);
		this.code = code;
		addErrorMessage(reason);
	}

	public VirtebResponseStatusException(ErrorCode code, ResponseStatusException responseStatusException) {
		this(responseStatusException.getStatus(), responseStatusException.getReason(), responseStatusException.getCause(), code);
	}

	/**
	 * Constructor with a response status and a reason to add to the exception
	 * message as explanation, as well as a nested exception and business error code.
	 * 
	 * @param status the HTTP status (required)
	 * @param reason the associated reason (optional)
	 * @param cause a nested exception (optional)
	 * @param code the business error code
	 */

	private VirtebResponseStatusException(HttpStatus status, @Nullable String reason, @Nullable Throwable cause, @Nullable ErrorCode code) {
		super(status, reason, cause);
		this.code = code;
		addErrorMessage(reason);
	}

	@Nullable
	public ErrorCode getCode() {
		return this.code;
	}

	public void addErrorMessage(String errorMessage) {
		errorMessages.add(errorMessage);
	}

	public void addErrorMessage(String... errorMessage) {
		errorMessages.addAll(Arrays.asList(errorMessage));
	}

	
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	
	public void addFieldError(FieldError field) {
		fieldErrors.add(field);
	}
	
	public void addFieldError(FieldError... field) {
		fieldErrors.addAll(Arrays.asList(field));
	}

	
	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

}
