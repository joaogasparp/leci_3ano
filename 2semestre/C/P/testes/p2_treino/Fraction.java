public class Fraction {
    public int num;
    public int den;

    public Fraction(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        if (den < 0){
            num = -num;
            den = -den;
        }
        this.num = num;
        this.den = den;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDen() {
        return den;
    }

    public void setDen(int den) {
        this.den = den;
    }

    @Override
    public String toString() {
        if (den == 1) {
            return num + "";
        }
        return num + "/" + den;
    }

    public static Fraction sumFractions(Fraction f1, Fraction f2) {
        int num = f1.num * f2.den + f2.num * f1.den;
        int den = f1.den * f2.den;
        return new Fraction(num, den);
    }

    public static Fraction subtractFractions(Fraction f1, Fraction f2) {
        int num = f1.num * f2.den - f2.num * f1.den;
        int den = f1.den * f2.den;
        return new Fraction(num, den);
    }

    public static Fraction multiplyFractions(Fraction f1, Fraction f2) {
        int num = f1.num * f2.num;
        int den = f1.den * f2.den;
        return new Fraction(num, den);
    }

    public static Fraction divideFractions(Fraction f1, Fraction f2) {
        int num = f1.num * f2.den;
        int den = f1.den * f2.num;
        return new Fraction(num, den);
    }
}
