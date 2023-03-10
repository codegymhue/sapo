//package vn.sapo.shared.exceptions;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import vn.sapo.shared.Result;
//
//import java.util.Locale;
//import java.util.Map;
//import java.util.stream.Collectors;
//
////https://www.baeldung.com/exception-handling-for-rest-with-spring
//@ControllerAdvice
//public class GExceptionHandler {
//    @Autowired
//    private MessageSource messageSource;
//
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseEntity<?> handleIllegalArgument(Exception ex) {
//        Map<Object, Object> errors;
//        Result<?> result = null;
//        if (ex instanceof MethodArgumentNotValidException) {
//            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
//            errors = bindingResult.getFieldErrors()
//                    .stream()
//                    .collect(Collectors
//                            .toMap(FieldError::getField,
//                                    fieldError -> messageSource.getMessage(fieldError.getDefaultMessage(), new Object[0], Locale.getDefault()   )));
//            result = new Result<>(errors, HttpStatus.BAD_REQUEST.value());
//        }
//        if (ex instanceof NotFoundException) {
//            String message = messageSource.getMessage(ex.getMessage(), new Object[0], Locale.getDefault());
//            result = new Result<>(message, HttpStatus.BAD_REQUEST.value());
//        }
//        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//    }
//}
