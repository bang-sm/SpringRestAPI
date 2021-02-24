package com.toggle.study.exception;

import org.springframework.http.HttpStatus;

public class E400_101DataCreateFailedException extends EcodeExcpetion {

    private static final long serialVersionUID = -2560117716406534709L;

    public E400_101DataCreateFailedException() {
        super();
    }

    public E400_101DataCreateFailedException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}
