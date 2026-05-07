// UC11QuantityMeasurementTest.java
// JUnit 5 test file for VolumeUnit + Generic Quantity<U>
// Assumes:
// - Quantity<U extends IMeasurable>
// - LengthUnit, WeightUnit, VolumeUnit
// - IMeasurable already implemented

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UC11QuantityMeasurementTest {

    private static final double EPSILON = 1e-4;

    /* =========================
       VOLUME EQUALITY TESTS
       ========================= */

    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(2.0, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
        );
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.GALLON),
                new Quantity<>(3.78541, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_MillilitreToGallon_EquivalentValue() {
        assertEquals(
                new Quantity<>(3785.41, VolumeUnit.MILLILITRE),
                new Quantity<>(1.0, VolumeUnit.GALLON)
        );
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testEquality_NullComparison() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                null
        );
    }

    @Test
    void testEquality_SameReference() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(volume, volume);
    }

    /* =========================
       CONSTRUCTOR VALIDATION
       ========================= */

    @Test
    void testConstructor_NullUnit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null)
        );
    }

    @Test
    void testConstructor_InvalidValue() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, VolumeUnit.LITRE)
        );
    }

    /* =========================
       VOLUME CONVERSION TESTS
       ========================= */

    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.GALLON);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        Quantity<VolumeUnit> original =
                new Quantity<>(1.5, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                original.convertTo(VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(original.getValue(), result.getValue(), EPSILON);
    }

    /* =========================
       VOLUME ADDITION TESTS
       ========================= */

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(
                new Quantity<>(3.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(
                new Quantity<>(2.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_GallonPlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .add(new Quantity<>(3.78541, VolumeUnit.LITRE));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(
                                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE
                        );

        assertEquals(
                new Quantity<>(2000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .add(
                                new Quantity<>(3.78541, VolumeUnit.LITRE),
                                VolumeUnit.GALLON
                        );

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_WithZero() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(
                new Quantity<>(5.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testAddition_NegativeValues() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(
                new Quantity<>(3.0, VolumeUnit.LITRE),
                result
        );
    }

    /* =========================
       VOLUME UNIT ENUM TESTS
       ========================= */

    @Test
    void testVolumeUnit_LitreFactor() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
    }

    @Test
    void testVolumeUnit_MillilitreFactor() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
    }

    @Test
    void testVolumeUnit_GallonFactor() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_MillilitreToLitre() {
        assertEquals(
                1.0,
                VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0),
                EPSILON
        );
    }

    @Test
    void testConvertToBaseUnit_GallonToLitre() {
        assertEquals(
                3.78541,
                VolumeUnit.GALLON.convertToBaseUnit(1.0),
                EPSILON
        );
    }

    @Test
    void testConvertFromBaseUnit_LitreToMillilitre() {
        assertEquals(
                1000.0,
                VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0),
                EPSILON
        );
    }

    @Test
    void testConvertFromBaseUnit_LitreToGallon() {
        assertEquals(
                1.0,
                VolumeUnit.GALLON.convertFromBaseUnit(3.78541),
                EPSILON
        );
    }

    /* =========================
       HASHCODE + IMMUTABILITY
       ========================= */

    @Test
    void testHashCodeConsistency() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    void testImmutability() {
        Quantity<VolumeUnit> original =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> converted =
                original.convertTo(VolumeUnit.MILLILITRE);

        assertNotSame(original, converted);
        assertEquals(1.0, original.getValue(), EPSILON);
    }
}