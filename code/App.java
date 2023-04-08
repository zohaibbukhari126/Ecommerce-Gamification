package code;
public class App {
    private int numerator;




    
    public void setNumerator(int numerator) {
        this.numerator = numerator;    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            System.out.println("Denominator cannot be zero. Setting it to 1.");
            this.denominator = 1;
        } else {
            this.denominator = denominator;
        }
    }

    public void addNumber(int number) {
        this.numerator += number * this.denominator;
    }

    public void simplifyFraction() {
        int gcd = gcd(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public void printFraction() {
        System.out.println(this.numerator + "/" + this.denominator);
    }
}
