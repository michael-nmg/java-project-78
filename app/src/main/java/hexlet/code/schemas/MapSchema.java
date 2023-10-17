package hexlet.code.schemas;

import hexlet.code.requirements.UseRequirement;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema required() {
        this.setState(new UseRequirement());
        this.addPredicate(Map.class::isInstance);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> predicate = map -> ((Map) map).size() == size;
        this.addPredicate(predicate);
        return this;
    }

}
