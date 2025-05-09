import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class Server {
  public static void main(String[] args) {
    try{
      //ORB Initialization
      ORB orb = ORB.init(args, null);
      POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootPOA.the_POAManager().activate();
      
      //Step 1 : Creation of service 
      ReverseImplementation service = new ReverseImplementation();
      
      //Step 2 : Creation of Reference to Service
      Object ref = rootPOA.servant_to_reference(service);
      
      //Step 3 : Narrow down the service reference 
      ReverseModule.Reverse href = ReverseModule.ReverseHelper.narrow(ref);
      
      //Step 4 : Creation of naming service reference 
      Object nref = orb.resolve_initial_references("NameService");
      
      //Step 5 : Narrow down the name service
      NamingContextExt ncRef = NamingContextExtHelper.narrow(nref);
      
      //Step 6 : Build path between the service and naming service
      String name = "Reverse";
      NameComponent[] path = ncRef.to_name(name);
      
      
      //Step 7 : Bind the path and service
      ncRef.rebind(path, href);
      
      //Step 8 : Run the server
      System.out.println("Server is up and Running...");
      orb.run();
      
    } catch (Exception e) {}
    
  }
}
