package hexlet.code.schemas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import hexlet.code.Validator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {

    private Validator validator;
    private NumberSchema schema;

    @BeforeEach
    void init() {
        validator = new Validator();
        schema = validator.number();
    }

    @Test
    void nonRequiredTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
        assertTrue(schema.range(1, 2).isValid(null));
    }

    @Test
    void requiredTest() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
        assertTrue(schema.isValid(10));
    }

    @Test
    void positiveTest() {
        schema.required().positive();
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));

    }

    @Test
    void rangeTest() {
        schema.required().range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void fullTest() {
        var actual1 = validator.number()
                .required()
                .positive()
                .range(0, 2)
                .isValid(1);
        assertTrue(actual1);

        var actual2 = validator.number()
                .required()
                .positive()
                .range(0, 2)
                .isValid(-1);
        assertFalse(actual2);
    }

}
