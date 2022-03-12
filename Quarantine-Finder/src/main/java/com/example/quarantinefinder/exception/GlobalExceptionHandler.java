package com.example.quarantinefinder.exception;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// import brave.Tracer;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// @Autowired
	// private Tracer tracer;

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(VirtebResponseStatusException.class)
	public ResponseEntity<Object> handleVirtebError(VirtebResponseStatusException e, HttpServletRequest request) {
		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.warn("Error VirtebResponseStatusException {}", e.getMessage());
		ExceptionUtils.logStack(log, e, 6);

		ResponseEntity<Object> resp = new ResponseEntity<>(
			new VirtebException(e.getStatus(), e.getErrorMessages(), e.getFieldErrors(), request.getServletPath().toString(), e.getCode(), ticketId),
			e.getStatus());
		return resp;
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleVirtebError(ResponseStatusException e, HttpServletRequest request) {
		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getPathTranslated());
		ExceptionUtils.logStack(log, e, 8);
		HttpStatus status = e.getStatus();
		if (status.equals(HttpStatus.UNAUTHORIZED)) {
			ErrorCode code = ErrorCode.LOGIN_ERROR;
			return new ResponseEntity<>(
				new VirtebException(status, Arrays.asList(code.getDescription()), null, request.getServletPath().toString(), code, ticketId), status);
		}
		ErrorCode code = ErrorCode.UNDEFINED;

		ResponseEntity<Object> resp = new ResponseEntity<>(new VirtebException(code.getHttpStatus(), Arrays.asList(code.getDescription()), null,
			request.getServletPath().toString(), code, ticketId), code.getHttpStatus());
		return resp;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleVirtebError(Exception e, HttpServletRequest request) {
		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getPathTranslated());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.UNDEFINED;

		ResponseEntity<Object> resp = new ResponseEntity<>(new VirtebException(code.getHttpStatus(), Arrays.asList(code.getDescription()), null,
			request.getServletPath().toString(), code, ticketId), code.getHttpStatus());
		return resp;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException e, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.INVALID_INPUT;

		BindingResult result = e.getBindingResult();

		List<FieldError> errors = result.getFieldErrors().stream().map(f -> new FieldError(f.getObjectName(), f.getField(), f.getDefaultMessage()))
			.collect(Collectors.toList());

		ResponseEntity<Object> resp = new ResponseEntity<>(
			new VirtebException(status, Arrays.asList(code.getDescription()), errors, request.getContextPath(), code, ticketId), status);
		return resp;
	}

	// 404
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers, HttpStatus status,
		WebRequest request) {
		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.NOT_SUPPORTED;

		ResponseEntity<Object> resp = new ResponseEntity<>(new VirtebException(status, null, null, e.getRequestURL(), code, ticketId), status);
		return resp;
	}

	// 405

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException e, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {
		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.NOT_SUPPORTED;

		ResponseEntity<Object> resp = new ResponseEntity<>(
			new VirtebException(status, Arrays.asList(e.getMethod() + " not supported"), null, null, code, ticketId), status);
		return resp;

	}

	// 415

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException e, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {

		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.NOT_SUPPORTED;

		ResponseEntity<Object> resp = new ResponseEntity<>(new VirtebException(status, Arrays.asList(e.getMessage()), null, null, code, ticketId),
			status);
		return resp;
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException e, final HttpHeaders headers, final HttpStatus status,
		final WebRequest request) {

		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.NOT_SUPPORTED;

		ResponseEntity<Object> resp = new ResponseEntity<>(
			new VirtebException(status, Arrays.asList(e.getPropertyName() + " should be " + e.getRequiredType()), null, null, code, ticketId),
			status);
		return resp;
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException e, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {

		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.NOT_SUPPORTED;

		ResponseEntity<Object> resp = new ResponseEntity<>(
			new VirtebException(status, Arrays.asList("invalid request part"), null, null, code, ticketId), status);
		return resp;
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException e, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {

		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.INVALID_INPUT;

		ResponseEntity<Object> resp = new ResponseEntity<>(
			new VirtebException(status, Arrays.asList(e.getParameterName() + " is missing - invalid request "), null, null, code, ticketId), status);
		return resp;
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(final ServletRequestBindingException e, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {

		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.INVALID_INPUT;

		ResponseEntity<Object> resp = new ResponseEntity<>(new VirtebException(status, Arrays.asList(e.getMessage()), null, null, code, ticketId),
			status);
		return resp;

	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException e, final HttpHeaders headers,
		final HttpStatus status, final WebRequest request) {

		String ticketId = getTicketId();// tracer.currentSpan().context().traceIdString();
		log.error("Error ticketId: {}| Exception:{} | message: {}", ticketId, e.getClass().getName(), e.getMessage());
		log.error("Error request path: [{}]", request.getContextPath());
		ExceptionUtils.logStack(log, e, 6);
		ErrorCode code = ErrorCode.INVALID_INPUT;

		ResponseEntity<Object> resp = new ResponseEntity<>(new VirtebException(status, Arrays.asList(e.getMessage()), null, null, code, ticketId),
			status);
		return resp;

	}

	private String getTicketId() {
		return UUID.randomUUID().toString();
	}

}
