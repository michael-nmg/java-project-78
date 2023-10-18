package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

import hexlet.code.requirements.UseRequirement;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        addPredicate(String.class::isInstance);
    }

    public StringSchema required() {
        setState(new UseRequirement());
        addPredicate(Objects::nonNull);
        addPredicate(obj -> !Objects.equals(obj, ""));
        return this;
    }

    public StringSchema minLength(int size) {
        Predicate<Object> predicate = obj -> size <= ((String) obj).length();
        this.addPredicate(predicate);
        return this;
    }

    public StringSchema contains(String chunk) {
        Predicate<Object> predicate = obj -> ((String) obj).contains(chunk);
        this.addPredicate(predicate);
        return this;
    }

}
