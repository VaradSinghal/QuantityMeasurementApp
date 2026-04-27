public class QuantityMeasurementApp {

    // ------------------- ENUM -------------------
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // ------------------- QUANTITY CLASS -------------------
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ------------------- PRIVATE HELPER (DRY) -------------------
        private static double addInFeet(Quantity q1, Quantity q2) {
            return q1.toFeet() + q2.toFeet();
        }

        // ------------------- UC6: ADD (DEFAULT UNIT) -------------------
        public Quantity add(Quantity other) {
            if (other == null) throw new IllegalArgumentException("Other cannot be null");

            double sumFeet = addInFeet(this, other);
            double result = this.unit.fromFeet(sumFeet);

            return new Quantity(result, this.unit);
        }

        // ------------------- UC7: ADD WITH TARGET UNIT -------------------
        public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = addInFeet(q1, q2);
            double result = targetUnit.fromFeet(sumFeet);

            return new Quantity(result, targetUnit);
        }

        // ------------------- CONVERT -------------------
        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit null");

            double feet = this.toFeet();
            return new Quantity(targetUnit.fromFeet(feet), targetUnit);
        }

        // ------------------- EQUALS -------------------
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;
            return Math.abs(this.toFeet() - other.toFeet()) < 1e-6;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ------------------- MAIN -------------------
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1, LengthUnit.FEET);
        Quantity q2 = new Quantity(12, LengthUnit.INCH);

        System.out.println(Quantity.add(q1, q2, LengthUnit.FEET));   // 2 FEET
        System.out.println(Quantity.add(q1, q2, LengthUnit.INCH));   // 24 INCH
        System.out.println(Quantity.add(q1, q2, LengthUnit.YARD));   // ~0.667 YARD
    }
}