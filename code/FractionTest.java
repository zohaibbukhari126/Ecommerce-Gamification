package code;
public class FractionTest {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(2, 3);
        f1.printFraction();
afjklj
        f1.addNumber(1);
        f1.printFraction();

        f1.simplifyFraction();
        f1.printFraction();

        Fraction f2 = new Fraction(6, 8);
        f2.simplifyFraction();
        f2.printFraction();
    }
    
}
