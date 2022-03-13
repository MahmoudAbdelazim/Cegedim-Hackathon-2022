package com.example.quarantinefinder.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
	RESOURCE_ALREADY_EXIST(52, "VEC_0052", "Resource Already Exist", HttpStatus.BAD_REQUEST.value()),
	RESOURCE_NOT_FOUND(51, "VEC_0051", "Resource Not Found", HttpStatus.NOT_FOUND.value()),
	CAN_NOT_BE_DELETED(55, "VEC_0055", "Resource can not be deleted", HttpStatus.BAD_REQUEST.value()),
	INVALID_REQUEST(1, "VEC_0001", "Invalid request", HttpStatus.BAD_REQUEST.value());

	private final int value;

	private final String code;

	private final String description;

	private final HttpStatus httpStatus;

	ErrorCode(int value, String code, String description, int httpCode) {
		this.value = value;
		this.code = code;
		this.description = description;
		this.httpStatus = HttpStatus.valueOf(httpCode);
	}

	/**
	 * Return the integer value of this error code.
	 */
	public int value() {
		return this.value;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@JsonValue
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("description", description);
		return map;
	}

	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * 
	 * @param value the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException if this enum has no constant for the
	 *                                  specified numeric value
	 */
	public static ErrorCode valueOf(int value) {
		ErrorCode lagortaError = resolve(value);
		if (lagortaError == null) {
			throw new IllegalArgumentException("No matching constant for [" + value + "]");
		}
		return lagortaError;
	}

	/**
	 * Resolve the given value to an {@code LagortaErrorCode}, if possible.
	 * 
	 * @param value the value of LagortaErrorCode
	 * @return the corresponding {@code LagortaErrorCode}, or {@code null} if not
	 *         found
	 */
	@Nullable
	public static ErrorCode resolve(int value) {
		for (ErrorCode lagortaError : values()) {
			if (lagortaError.value == value) {
				return lagortaError;
			}
		}
		return null;
	}

}
