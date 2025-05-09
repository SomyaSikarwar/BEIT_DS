import java.util.*;

public class BullyAlgorithm {
  public static void main(String[] args) {
    int initiator = -1, leader = -1;
    boolean leaderAlive = false;
    List<Integer> processes = new ArrayList<>();
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number of processes : ");
    int numberOfProcesses = sc.nextInt();
    
    for(int i = 1; i < numberOfProcesses + 1; i++) processes.add(i);
    
    System.out.println("The processes are : "+ processes);
    
    System.out.println("Enter the number of leader process : ");
    leader = sc.nextInt();
    
    System.out.println("Enter the number of communicating process : ");
    initiator = sc.nextInt();
    
    System.out.println("Enter 1 for Bully, 2 for Ring : ");
    int input = sc.nextInt();
    
    int current = initiator, newLeader = 0;
    List<Integer> activeProcesses = new ArrayList<>();
    
    while(!leaderAlive){
      if(current == leader) current = current % numberOfProcesses + 1;
      if(input == 1) newLeader = electBullyLeader(current, leader, processes);
      else newLeader = electRingLeader(current, initiator, leader, activeProcesses, numberOfProcesses);
      if(newLeader != 0) {
        System.out.println("New Leader is : "+ newLeader);
        leader = newLeader;
        leaderAlive = true;
      }
      current = current % numberOfProcesses + 1;
    }
    
  }
  
  public static int electBullyLeader(int initiator, int leader, List<Integer> processes){
    System.out.println("==========================================");
    System.out.println("Election has started by process "+ initiator);
    int count = 0;
    for(int i = initiator; i < processes.size(); i++){
      int currProcess = processes.get(i);
      System.out.println("Election signal sent to process : "+ currProcess);
      if(currProcess == leader) System.out.println("Process "+ currProcess + " is down.");
      else System.out.println("Process "+ currProcess + " responded with Active.");
      count++;
    }
    System.out.println("==========================================");
    
    if(count == 1 || count == 0) {
      if(initiator >=  processes.size()) initiator--; 
      if(processes.get(initiator) == leader) return processes.get(initiator - 1);
      return processes.get(initiator);
    } else return 0;
  }
  
  public static int electRingLeader(int current, int initiator, int leader, List<Integer> activeProcesses, int numberOfProcesses){
    if(initiator == current && !activeProcesses.isEmpty()){
      return Collections.max(activeProcesses);
    }
    
    if(current != leader) activeProcesses.add(current);
    System.out.println("Adding process : " + current + " to active Processes");
    System.out.println(activeProcesses);
    
    return 0;
  }
}
