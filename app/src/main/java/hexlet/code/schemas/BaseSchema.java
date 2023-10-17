package hexlet.code.schemas;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

import hexlet.code.requirements.Requirement;
import hexlet.code.requirements.NotRequirement;

public abstract class Schema {

    private Requirement requirement = new NotRequirement();
    private final List<Predicate<Object>> predicates = new ArrayList<>();

    public abstract Schema required();

    public boolean isValid(Object object) {
        if (requirement.notUse()) {
            return true;
        }

        if (object == null || object == "") {
            return false;
        }

        return predicates.stream()
                .allMatch(predicate -> predicate.test(object));
    }

    public void addPredicate(Predicate<Object> predicate) {
        predicates.add(predicate);
    }

    public void setState(Requirement requirement) {
        this.requirement = requirement;
    }

}
