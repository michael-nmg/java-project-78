### Project "Data Validator"

#### Tests and linter status:
[![Actions Status](https://github.com/michael-nmg/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/michael-nmg/java-project-78/actions) [![Java CI Status](https://github.com/michael-nmg/java-project-78/workflows/java-ci/badge.svg)](https://github.com/michael-nmg/java-project-78/actions) [![Maintainability](https://api.codeclimate.com/v1/badges/e5a987b686ea6e255c0f/maintainability)](https://codeclimate.com/github/michael-nmg/java-project-78/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/e5a987b686ea6e255c0f/test_coverage)](https://codeclimate.com/github/michael-nmg/java-project-78/test_coverage)

#### Description:

A data validator is a library with which you can check the correctness of any data. There are many similar libraries in every language, since almost all programs work with external data that needs to be checked for correctness. First of all, we are talking about the data of forms filled in by users. The [yup](https://github.com/jquense/yup) library is taken as the basis for the project.

#### String validation:

Following validators are available:
* `required` – not null and any non-empty string
* `minLength` – string is equal to or longer than argument
* `contains` – string contains a specific substring

##### Example:

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
// not called yet required(), null is valid
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(5); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// false, added another check contains("whatthe")
```

#### Number validation:

Following validators are available:
* `required` – any integer other than null
* `positive` – positive number
* `range` – segment to which the number should belong (including borders)

##### Example:

```java
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();

NumberSchema schema = v.number();

// not called yet required(), null is valid
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false
schema.isValid(-10); // false
// 0 (zero) - is not a positive number
schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```

#### Map-object validation:

Following validators are available:
* `required` – Map data type is required
* `sizeof` – specified number of pairs of key-value

##### Example:

```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```

#### Embedded validation

* `shape` – validating data inside a Map object

##### Example:

```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

MapSchema schema = v.map();

// shape - description of key-value verification

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```

