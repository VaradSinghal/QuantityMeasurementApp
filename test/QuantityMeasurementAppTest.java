// QuantityMeasurementTest.java
// UC12 Comprehensive JUnit Tests for Equality, Conversion, Addition, Subtraction, Division

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    private static final double EPSILON = 1e-2;

    /* ================= LENGTH TESTS ================= */

    @Test
    void testLengthEquality() {
        assertEquals(
                new Quantity<>(1, LengthUnit.FEET),
                new Quantity<>(12, LengthUnit.INCHES)
        );
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> result =
                new Quantity<>(1, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthAddition() {
        Quantity<LengthUnit> result =
                new Quantity<>(1, LengthUnit.FEET)
                        .add(new Quantity<>(12, LengthUnit.INCHES));

        assertEquals(new Quantity<>(2, LengthUnit.FEET), result);
    }

    @Test
    void testLengthSubtraction() {
        Quantity<LengthUnit> result =
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(6, LengthUnit.INCHES));

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testLengthSubtractionExplicitTarget() {
        Quantity<LengthUnit> result =
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(6, LengthUnit.INCHES), LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthDivision() {
        double result =
                new Quantity<>(24, LengthUnit.INCHES)
                        .divide(new Quantity<>(2, LengthUnit.FEET));

        assertEquals(1.0, result, EPSILON);
    }

    /* ================= WEIGHT TESTS ================= */

    @Test
    void testWeightEquality() {
        assertEquals(
                new Quantity<>(1, WeightUnit.KILOGRAM),
                new Quantity<>(1000, WeightUnit.GRAM)
        );
    }

    @Test
    void testWeightConversion() {
        Quantity<WeightUnit> result =
                new Quantity<>(1, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightAddition() {
        Quantity<WeightUnit> result =
                new Quantity<>(1, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000, WeightUnit.GRAM));

        assertEquals(new Quantity<>(2, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testWeightSubtraction() {
        Quantity<WeightUnit> result =
                new Quantity<>(10, WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(5000, WeightUnit.GRAM));

        assertEquals(new Quantity<>(5, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testWeightDivision() {
        double result =
                new Quantity<>(2000, WeightUnit.GRAM)
                        .divide(new Quantity<>(1, WeightUnit.KILOGRAM));

        assertEquals(2.0, result, EPSILON);
    }

    /* ================= VOLUME TESTS ================= */

    @Test
    void testVolumeEquality() {
        assertEquals(
                new Quantity<>(1, VolumeUnit.LITRE),
                new Quantity<>(1000, VolumeUnit.MILLILITRE)
        );
    }

    @Test
    void testVolumeGallonEquality() {
        assertEquals(
                new Quantity<>(1, VolumeUnit.GALLON),
                new Quantity<>(3.78541, VolumeUnit.LITRE)
        );
    }

    @Test
    void testVolumeConversion() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testVolumeAddition() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(2, VolumeUnit.LITRE), result);
    }

    @Test
    void testVolumeAdditionExplicitTarget() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1, VolumeUnit.LITRE)
                        .add(new Quantity<>(1, VolumeUnit.GALLON), VolumeUnit.MILLILITRE);

        assertEquals(4785.41, result.getValue(), 0.1);
    }

    @Test
    void testVolumeSubtraction() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(500, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(4.5, VolumeUnit.LITRE), result);
    }

    @Test
    void testVolumeSubtractionExplicitTarget() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);

        assertEquals(3000.0, result.getValue(), EPSILON);
    }

    @Test
    void testVolumeDivision() {
        double result =
                new Quantity<>(1000, VolumeUnit.MILLILITRE)
                        .divide(new Quantity<>(1, VolumeUnit.LITRE));

        assertEquals(1.0, result, EPSILON);
    }

    /* ================= EDGE CASES ================= */

    @Test
    void testSubtractionNegativeResult() {
        Quantity<LengthUnit> result =
                new Quantity<>(5, LengthUnit.FEET)
                        .subtract(new Quantity<>(10, LengthUnit.FEET));

        assertEquals(new Quantity<>(-5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtractionZeroResult() {
        Quantity<LengthUnit> result =
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(120, LengthUnit.INCHES));

        assertEquals(new Quantity<>(0, LengthUnit.FEET), result);
    }

    @Test
    void testDivisionGreaterThanOne() {
        double result =
                new Quantity<>(10, LengthUnit.FEET)
                        .divide(new Quantity<>(2, LengthUnit.FEET));

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testDivisionLessThanOne() {
        double result =
                new Quantity<>(5, LengthUnit.FEET)
                        .divide(new Quantity<>(10, LengthUnit.FEET));

        assertEquals(0.5, result, EPSILON);
    }

    /* ================= ERROR HANDLING ================= */

    @Test
    void testNullUnitConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1, null));
    }

    @Test
    void testNullSubtraction() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10, LengthUnit.FEET).subtract(null));
    }

    @Test
    void testNullDivision() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10, LengthUnit.FEET).divide(null));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class,
                () -> new Quantity<>(10, LengthUnit.FEET)
                        .divide(new Quantity<>(0, LengthUnit.FEET)));
    }

    @Test
    void testCrossCategoryEquality() {
        assertNotEquals(
                new Quantity<>(1, VolumeUnit.LITRE),
                new Quantity<>(1, LengthUnit.FEET)
        );
    }

    @Test
    void testCrossCategorySubtractionCompileSafety() {
        assertFalse(
                new Quantity<>(1, VolumeUnit.LITRE)
                        .equals(new Quantity<>(1, WeightUnit.KILOGRAM))
        );
    }

    /* ================= IMMUTABILITY ================= */

    @Test
    void testImmutabilityAfterAddition() {
        Quantity<LengthUnit> original = new Quantity<>(1, LengthUnit.FEET);
        original.add(new Quantity<>(12, LengthUnit.INCHES));

        assertEquals(new Quantity<>(1, LengthUnit.FEET), original);
    }

    @Test
    void testImmutabilityAfterSubtraction() {
        Quantity<LengthUnit> original = new Quantity<>(10, LengthUnit.FEET);
        original.subtract(new Quantity<>(5, LengthUnit.FEET));

        assertEquals(new Quantity<>(10, LengthUnit.FEET), original);
    }

    @Test
    void testImmutabilityAfterDivision() {
        Quantity<LengthUnit> original = new Quantity<>(10, LengthUnit.FEET);
        original.divide(new Quantity<>(2, LengthUnit.FEET));

        assertEquals(new Quantity<>(10, LengthUnit.FEET), original);
    }

    /* ================= CHAIN OPERATIONS ================= */

    @Test
    void testSubtractionChain() {
        Quantity<LengthUnit> result =
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(2, LengthUnit.FEET))
                        .subtract(new Quantity<>(1, LengthUnit.FEET));

        assertEquals(new Quantity<>(7, LengthUnit.FEET), result);
    }

    @Test
    void testAdditionSubtractionInverse() {
        Quantity<VolumeUnit> original = new Quantity<>(5, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                original.add(new Quantity<>(2, VolumeUnit.LITRE))
                        .subtract(new Quantity<>(2, VolumeUnit.LITRE));

        assertEquals(original, result);
    }
}