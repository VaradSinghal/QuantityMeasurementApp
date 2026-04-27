// QuantityMeasurementTest.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    /* ================= LENGTH TESTS ================= */

    @Test
    void testLengthEquality() {
        assertEquals(
                new QuantityLength(1, LengthUnit.FEET),
                new QuantityLength(12, LengthUnit.INCHES)
        );
    }

    @Test
    void testLengthConversion() {
        QuantityLength result =
                new QuantityLength(1, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES).convertTo(LengthUnit.INCHES), 1e-6);
    }

    @Test
    void testLengthAddition() {
        QuantityLength result =
                new QuantityLength(1, LengthUnit.FEET)
                        .add(new QuantityLength(12, LengthUnit.INCHES));

        assertEquals(new QuantityLength(2, LengthUnit.FEET), result);
    }

    /* ================= WEIGHT TESTS ================= */

    @Test
    void testWeightEquality() {
        assertEquals(
                new QuantityWeight(1, WeightUnit.KILOGRAM),
                new QuantityWeight(1000, WeightUnit.GRAM)
        );
    }

    @Test
    void testWeightConversion() {
        QuantityWeight result =
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.GRAM), 1e-6);
    }

    @Test
    void testWeightAddition() {
        QuantityWeight result =
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000, WeightUnit.GRAM));

        assertEquals(new QuantityWeight(2, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testWeightAdditionWithTarget() {
        QuantityWeight result =
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(new QuantityWeight(2000, WeightUnit.GRAM), result);
    }
}