// QuantityMeasurementTest.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    private static final double EPSILON = 1e-6;

    /* ================= LENGTH TESTS ================= */

    @Test
    void testLengthEquality() {
        assertEquals(
                new Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET),
                new Quantity<>(12, QuantityMeasurementApp.LengthUnit.INCHES)
        );
    }

    @Test
    void testLengthInequality() {
        assertNotEquals(
                new Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET),
                new Quantity<>(13, QuantityMeasurementApp.LengthUnit.INCHES)
        );
    }

    @Test
    void testLengthConversion_FeetToInches() {
        Quantity<QuantityMeasurementApp.LengthUnit> result =
                new Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET)
                        .convertTo(QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthConversion_YardsToFeet() {
        Quantity<QuantityMeasurementApp.LengthUnit> result =
                new Quantity<>(1, QuantityMeasurementApp.LengthUnit.YARDS)
                        .convertTo(QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthAddition_DefaultUnit() {
        Quantity<QuantityMeasurementApp.LengthUnit> result =
                new Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET)
                        .add(new Quantity<>(12, QuantityMeasurementApp.LengthUnit.INCHES));

        assertEquals(
                new Quantity<>(2, QuantityMeasurementApp.LengthUnit.FEET),
                result
        );
    }

    @Test
    void testLengthAddition_WithExplicitTargetUnit() {
        Quantity<QuantityMeasurementApp.LengthUnit> result =
                new Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET)
                        .add(
                                new Quantity<>(12, QuantityMeasurementApp.LengthUnit.INCHES),
                                QuantityMeasurementApp.LengthUnit.INCHES
                        );

        assertEquals(
                new Quantity<>(24, QuantityMeasurementApp.LengthUnit.INCHES),
                result
        );
    }

    /* ================= WEIGHT TESTS ================= */

    @Test
    void testWeightEquality() {
        assertEquals(
                new Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM),
                new Quantity<>(1000, QuantityMeasurementApp.WeightUnit.GRAM)
        );
    }

    @Test
    void testWeightInequality() {
        assertNotEquals(
                new Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM),
                new Quantity<>(999, QuantityMeasurementApp.WeightUnit.GRAM)
        );
    }

    @Test
    void testWeightConversion_KgToGram() {
        Quantity<QuantityMeasurementApp.WeightUnit> result =
                new Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM)
                        .convertTo(QuantityMeasurementApp.WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightConversion_PoundToKilogram() {
        Quantity<QuantityMeasurementApp.WeightUnit> result =
                new Quantity<>(2.20462, QuantityMeasurementApp.WeightUnit.POUND)
                        .convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-2);
    }

    @Test
    void testWeightAddition_DefaultUnit() {
        Quantity<QuantityMeasurementApp.WeightUnit> result =
                new Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000, QuantityMeasurementApp.WeightUnit.GRAM));

        assertEquals(
                new Quantity<>(2, QuantityMeasurementApp.WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testWeightAddition_WithTargetUnit() {
        Quantity<QuantityMeasurementApp.WeightUnit> result =
                new Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM)
                        .add(
                                new Quantity<>(1000, QuantityMeasurementApp.WeightUnit.GRAM),
                                QuantityMeasurementApp.WeightUnit.GRAM
                        );

        assertEquals(
                new Quantity<>(2000, QuantityMeasurementApp.WeightUnit.GRAM),
                result
        );
    }

    /* ================= GENERIC / TYPE SAFETY TESTS ================= */

    @Test
    void testCrossCategoryComparison() {
        Quantity<QuantityMeasurementApp.LengthUnit> length =
                new Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET);

        Quantity<QuantityMeasurementApp.WeightUnit> weight =
                new Quantity<>(1, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    @Test
    void testNullUnitValidation() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1, null)
        );
    }

    @Test
    void testInvalidValueValidation() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, QuantityMeasurementApp.LengthUnit.FEET)
        );
    }

    @Test
    void testAdditionWithNullTargetUnit() {
        Quantity<QuantityMeasurementApp.LengthUnit> length =
                new Quantity<>(1, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.add(
                        new Quantity<>(12, QuantityMeasurementApp.LengthUnit.INCHES),
                        null
                )
        );
    }

    @Test
    void testRoundTripConversion_Length() {
        Quantity<QuantityMeasurementApp.LengthUnit> original =
                new Quantity<>(5, QuantityMeasurementApp.LengthUnit.FEET);

        Quantity<QuantityMeasurementApp.LengthUnit> result =
                original.convertTo(QuantityMeasurementApp.LengthUnit.INCHES)
                        .convertTo(QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(original, result);
    }

    @Test
    void testRoundTripConversion_Weight() {
        Quantity<QuantityMeasurementApp.WeightUnit> original =
                new Quantity<>(2, QuantityMeasurementApp.WeightUnit.KILOGRAM);

        Quantity<QuantityMeasurementApp.WeightUnit> result =
                original.convertTo(QuantityMeasurementApp.WeightUnit.POUND)
                        .convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertEquals(original, result);
    }
}