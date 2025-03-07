package dev.pedrodias.service_auth.config.exception;

public class HashingException extends RuntimeException {
    public HashingException(String message, Throwable cause) {
        super(message, cause);
    }
}
