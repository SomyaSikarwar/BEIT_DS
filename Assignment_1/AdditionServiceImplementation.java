import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdditionServiceImplementation extends UnicastRemoteObject implements AdditionServiceInterface{
    public AdditionServiceImplementation() throws RemoteException {}

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
