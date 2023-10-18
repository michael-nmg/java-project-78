package hexlet.code.schemas;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

import hexlet.code.requirements.Requirement;
import hexlet.code.requirements.NotRequirement;

public abstract class BaseSchema {

    private Requirement requirement = new NotRequirement();
    private final List<Predicate<Object>> predicates = new ArrayList<>();

    public abstract BaseSchema required();

    public final boolean isValid(Object object) {
        if (requirement.notUsed() && (object == null || object == "")) {
            return true;
        }

        return predicates.stream()
                .allMatch(predicate -> predicate.test(object));
    }

    public final void addPredicate(Predicate<Object> predicate) {
        predicates.add(predicate);
    }

    public final void setState(Requirement state) {
        this.requirement = state;
    }

}
