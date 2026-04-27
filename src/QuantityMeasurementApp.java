public class QuantityMeasurementApp {

    // ------------------- ENUM -------------------
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0), // 1 yard = 3 feet
        CENTIMETER(0.393701 / 12.0); // convert cm → inch → feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
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
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            // safer comparison with tolerance
            return Math.abs(this.toFeet() - other.toFeet()) < 0.0001;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }

    // ------------------- MAIN -------------------
    public static void main(String[] args) {

        System.out.println(
                new Quantity(1, LengthUnit.YARD)
                        .equals(new Quantity(3, LengthUnit.FEET)) // true
        );

        System.out.println(
                new Quantity(1, LengthUnit.YARD)
                        .equals(new Quantity(36, LengthUnit.INCH)) // true
        );

        System.out.println(
                new Quantity(1, LengthUnit.CENTIMETER)
                        .equals(new Quantity(0.393701, LengthUnit.INCH)) // true
        );

        System.out.println(
                new Quantity(2, LengthUnit.YARD)
                        .equals(new Quantity(6, LengthUnit.FEET)) // true
        );
    }
}