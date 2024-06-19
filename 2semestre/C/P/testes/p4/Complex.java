public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        return this.real + " + " + this.imaginary + "i";
    }

    public Complex add(Complex b) {
        return new Complex(this.real + b.real, this.imaginary + b.imaginary);
    }

    public Complex subtract(Complex b) {
        return new Complex(this.real - b.real, this.imaginary - b.imaginary);
    }

    public Complex multiply(Complex b) {
        return new Complex(this.real * b.real - this.imaginary * b.imaginary,
                this.real * b.imaginary + this.imaginary * b.real);
    }

    public Complex divide(Complex b) {
        double temp = b.real * b.real + b.imaginary * b.imaginary;
        return new Complex((this.real * b.real + this.imaginary * b.imaginary) / temp,
                (this.imaginary * b.real - this.real * b.imaginary) / temp);
    }

    public Complex unary() {
        return new Complex(-this.real, -this.imaginary);
    }
}
