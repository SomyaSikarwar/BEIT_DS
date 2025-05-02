import java.util.*;

public class TokenRing {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.println("The processes are:");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }

        int choice = 1;
        int token = 1;
        sc.nextLine(); // Consume leftover newline

        while(choice == 1) {
            System.out.println("\nEnter the message: ");
            String message = sc.nextLine();

            System.out.println("Enter the sender: ");
            int sender = sc.nextInt();

            System.out.println("Enter the receiver: ");
            int receiver = sc.nextInt();

            System.out.print("Token Passing: ");
            for (int i = token; i != sender; i = (i % n) + 1) {
                System.out.print(i + " -> ");
            }
            System.out.println(sender);

            System.out.println("Sender " + sender + " sending message: " + message);

            for (int i = sender + 1; i != receiver; i = (i % n) + 1) {
                System.out.println("Message forwarded by process " + i);
            }

            System.out.println("Receiver " + receiver + " received the message: " + message);
            System.out.println("Acknowledgement sent back to sender: " + sender);

            token = sender;

            System.out.println("Press 1 to continue or 0 to exit: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline after nextInt()
        }

        System.out.println("Program Ended.");
    }
}

