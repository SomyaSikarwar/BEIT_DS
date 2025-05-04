public class CalculatorImplementation extends CalculatorModule.CalculatorPOA {

    public CalculatorImplementation() {
        super();
        System.out.println("CalculatorImplementation object created.");
    }

    @Override
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    @Override
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    @Override
    public double divide(double num1, double num2) {
        return num1 / num2;
    }
}
