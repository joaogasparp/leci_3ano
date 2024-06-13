public class Scalar extends Value{
    private double value;

    public Scalar(double value) {
        this.value = value;
        setScalar(true);
    }

    public void setScalar(double value) {
        this.value = value;
    }

    public double getScalar() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public Value symmetric() {
        return new Scalar(-value);
    }

    @Override
    public Value sum(Value v) {
        return new Scalar(this.value + ((Scalar) v).getScalar());
    }

    @Override
    public Value subtract(Value v) {
        return new Scalar(this.value - ((Scalar) v).getScalar());
    }
}
