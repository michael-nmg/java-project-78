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
    private Map<String, String> data = new HashMap<>();

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
        assertTrue(schema.sizeof(0).isValid(5));
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
