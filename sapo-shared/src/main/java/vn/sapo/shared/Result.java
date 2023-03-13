package vn.sapo.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Result<T> {
    private T data;
    private Integer status;
    private String message;
    private Map<Object, Object> errors;

    public Result(T data, Integer status, String message, Map<Object, Object> errors) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public Result(T data, Integer status) {
        this(data, null, null, null);
    }


    public Result(Map<Object, Object> errors, Integer status) {
        this(null, status, null, errors);
    }

    public Result(T data, String message, Integer status) {
        this(data, status, message, null);
    }

    public Result(String message, Map<Object, Object> errors, Integer status) {
        this(null, status, message, errors);
    }

    public Result(String message, Integer status) {
        this(null, status, message, null);
    }
}
