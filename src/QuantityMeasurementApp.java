// QuantityMeasurement.java

import java.util.Objects;

/* ===================== LENGTH UNIT ===================== */
enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor; // convert to feet
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

/* ===================== LENGTH ===================== */
class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid length input");
        }
        this.value = value;
        this.unit = unit;
    }

    public QuantityLength convertTo(LengthUnit target) {
        double base = unit.toBase(value);
        return new QuantityLength(target.fromBase(base), target);
    }

    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {
        if (other == null || target == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double baseSum = this.unit.toBase(this.value) +
                other.unit.toBase(other.value);

        return new QuantityLength(target.fromBase(baseSum), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double v1 = this.unit.toBase(this.value);
        double v2 = other.unit.toBase(other.value);

        return Math.abs(v1 - v2) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.toBase(value));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}

/* ===================== WEIGHT UNIT ===================== */
enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor; // convert to kg
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

/* ===================== WEIGHT ===================== */
class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid weight input");
        }
        this.value = value;
        this.unit = unit;
    }

    public QuantityWeight convertTo(WeightUnit target) {
        double base = unit.toBase(value);
        return new QuantityWeight(target.fromBase(base), target);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        if (other == null || target == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double baseSum = this.unit.toBase(this.value) +
                other.unit.toBase(other.value);

        return new QuantityWeight(target.fromBase(baseSum), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double v1 = this.unit.toBase(this.value);
        double v2 = other.unit.toBase(other.value);

        return Math.abs(v1 - v2) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.toBase(value));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}