import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class Server {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            CalculatorImplementation calculatorImplementation = new CalculatorImplementation();
            Object ref = rootPOA.servant_to_reference(calculatorImplementation);

            System.out.println("Step 1");
            CalculatorModule.Calculator href = CalculatorModule.CalculatorHelper.narrow(ref);
            System.out.println("Created a reference to the Reverse object.");

            System.out.println("Step 2");
            Object objRef = orb.resolve_initial_references("NameService");
            System.out.println("Obtained a reference to the NameService.");

            System.out.println("Step 3");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            System.out.println("Narrowed the reference to a NamingContextExt.");

            System.out.println("Step 4");
            String name = "Calculator";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, href);
            System.out.println("Calculator object is bound in the NameService with name: " + name);

            System.out.println("Server is ready and waiting for client requests...");
            orb.run();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
