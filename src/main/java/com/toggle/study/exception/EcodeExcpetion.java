package com.toggle.study.exception;

public abstract class EcodeExcpetion extends RuntimeException implements CustomException {

	private static final long serialVersionUID = 4586365134366311073L;

	public EcodeExcpetion() {
		super();
	}

	public EcodeExcpetion(String message) {
		super(message);
	}

}
