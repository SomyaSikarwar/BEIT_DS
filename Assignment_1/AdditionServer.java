package Assignment_1;
import java.rmi.*;

public class AdditionServer {
    public static void main(String[] args) throws RemoteException {
        AdditionServiceImplementation additionService = new AdditionServiceImplementation();
        try {
            Naming.rebind("rmi://127.0.0.1/AdditionService", additionService);
            System.out.println("Addition Service is running...");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}