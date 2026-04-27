import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // -------- SAME UNIT --------
    @Test
    void testAddition_FeetPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(2,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(new QuantityMeasurementApp.Quantity(3,
                QuantityMeasurementApp.LengthUnit.FEET), q1.add(q2));
    }

    // -------- CROSS UNIT --------
    @Test
    void testAddition_FeetPlusInch() {
        var q1 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(12,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(new QuantityMeasurementApp.Quantity(2,
                QuantityMeasurementApp.LengthUnit.FEET), q1.add(q2));
    }

    @Test
    void testAddition_InchPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(12,
                QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(new QuantityMeasurementApp.Quantity(24,
                QuantityMeasurementApp.LengthUnit.INCH), q1.add(q2));
    }

    // -------- YARD --------
    @Test
    void testAddition_YardPlusFeet() {
        var q1 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.YARD);
        var q2 = new QuantityMeasurementApp.Quantity(3,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(new QuantityMeasurementApp.Quantity(2,
                QuantityMeasurementApp.LengthUnit.YARD), q1.add(q2));
    }

    // -------- CM --------
    @Test
    void testAddition_CmPlusInch() {
        var q1 = new QuantityMeasurementApp.Quantity(2.54,
                QuantityMeasurementApp.LengthUnit.CENTIMETER);
        var q2 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.add(q2).equals(
                new QuantityMeasurementApp.Quantity(5.08,
                        QuantityMeasurementApp.LengthUnit.CENTIMETER)
        ));
    }

    // -------- COMMUTATIVE --------
    @Test
    void testAddition_Commutative() {
        var a = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.Quantity(12,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(a.add(b).equals(b.add(a)));
    }

    // -------- ZERO --------
    @Test
    void testAddition_WithZero() {
        var q1 = new QuantityMeasurementApp.Quantity(5,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(q1, q1.add(q2));
    }

    // -------- NEGATIVE --------
    @Test
    void testAddition_Negative() {
        var q1 = new QuantityMeasurementApp.Quantity(5,
                QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(-2,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(new QuantityMeasurementApp.Quantity(3,
                QuantityMeasurementApp.LengthUnit.FEET), q1.add(q2));
    }

    // -------- NULL --------
    @Test
    void testAddition_Null() {
        var q1 = new QuantityMeasurementApp.Quantity(1,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }
}