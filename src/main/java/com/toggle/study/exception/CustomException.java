package com.toggle.study.exception;

import org.springframework.http.HttpStatus;

public interface CustomException {
	HttpStatus getHttpStatus();
	default HttpStatus getResponseHttpStatus() {
		return getHttpStatus();
	}
}
