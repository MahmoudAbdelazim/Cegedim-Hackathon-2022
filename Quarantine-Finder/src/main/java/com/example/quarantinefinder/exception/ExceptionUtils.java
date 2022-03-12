package com.example.quarantinefinder.exception;

import org.slf4j.Logger;

public class ExceptionUtils {

	public static void logStack(Logger log,Throwable t, int depth) {
		if(t==null)
			return;
		log.error("Exception Message: {}", t.getMessage());
		StackTraceElement[] array = t.getStackTrace();
		for (int i = 0; i < (array.length < depth ? array.length : depth); i++) {
			log.error("Exception Trace: | {}", array[i]);
		}
		log.error("Exception Cause:-------- ");
		logStack(log, t.getCause(), depth);
	}
}
