import java.util.*;

public class TokenRing {
  public static void main(String[] args){
    List<Integer> processes = new ArrayList<>();
    
    System.out.println("Enter the number of processes");
    Scanner sc = new Scanner(System.in);
    
    int numberOfProcesses = sc.nextInt();
    for(int i = 1; i <= numberOfProcesses; i++){
      processes.add(i);
    }
    
    System.out.println("Processes are : " + processes);
    
    int count = 0;
    while(true){
      if(count != 0){
        System.out.println("Do you want to continue? Enter 1 for YES, 0 for NO");
        int input = sc.nextInt();
        if(input == 0) break;
      }
    
      System.out.println("Enter the number of sender process : ");
      int sender = sc.nextInt();
      sc.nextLine();
      System.out.println("Enter the message to be sent : ");
      String message = sc.nextLine();
      System.out.println("Enter the number of receiver process : ");
      int receiver = sc.nextInt();
  
      simulateTokenRing(sender, receiver, message, processes);
      count++;
      
    }
  }
  
  public static void simulateTokenRing(int sender, int receiver, String message, List<Integer> processes){
    int numberOfProcesses = processes.size();
    boolean round = false;
    for(int current = 0; current < processes.size(); current = (current+1) % numberOfProcesses){
      int currentProcess = processes.get(current);
      if(sender > receiver && currentProcess >= sender) round = true;
      if(currentProcess == receiver){
        if(sender > receiver){
          if(round){
            System.out.println("Message delivered to the receiver process : " + currentProcess + "\nMessage : "+ message);
            receiver = -1;
            message = "Acknowledgment";
          }
        } else {
          System.out.println("Message delivered to the receiver process : " + currentProcess + "\nMessage : "+ message);
          receiver = -1;
          message = "Acknowledgment";
        }
        
      }
      else if(currentProcess == sender && receiver == -1){
        System.out.println(message + " delivered to the sender process : " + currentProcess);
        break;
      }
      else {
        System.out.println("Message forwarded by process " + currentProcess);
      }
    }
  }
}
