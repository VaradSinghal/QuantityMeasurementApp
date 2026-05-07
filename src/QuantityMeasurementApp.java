import java.util.Objects;

// ===========================
// UC10: Generic Quantity App
// ===========================
public class QuantityMeasurementApp {

    // ---------------------------
    // IMeasurable Interface
    // ---------------------------
    interface IMeasurable {
        double getConversionFactor();
        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
        String getUnitName();
    }

    // ---------------------------
    // LengthUnit Enum
    // Base Unit = FEET
    // ---------------------------
    enum LengthUnit implements IMeasurable {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }

        public double convertToBaseUnit(double value) {
            return value * conversionFactor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / conversionFactor;
        }

        public String getUnitName() {
            return name();
        }
    }

    // ---------------------------
    // WeightUnit Enum
    // Base Unit = KILOGRAM
    // ---------------------------
    enum WeightUnit implements IMeasurable {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double conversionFactor;

        WeightUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }

        public double convertToBaseUnit(double value) {
            return value * conversionFactor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / conversionFactor;
        }

        public String getUnitName() {
            return name();
        }
    }

    // ---------------------------
    // Generic Quantity Class
    // ---------------------------
    static class Quantity<U extends IMeasurable> {
        private final double value;
        private final U unit;
        private static final double EPSILON = 1e-6;

        public Quantity(double value, U unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public U getUnit() {
            return unit;
        }

        // ---------------------------
        // Convert
        // ---------------------------
        public Quantity<U> convertTo(U targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = unit.convertToBaseUnit(value);
            double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

            return new Quantity<>(round(convertedValue), targetUnit);
        }

        // ---------------------------
        // Add (default target = first operand unit)
        // ---------------------------
        public Quantity<U> add(Quantity<U> other) {
            return add(other, this.unit);
        }

        // ---------------------------
        // Add (explicit target unit)
        // ---------------------------
        public Quantity<U> add(Quantity<U> other, U targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Arguments cannot be null");
            }

            if (!this.unit.getClass().equals(other.unit.getClass())) {
                throw new IllegalArgumentException("Measurement categories must match");
            }

            double thisBase = this.unit.convertToBaseUnit(this.value);
            double otherBase = other.unit.convertToBaseUnit(other.value);

            double sumBase = thisBase + otherBase;

            double result = targetUnit.convertFromBaseUnit(sumBase);

            return new Quantity<>(round(result), targetUnit);
        }

        // ---------------------------
        // Equality
        // ---------------------------
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity<?> other = (Quantity<?>) obj;

            if (!this.unit.getClass().equals(other.unit.getClass())) {
                return false;
            }

            double thisBase = this.unit.convertToBaseUnit(this.value);
            double otherBase = other.unit.convertToBaseUnit(other.value);

            return Math.abs(thisBase - otherBase) < EPSILON;
        }

        @Override
        public int hashCode() {
            double baseValue = unit.convertToBaseUnit(value);
            return Objects.hash(round(baseValue), unit.getClass());
        }

        @Override
        public String toString() {
            return "Quantity(" + round(value) + ", " + unit.getUnitName() + ")";
        }

        private static double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }

    // ---------------------------
    // Generic Demonstration Methods
    // ---------------------------
    public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        System.out.println(q1 + " equals " + q2 + " -> " + q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U targetUnit) {
        System.out.println(q + " converts to " + q.convertTo(targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.println(q1 + " + " + q2 + " = " + q1.add(q2, targetUnit));
    }

    // ---------------------------
    // Main Method
    // ---------------------------
    public static void main(String[] args) {

        // Length
        Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);
        demonstrateConversion(length1, LengthUnit.INCHES);
        demonstrateAddition(length1, length2, LengthUnit.YARDS);

        // Weight
        Quantity<WeightUnit> weight1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);
        demonstrateConversion(weight1, WeightUnit.POUND);
        demonstrateAddition(weight1, weight2, WeightUnit.GRAM);
    }
}