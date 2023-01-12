package vn.sapo.shared.parsers;

import com.jayway.jsonpath.*;

import java.util.Map;
import java.util.Set;

public class JsonPathParser {
    public static final JsonPathParser INSTANCE = new JsonPathParser();
    private ParseContext parseContext;

    private JsonPathParser() {
        Configuration configuration = Configuration.defaultConfiguration();
        configuration.addOptions(Option.ALWAYS_RETURN_LIST);
        configuration.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        parseContext = JsonPath.using(configuration);
    }

    public ReadContext parse(String json) {
        return parseContext.parse(json);
    }

    public static <T extends Map<String, Object>> T deserialize(Class<T> clazz, String json,
                                                                Map<String, String> jsonPathFilter) {
        T instance = null;
        try {
            ReadContext readContext = INSTANCE.parse(json);
            instance = clazz.newInstance();
            Set<String> keySet = jsonPathFilter.keySet();
            for (String key : keySet) {
                Object value = readContext.read(jsonPathFilter.get(key));
                instance.put(key, value);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return instance;
    }
}
