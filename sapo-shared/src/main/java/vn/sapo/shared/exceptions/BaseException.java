package vn.sapo.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BaseException extends RuntimeException {

    protected Map<Object, Object> errors;

    public BaseException(Map<Object, Object> errors) {
        this.errors = errors;
    }

    public BaseException(String message) {
        super(message);
    }
}
