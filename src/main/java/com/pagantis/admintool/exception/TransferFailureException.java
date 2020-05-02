package com.pagantis.admintool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("deprecation")
@ResponseStatus(HttpStatus.METHOD_FAILURE)
public class TransferFailureException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public TransferFailureException() {
        super();
    }

    public TransferFailureException(String message) {
        super(message);
    }

    public TransferFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
