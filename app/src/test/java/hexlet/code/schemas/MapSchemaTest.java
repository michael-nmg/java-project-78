package hexlet.code.schemas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import hexlet.code.Validator;

import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MapSchemaTest {

    private MapSchema schema;
    private Validator validator;
    private final Map<String, String> data = new HashMap<>();

    @BeforeEach
    void init() {
        validator = new Validator();
        schema = validator.map();
        data.put("key1", "value1");
    }

    @Test
    void nonRequiredTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.sizeof(0).isValid(null));
        assertFalse(schema.sizeof(0).isValid(5));
    }

    @Test
    void requiredTest() {
        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(data));
    }

    @Test
    void sizeofTest() {
        schema.required().sizeof(2);

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void shapeTest() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4));
    }

    @Test
    void fullTest() {
        var actual1 = validator.map()
                .required()
                .sizeof(1)
                .isValid(data);
        assertTrue(actual1);

        var actual2 = validator.map()
                .required()
                .sizeof(2)
                .isValid(data);
        assertFalse(actual2);
    }

}
