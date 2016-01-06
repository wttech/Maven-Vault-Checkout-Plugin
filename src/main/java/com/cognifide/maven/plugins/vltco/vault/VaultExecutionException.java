package com.cognifide.maven.plugins.vltco.vault;

public class VaultExecutionException extends RuntimeException {

	private static final long serialVersionUID = -4639309701219052464L;

	public VaultExecutionException() {
		super();
	}

	public VaultExecutionException(String message) {
		super(message);
	}

	public VaultExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public VaultExecutionException(Throwable cause) {
		super(cause);
	}

	protected VaultExecutionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
