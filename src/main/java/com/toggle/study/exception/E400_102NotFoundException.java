package com.toggle.study.exception;

import org.springframework.http.HttpStatus;

public class E400_102NotFoundException extends EcodeExcpetion {

    private static final long serialVersionUID = -2560117716406534709L;

    public E400_102NotFoundException() {
    	super();
    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}
