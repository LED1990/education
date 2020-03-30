package app.restmvc.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomResourceNotFoundException extends RuntimeException{

    public CustomResourceNotFoundException() {
        log.error("Custom resource not found exceptions");
    }

    public CustomResourceNotFoundException(String message) {
        super(message);
        log.error("Custom resource not found exceptions");
    }

    public CustomResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        log.error("Custom resource not found exceptions");
    }

    public CustomResourceNotFoundException(Throwable cause) {
        super(cause);
        log.error("Custom resource not found exceptions");
    }

    public CustomResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error("Custom resource not found exceptions");
    }
}
