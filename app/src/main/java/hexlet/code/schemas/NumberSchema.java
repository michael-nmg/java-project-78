package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

import hexlet.code.requirements.UseRequirement;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addPredicate(Integer.class::isInstance);
    }

    public NumberSchema required() {
        setState(new UseRequirement());
        addPredicate(Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> predicate = object -> Integer.signum(((Integer) object)) > 0;
        this.addPredicate(predicate);
        return this;
    }

    public NumberSchema range(int left, int right) {
        Predicate<Object> predicate = object -> left <= ((Integer) object) && ((Integer) object) <= right;
        this.addPredicate(predicate);
        return this;
    }

}
