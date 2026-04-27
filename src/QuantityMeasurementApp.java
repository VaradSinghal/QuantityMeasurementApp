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
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ------------------- UC5: CONVERT -------------------
        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double feet = this.toFeet();
            return new Quantity(targetUnit.fromFeet(feet), targetUnit);
        }

        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (source == null || target == null) {
                throw new IllegalArgumentException("Units cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }

            double feet = source.toFeet(value);
            return target.fromFeet(feet);
        }

        // ------------------- UC6: ADD -------------------

        // Instance method (result in this.unit)
        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            double sumFeet = this.toFeet() + other.toFeet();
            double resultValue = this.unit.fromFeet(sumFeet);

            return new Quantity(resultValue, this.unit);
        }

        // Static method with target unit
        public static Quantity add(Quantity q1, Quantity q2, LengthUnit targetUnit) {
            if (q1 == null || q2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumFeet = q1.toFeet() + q2.toFeet();
            double result = targetUnit.fromFeet(sumFeet);

            return new Quantity(result, targetUnit);
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

        System.out.println("Add (instance): " + q1.add(q2)); // 2 FEET
        System.out.println("Add (static): " +
                Quantity.add(q1, q2, LengthUnit.INCH)); // 24 INCH
    }
}