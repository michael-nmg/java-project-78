package hexlet.code.schemas;

import hexlet.code.requirements.UseRequirement;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        this.addPredicate(Map.class::isInstance);
    }

    public MapSchema required() {
        this.setState(new UseRequirement());
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> predicate = map -> ((Map) map).size() == size;
        this.addPredicate(predicate);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Object> predicate = map -> schemas.entrySet().stream()
                .allMatch(entry -> {
                    Object key = entry.getKey();
                    BaseSchema schema = entry.getValue();
                    return schema.isValid(((Map) map).get(key));
                });
        this.addPredicate(predicate);
        return this;
    }

}
