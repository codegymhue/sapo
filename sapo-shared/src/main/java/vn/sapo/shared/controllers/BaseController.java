package vn.sapo.shared.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.sapo.shared.Result;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseController {
    @Autowired
    private MessageSource messageSource;

//    @ExceptionHandler({MethodArgumentNotValidException.class, NotFoundException.class})
//    @ResponseBody
//    public ResponseEntity<?> handleValidationException(Exception ex, Locale locale) {
//        Map<Object, Object> errors  ;
//        ResponseEntity<?> response = null;
//        Result<Object> result;
//        if (ex instanceof MethodArgumentNotValidException) {
//            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
//            errors = bindingResult.getFieldErrors()
//                    .stream()
//                    .collect(Collectors
//                            .toMap(FieldError::getField,
//                                    FieldError::getDefaultMessage));
//
//            result = new Result<>(errors, HttpStatus.BAD_REQUEST.value());
//            response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//        }
//        if (ex instanceof NotFoundException) {
//            String message = messageSource.getMessage(ex.getMessage(), new Object[0], locale);
//            result = new Result<>(message, HttpStatus.NOT_FOUND.value());
//            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND    );
//        }
//        return response;
//    }

}
