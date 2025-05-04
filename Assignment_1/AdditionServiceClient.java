import java.rmi.Naming;

public class AdditionServiceClient {
    public static void main(String[] args) {
        try{
            String serverURL = "rmi://127.0.0.1/AdditionService";
            AdditionServiceInterface additionService = (AdditionServiceInterface) Naming.lookup(serverURL);

            System.out.println("Enter First number: " + args[0]);
            int a = Integer.parseInt(args[0]);

            System.out.println("Enter Second number: " + args[1]);
            int b = Integer.parseInt(args[1]);

            int result = additionService.add(a, b);
            System.out.println("Result: " + result);

        }

        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
