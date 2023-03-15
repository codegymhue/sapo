package vn.sapo.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends BaseException {
    public ValidationException(Map<String, String> errors) {
        super(errors, HttpStatus.BAD_REQUEST.value());
    }

    public ValidationException(String field, String message) {
        super(new HashMap<>() {{
            put(field, message);
        }}, HttpStatus.BAD_REQUEST.value());
    }

    public ValidationException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
