package vn.sapo.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppNotFoundException extends Exception {
    public AppNotFoundException(String message) {
        super(message);
    }
}
