// QuantityMeasurementApp.java
// UC11 Main Application for Generic Quantity Measurement System
// Supports Length + Weight + Volume using IMeasurable + Generic Quantity<U>

public class QuantityMeasurementApp {

    /* =========================
       GENERIC DEMONSTRATION METHODS
       ========================= */

    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2
    ) {
        System.out.println("Comparing: " + q1 + " and " + q2);
        System.out.println("Equal? " + q1.equals(q2));
        System.out.println("-----------------------------------");
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit
    ) {
        System.out.println("Converting: " + quantity);
        System.out.println("Converted to " + targetUnit.getUnitName()
                + ": " + quantity.convertTo(targetUnit));
        System.out.println("-----------------------------------");
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2
    ) {
        System.out.println("Adding: " + q1 + " + " + q2);
        System.out.println("Result: " + q1.add(q2));
        System.out.println("-----------------------------------");
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit
    ) {
        System.out.println("Adding: " + q1 + " + " + q2);
        System.out.println("Result in " + targetUnit.getUnitName()
                + ": " + q1.add(q2, targetUnit));
        System.out.println("-----------------------------------");
    }

    /* =========================
       MAIN METHOD
       ========================= */

    public static void main(String[] args) {

        /* ================= LENGTH ================= */

        System.out.println("========== LENGTH OPERATIONS ==========");

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> length3 =
                new Quantity<>(1.0, LengthUnit.YARDS);

        demonstrateEquality(length1, length2);
        demonstrateConversion(length1, LengthUnit.INCHES);
        demonstrateAddition(length1, length2);
        demonstrateAddition(length1, length3, LengthUnit.FEET);

        /* ================= WEIGHT ================= */

        System.out.println("\n========== WEIGHT OPERATIONS ==========");

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> weight3 =
                new Quantity<>(2.0, WeightUnit.KILOGRAM);

        demonstrateEquality(weight1, weight2);
        demonstrateConversion(weight1, WeightUnit.GRAM);
        demonstrateAddition(weight1, weight2);
        demonstrateAddition(weight1, weight3, WeightUnit.GRAM);

        /* ================= VOLUME ================= */

        System.out.println("\n========== VOLUME OPERATIONS ==========");

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> volume3 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        demonstrateEquality(volume1, volume2);
        demonstrateEquality(
                volume3,
                new Quantity<>(3.78541, VolumeUnit.LITRE)
        );

        demonstrateConversion(volume1, VolumeUnit.MILLILITRE);
        demonstrateConversion(volume3, VolumeUnit.LITRE);
        demonstrateConversion(volume2, VolumeUnit.GALLON);

        demonstrateAddition(volume1, volume2);
        demonstrateAddition(volume1, volume3, VolumeUnit.MILLILITRE);
        demonstrateAddition(volume2, volume3, VolumeUnit.GALLON);

        /* ================= CROSS CATEGORY ================= */

        System.out.println("\n========== CROSS CATEGORY CHECK ==========");

        System.out.println(
                "Length vs Weight Equality: " +
                        length1.equals(weight1)
        );

        System.out.println(
                "Length vs Volume Equality: " +
                        length1.equals(volume1)
        );

        System.out.println(
                "Weight vs Volume Equality: " +
                        weight1.equals(volume1)
        );

        System.out.println("-----------------------------------");

        /* ================= SUMMARY ================= */

        System.out.println("\n========== SYSTEM SUMMARY ==========");
        System.out.println("UC1–UC8: Length supported");
        System.out.println("UC9: Weight supported");
        System.out.println("UC10: Generic architecture");
        System.out.println("UC11: Volume supported");
        System.out.println("All categories operate using ONE reusable Quantity<U> class.");
    }
}