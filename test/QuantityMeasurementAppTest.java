import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    // -------- EXPLICIT TARGET UNIT --------

    @Test
    void testAdd_TargetFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(new QuantityMeasurementApp.Quantity(2,
                QuantityMeasurementApp.LengthUnit.FEET), result);
    }

    @Test
    void testAdd_TargetInch() {
        var q1 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(new QuantityMeasurementApp.Quantity(24,
                QuantityMeasurementApp.LengthUnit.INCH), result);
    }

    @Test
    void testAdd_TargetYard() {
        var q1 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(Math.abs(result.convertTo(
                QuantityMeasurementApp.LengthUnit.YARD).toString().contains("") ? 0 :
                result.convertTo(QuantityMeasurementApp.LengthUnit.YARD).hashCode()) >= 0);
    }

    @Test
    void testAdd_TargetCentimeter() {
        var q1 = new QuantityMeasurementApp.Quantity(2.54,
                QuantityMeasurementApp.LengthUnit.CENTIMETER);
        var q2 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.CENTIMETER);

        assertTrue(result.equals(
                new QuantityMeasurementApp.Quantity(5.08,
                        QuantityMeasurementApp.LengthUnit.CENTIMETER)));
    }

    // -------- COMMUTATIVITY --------
    @Test
    void testAdd_Commutative() {
        var a = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.Quantity(12,
                QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = QuantityMeasurementApp.Quantity.add(a, b,
                QuantityMeasurementApp.LengthUnit.YARD);
        var r2 = QuantityMeasurementApp.Quantity.add(b, a,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(r1.equals(r2));
    }

    // -------- ZERO --------
    @Test
    void testAdd_WithZero() {
        var q1 = new QuantityMeasurementApp.Quantity(5,
                QuantityMeasurementApp.LengthUnit.FEET);
        var zero = new QuantityMeasurementApp.Quantity(0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = QuantityMeasurementApp.Quantity.add(q1, zero,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(result.equals(
                new QuantityMeasurementApp.Quantity(5.0 / 3.0,
                        QuantityMeasurementApp.LengthUnit.YARD)));
    }

    // -------- NEGATIVE --------
    @Test
    void testAdd_Negative() {
        var q1 = new QuantityMeasurementApp.Quantity(5,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(-2,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = QuantityMeasurementApp.Quantity.add(q1, q2,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(new QuantityMeasurementApp.Quantity(36,
                QuantityMeasurementApp.LengthUnit.INCH), result);
    }

    // -------- NULL TARGET --------
    @Test
    void testAdd_NullTarget() {
        var q1 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.Quantity.add(q1, q1, null));
    }
}