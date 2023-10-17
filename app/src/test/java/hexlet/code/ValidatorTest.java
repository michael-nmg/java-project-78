package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void init() {
        validator = new Validator();
    }

    @Test
    void stringSchemaTest() {
        var actual = validator.string().getClass();
        var expected = StringSchema.class;
        assertEquals(expected, actual);
    }

    @Test
    void numberSchemaTest() {
        var actual = validator.number().getClass();
        var expected = NumberSchema.class;
        assertEquals(expected, actual);
    }

}
