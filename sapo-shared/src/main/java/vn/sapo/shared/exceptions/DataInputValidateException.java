package vn.sapo.shared.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataInputValidateException extends RuntimeException {

    private Map errors;

    public DataInputValidateException(Map errors) {
        this.errors = errors;
    }
}
