package vn.sapo.shared.parsers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @author Bien Ly
 * @version 1.0.0
 * @Email lnbienit@gmail.com
 */
@SuppressWarnings("unchecked")
public class JacksonParser {
    public static final JacksonParser INSTANCE = new JacksonParser();
    private ObjectMapper mapper;

    private JacksonParser() {
        mapper = new ObjectMapper();
        mapper.setDefaultPropertyInclusion(Value.construct(Include.ALWAYS, Include.NON_NULL));
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(Include.NON_DEFAULT);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public <T> List<T> toList(String jsonString, Class<T> cls) throws JsonParseException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, cls);
        try {
            return (List<T>) mapper.readValue(jsonString, listType);
        } catch (Exception e) {
            throw new JsonParseException("Unnable to parse json string to list/array!", e);
        }
    }

    public <T> T toObject(String jsonString, Class<T> baseCls, Class<?>... paramCls) throws JsonParseException {
        JavaType type = TypeFactory.defaultInstance().constructParametricType(baseCls, paramCls);
        try {
            return (T) mapper.readValue(jsonString, type);
        } catch (Exception e) {
            throw new JsonParseException("Unnable to parse json string to parametric object!", e);
        }
    }

    public <T> T[] toArray(String jsonString, Class<T> cls) throws JsonParseException {
        List<T> list = toList(jsonString, cls);
        T[] result = (T[]) Array.newInstance(cls, list.size());
        list.toArray(result);
        return result;
    }

    public String toJson(Object value) throws JsonParseException {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new JsonParseException("Can not parse object to Json!", e);
        }
    }

    public <T> T toObject(String jsonString, Class<T> cls) throws JsonParseException {
        try {
            return mapper.readValue(jsonString, cls);
        } catch (Exception e) {
            throw new JsonParseException("Unnable to parse json string to object! Json String:" + jsonString, e);
        }
    }

    public static class JsonParseException extends RuntimeException {
        public JsonParseException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }
}