package vn.sapo.shared.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.sapo.shared.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseController {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result<?> handleValidationException(Exception ex) {

        Map<Object, Object> errors = new HashMap<>();

        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();

            errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors
                            .toMap(FieldError::getField, FieldError::getDefaultMessage));
        }

        return new Result<>(errors, HttpStatus.BAD_REQUEST.value());
    }
}
