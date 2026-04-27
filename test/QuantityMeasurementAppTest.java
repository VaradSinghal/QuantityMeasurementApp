import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    // -------- FEET TESTS --------
    @Test
    void testFeet_SameValue() {
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0));
    }

    @Test
    void testFeet_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compareFeet(1.0, 2.0));
    }

    // -------- INCHES TESTS --------
    @Test
    void testInches_SameValue() {
        assertTrue(QuantityMeasurementApp.compareInches(1.0, 1.0));
    }

    @Test
    void testInches_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compareInches(1.0, 2.0));
    }

    // -------- COMMON TESTS --------
    @Test
    void testNullComparison() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    void testSameReference() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(i.equals(i));
    }
}