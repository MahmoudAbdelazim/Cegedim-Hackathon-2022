/*
 * package com.example.quarantinefinder.exception;
 * 
 * import java.io.Serializable; import java.time.Instant; import
 * java.time.LocalDateTime; import java.time.ZoneId; import java.util.HashMap;
 * import java.util.List; import java.util.Map;
 * 
 * import org.springframework.http.HttpStatus; import
 * org.springframework.lang.Nullable; import
 * org.springframework.validation.FieldError;
 * 
 * @SuppressWarnings("serial") public class VirtebException implements
 * Serializable {
 * 
 * @Nullable private final Long unixTime;
 * 
 * @Nullable private final LocalDateTime timeStamp;
 * 
 * @Nullable private final int status;
 * 
 * @Nullable private final List<String> messages;
 * 
 * @Nullable private final List<FieldError> fieldErrors;
 * 
 * @Nullable private final String path;
 * 
 * @Nullable private final ErrorCode code;
 * 
 * private final String ticketId;
 * 
 * public Long getUnixTime() { return unixTime; }
 * 
 * public LocalDateTime getTimeStamp() { return timeStamp; }
 * 
 * public int getStatus() { return status; }
 * 
 * public HttpStatus getError() { return HttpStatus.valueOf(status); }
 * 
 * public List<String> getMessages() { return messages; }
 * 
 * public String getPath() { return path; }
 * 
 * public ErrorCode getCode() { return code; }
 * 
 * public String getTicketId() { return ticketId; }
 * 
 * public List<FieldError> getFieldErrors() { return fieldErrors; }
 *//**
	 * @param status
	 * @param message
	 * @param path
	 * @param code
	 * @param ticketId
	 *//*
		 * public VirtebException(HttpStatus status, @Nullable List<String> messages,
		 * List<FieldError> fieldErrors, @Nullable String path,
		 * 
		 * @Nullable ErrorCode code, String ticketId) { super(); Instant instant =
		 * Instant.now(); this.unixTime = instant.toEpochMilli(); this.timeStamp =
		 * LocalDateTime.ofInstant(instant, ZoneId.of("Z"));//
		 * 2020-04-23T19:01:01.014+0000 this.status = status.value(); this.messages =
		 * messages; this.path = path; this.code = code; this.ticketId = ticketId;
		 * this.fieldErrors = fieldErrors; }
		 * 
		 * @Override public String toString() { return
		 * "LagortaStatusException [unixTime=" + unixTime + ", timeStamp=" + timeStamp +
		 * ", status=" + status + ", message=" + messages + ", path=" + path + ", code="
		 * + code + ", ticketId=" + ticketId + "]"; }
		 * 
		 * public Map<String, Object> toMap(Map<String, Object> map) { if (map != null)
		 * { map.put("unixTime", getUnixTime()); map.put("timeStamp", getTimeStamp());
		 * map.put("status", getStatus()); map.put("message", getMessages());
		 * map.put("path", getPath()); map.put("code", getCode()); map.put("ticketId",
		 * getTicketId()); } return map;
		 * 
		 * }
		 * 
		 * public Map<String, Object> toMap() { Map<String, Object> map = new
		 * HashMap<String, Object>(); map.put("unixTime", getUnixTime());
		 * map.put("timeStamp", getTimeStamp()); map.put("status", getStatus());
		 * map.put("message", getMessages()); map.put("path", getPath());
		 * map.put("code", getCode()); map.put("ticketId", getTicketId()); return map;
		 * 
		 * }
		 * 
		 * }
		 */