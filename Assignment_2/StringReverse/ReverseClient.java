import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.*;

public class ReverseClient {
  public static void main(String[] args){
    try{
      //Init
      ORB orb = ORB.init(args, null);
      Object ref = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(ref);
      
      String name = "Reverse";
      ReverseModule.Reverse service = ReverseModule.ReverseHelper.narrow(ncRef.resolve_str(name));
      
      String str = "ABCD";
      String op = service.reverse(str);
      
      System.out.println(op);
    
    } catch (Exception e) {}
  }
}
