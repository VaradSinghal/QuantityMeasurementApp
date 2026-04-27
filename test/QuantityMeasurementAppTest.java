import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // UC1 + UC3 Equality
    @Test
    void testEquality_FeetToFeet() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(1.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_FeetToInches() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testEquality_NotEqual() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(2.0, LengthUnit.FEET)
        );
    }

    // UC4 (yards, cm)
    @Test
    void testEquality_YardsToFeet() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_CmToInches() {
        assertEquals(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES)
        );
    }

    // UC5 Conversion
    @Test
    void testConvert_FeetToInches() {
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConvert_InchesToFeet() {
        assertEquals(2.0,
                QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET),
                EPSILON);
    }

    // UC6 Addition
    @Test
    void testAddition_FeetPlusFeet() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(2.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_FeetPlusInches() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    // UC7 Target Unit
    @Test
    void testAddition_TargetUnit_Inches() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES),
                                LengthUnit.INCHES);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_TargetUnit_Yards() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES),
                                LengthUnit.YARDS);

        assertEquals(0.666666, result.convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS), EPSILON);
    }

    // Edge cases
    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testNaNValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }
}