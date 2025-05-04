import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Calculator";

            CalculatorModule.Calculator calculator = CalculatorModule.CalculatorHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Enter two numbers : ");

            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println("Addition : " + calculator.add(a,b));
            System.out.println("Subtraction : " + calculator.subtract(a,b));
            System.out.println("Multiplication : " + calculator.multiply(a,b));
            System.out.println("Division : " + calculator.divide(a,b));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
