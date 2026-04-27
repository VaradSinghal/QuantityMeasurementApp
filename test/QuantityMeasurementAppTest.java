import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertTrue(new QuantityMeasurementApp.Quantity(1.0,
                QuantityMeasurementApp.LengthUnit.FEET)
                .equals(new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertTrue(new QuantityMeasurementApp.Quantity(1.0,
                QuantityMeasurementApp.LengthUnit.INCH)
                .equals(new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.LengthUnit.INCH)));
    }

    @Test
    void testEquality_FeetToInch_Equivalent() {
        assertTrue(new QuantityMeasurementApp.Quantity(1.0,
                QuantityMeasurementApp.LengthUnit.FEET)
                .equals(new QuantityMeasurementApp.Quantity(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH)));
    }

    @Test
    void testEquality_InchToFeet_Equivalent() {
        assertTrue(new QuantityMeasurementApp.Quantity(12.0,
                QuantityMeasurementApp.LengthUnit.INCH)
                .equals(new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testDifferentValues() {
        assertFalse(new QuantityMeasurementApp.Quantity(1.0,
                QuantityMeasurementApp.LengthUnit.FEET)
                .equals(new QuantityMeasurementApp.Quantity(2.0,
                        QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testNullComparison() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q.equals(null));
    }

    @Test
    void testSameReference() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.Quantity(1.0, null);
        });
    }
}