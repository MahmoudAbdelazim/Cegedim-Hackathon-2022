package com.example.quarantinefinder.constant;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import com.example.quarantinefinder.exception.ErrorCode;
import com.example.quarantinefinder.exception.VirtebResponseStatusException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Exceptions {

	public static VirtebResponseStatusException resourceFound(String template, String field, Object rejectedValue, Object... params) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.RESOURCE_ALREADY_EXIST, Utils.format(template, params));
		FieldError fieldError = new FieldError("", field, rejectedValue, false, null, null, null);
		exception.addFieldError(fieldError);
		return exception;
	}

	public static VirtebResponseStatusException resourceFound(String message, String field) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.RESOURCE_ALREADY_EXIST, message);
		exception.addFieldError(new FieldError("", field, null, false, null, null, null));
		return exception;
	}

	// public static VirtebResponseStatusException badRequest(String message) {
	// return new VirtebResponseStatusException(ErrorCode.INVALID_REQUEST, message);
	// }
	//
	// public static VirtebResponseStatusException badRequest(String template, Object... params) {
	// return badRequest(Utils.format(template, params));
	// }

	public static VirtebResponseStatusException notFound(String template, String entityName, Object rejectedValue, Object... params) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.RESOURCE_NOT_FOUND, Utils.format(template, params));
		FieldError fieldError = new FieldError("", entityName, rejectedValue, false, null, null, null);
		exception.addFieldError(fieldError);
		return exception;
	}

	public static VirtebResponseStatusException badRequest(String template, String fieldName, Object rejectedValue, Object... params) {
		return invalidRequest(template, fieldName, rejectedValue, params);
	}

	public static VirtebResponseStatusException invalidRequest(String template, String fieldName, Object rejectedValue, Object... params) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.INVALID_REQUEST, Utils.format(template, params));
		FieldError fieldError = new FieldError("", fieldName, rejectedValue, false, null, null, null);
		exception.addFieldError(fieldError);
		return exception;
	}

	public static VirtebResponseStatusException resourceCanNotBeDeleted(String entityName, Object entityId) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.CAN_NOT_BE_DELETED,
			Utils.format("{} '{}' can not be deleted", entityName, entityId));
		FieldError fieldError = new FieldError(entityName, entityName, entityId, false, null, new Object[] {entityName, entityId}, "{} '{}' can not be deleted");
		exception.addFieldError(fieldError);
		return exception;
	}
	
	public static VirtebResponseStatusException resourceLocked(String entityName,Object entityId, String userName) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.RESOURCE_LOCKED,
			Utils.format("{} '{}' locked by {}", entityName, entityId, userName));
		FieldError fieldError = new FieldError(entityName, entityName, entityId, false, null, new Object[] {entityName, entityId,userName}, "{} '{}' locked by {}");
		exception.addFieldError(fieldError);
		return exception;
	}

	public static ResponseStatusException serverError(String message) {
		return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

	public static ResponseStatusException serverError(String template, Object... params) {
		return serverError(Utils.format(template, params));
	}

	public static VirtebResponseStatusException actionNotAllowed(String template, String rejectedAction, Object rejectedResource, Object... params) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.NOT_ALLOWED_ACTION, Utils.format(template, params));
		FieldError fieldError = new FieldError("", rejectedAction, rejectedResource, false, null, null, null);
		exception.addFieldError(fieldError);
		return exception;
	}
	

	public static VirtebResponseStatusException problemOpeningScreen(String screenName,String screenResource) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.CAN_NOT_OPEN_SCREEN, Utils.format("screen '{}' can not be open", screenResource));
		FieldError fieldError = new FieldError("", "screen", screenName, false, null, null, null);
		exception.addFieldError(fieldError);
		return exception;
	}

}
