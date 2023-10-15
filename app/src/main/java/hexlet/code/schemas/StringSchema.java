package hexlet.code.schemas;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.function.Predicate;

public class StringSchema implements Schema {

    List<Predicate<Object>> parametrs = new ArrayList<>();

    public StringSchema required() {
        Predicate<Object> predicate = object -> {
            if (Objects.nonNull(object)) {
                if (object.getClass().equals(String.class)) {
                    return !((String) object).isEmpty();
                }
            }
            return false;
        };
        parametrs.add(predicate);
        return this;
    }

    public StringSchema minLength(int size) {
        Predicate<Object> predicate = str -> size <= ((String) str).length();
        parametrs.add(predicate);
        return this;
    }

    public StringSchema contains(String chunk) {
        Predicate<Object> predicate = str -> ((String) str).contains(chunk);
        parametrs.add(predicate);
        return this;
    }

    public boolean isValid(Object object) {
        boolean result = true;
        for (var param : parametrs) {
            result &= param.test(object);
        }
        return result;
    }

}
