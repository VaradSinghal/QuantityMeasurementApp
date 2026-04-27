import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // -------- BASIC CONVERSIONS --------

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.Quantity.convert(1,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.Quantity.convert(24,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_YardToFeet() {
        assertEquals(3.0,
                QuantityMeasurementApp.Quantity.convert(1,
                        QuantityMeasurementApp.LengthUnit.YARD,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_CentimeterToInch() {
        assertEquals(1.0,
                QuantityMeasurementApp.Quantity.convert(2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETER,
                        QuantityMeasurementApp.LengthUnit.INCH),
                1e-3);
    }

    // -------- ROUND TRIP --------

    @Test
    void testRoundTripConversion() {
        double value = 5.0;

        double result = QuantityMeasurementApp.Quantity.convert(
                QuantityMeasurementApp.Quantity.convert(value,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        assertEquals(value, result, EPSILON);
    }

    // -------- EDGE CASES --------

    @Test
    void testZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.Quantity.convert(0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testNegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.Quantity.convert(-1,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testSameUnit() {
        assertEquals(5.0,
                QuantityMeasurementApp.Quantity.convert(5,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    // -------- INVALID INPUT --------

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.Quantity.convert(1, null,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.Quantity.convert(Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH));
    }

    // -------- EQUALITY --------

    @Test
    void testEquality_YardToFeet() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(1,
                        QuantityMeasurementApp.LengthUnit.YARD);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(3,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_InchToCm() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETER);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(1,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }
}