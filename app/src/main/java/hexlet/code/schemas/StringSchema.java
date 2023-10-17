package hexlet.code.schemas;

import java.util.function.Predicate;

import hexlet.code.requirements.UseRequirement;

public final class StringSchema extends Schema {

    public StringSchema() {
        addPredicate(String.class::isInstance);
    }

    public StringSchema required() {
        setState(new UseRequirement());
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
