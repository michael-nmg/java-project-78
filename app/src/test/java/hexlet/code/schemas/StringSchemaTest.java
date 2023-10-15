package hexlet.code.schemas;

import hexlet.code.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringSchemaTest {

    private Validator validator;
    private StringSchema schema;

    @BeforeEach
    void init() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    void nonRequiredTest() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void requiredTest() {
        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(5));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void containsTest() {
        schema.required();

        var actual1 = schema.contains("wh").isValid("what does the fox say");
        assertTrue(actual1);

        var actual2 = schema.contains("what").isValid("what does the fox say");
        assertTrue(actual2);

        var actual3 = schema.contains("whatthe").isValid("what does the fox say");
        assertFalse(actual3);

        var actual4 = schema.isValid("what does the fox say");
        assertFalse(actual4);
    }

    @Test
    void minLength() {
        schema.required();

        var actual1 = schema.minLength(5).isValid("word");
        assertFalse(actual1);

        var actual2 = schema.minLength(5).isValid("word123");
        assertTrue(actual2);
    }

    @Test
    void fullTest() {
        var actual1 = validator.string()
                .required()
                .minLength(5)
                .contains("hex")
                .isValid("hexlet");
        assertTrue(actual1);

        var actual2 = validator.string()
                .required()
                .minLength(5)
                .contains("gex")
                .isValid("gexl");
        assertFalse(actual2);
    }

}
