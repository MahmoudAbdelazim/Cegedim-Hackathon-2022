package com.example.quarantinefinder.constant;

import org.springframework.validation.FieldError;

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


	public static VirtebResponseStatusException notFound(String template, String entityName, Object rejectedValue, Object... params) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.RESOURCE_NOT_FOUND, Utils.format(template, params));
		FieldError fieldError = new FieldError("", entityName, rejectedValue, false, null, null, null);
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
	public static VirtebResponseStatusException badRequest(String template, String fieldName, Object rejectedValue, Object... params) {
		return invalidRequest(template, fieldName, rejectedValue, params);
	}
	public static VirtebResponseStatusException invalidRequest(String template, String fieldName, Object rejectedValue, Object... params) {
		VirtebResponseStatusException exception = new VirtebResponseStatusException(ErrorCode.INVALID_REQUEST, Utils.format(template, params));
		FieldError fieldError = new FieldError("", fieldName, rejectedValue, false, null, null, null);
		exception.addFieldError(fieldError);
		return exception;
	}
}
