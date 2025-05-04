import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class ReverseClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "Reverse";

            ReverseModule.Reverse reverseImplementation = ReverseModule.ReverseHelper.narrow(ncRef.resolve_str(name));
            System.out.println("Enter String : ");

            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            String tempStr = reverseImplementation.reverse(str);

            System.out.println(tempStr);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}