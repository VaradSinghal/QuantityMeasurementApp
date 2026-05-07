// QuantityMeasurementApp.java
// UC13 Main File – Demonstrates centralized arithmetic logic (ADD, SUBTRACT, DIVIDE)

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("===== UC13: Centralized Arithmetic Logic Demo =====\n");

        demonstrateLengthOperations();
        demonstrateWeightOperations();
        demonstrateVolumeOperations();

        System.out.println("\n===== UC13 Demo Completed Successfully =====");
    }

    private static void demonstrateLengthOperations() {
        System.out.println("---- LENGTH OPERATIONS ----");

        Quantity<LengthUnit> feet10 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches6 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);

        // Addition
        System.out.println("Addition (Implicit): " +
                feet10.add(inches6));

        System.out.println("Addition (Explicit Inches): " +
                feet10.add(inches6, LengthUnit.INCHES));

        // Subtraction
        System.out.println("Subtraction (Implicit): " +
                feet10.subtract(inches6));

        System.out.println("Subtraction (Explicit Inches): " +
                feet10.subtract(inches6, LengthUnit.INCHES));

        // Division
        System.out.println("Division: " +
                feet10.divide(feet2));

        System.out.println();
    }

    private static void demonstrateWeightOperations() {
        System.out.println("---- WEIGHT OPERATIONS ----");

        Quantity<WeightUnit> kg10 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gram5000 = new Quantity<>(5000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> kg5 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        // Addition
        System.out.println("Addition (Implicit): " +
                kg10.add(gram5000));

        System.out.println("Addition (Explicit Gram): " +
                kg10.add(gram5000, WeightUnit.GRAM));

        // Subtraction
        System.out.println("Subtraction (Implicit): " +
                kg10.subtract(gram5000));

        System.out.println("Subtraction (Explicit Gram): " +
                kg10.subtract(gram5000, WeightUnit.GRAM));

        // Division
        System.out.println("Division: " +
                kg10.divide(kg5));

        System.out.println();
    }

    private static void demonstrateVolumeOperations() {
        System.out.println("---- VOLUME OPERATIONS ----");

        Quantity<VolumeUnit> litre5 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml500 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        // Addition
        System.out.println("Addition (Implicit): " +
                litre5.add(ml500));

        System.out.println("Addition (Explicit Millilitre): " +
                litre5.add(ml500, VolumeUnit.MILLILITRE));

        // Subtraction
        System.out.println("Subtraction (Implicit): " +
                litre5.subtract(ml500));

        System.out.println("Subtraction (Explicit Millilitre): " +
                litre5.subtract(litre2, VolumeUnit.MILLILITRE));

        // Division
        System.out.println("Division: " +
                litre5.divide(litre2));

        System.out.println();
    }
}