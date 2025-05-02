package Assignment_6;

import java.util.*;

public class RingAlgorithm {

    public static void election(int nodes, int initiator, int[] isActive) {
        int[] arr = new int[nodes];
        int count = 1;
        arr[0] = initiator + 1;
        int current = (initiator + 1) % nodes;

        do {
            if (isActive[current] != 0) {
                arr[count] = current + 1;
                System.out.println("Process " + (initiator + 1) + " sends the Election message to process " + (current + 1));
                count++;
                printArr(arr, count);
            }
            current = (current + 1) % nodes;
        } while (current != initiator);

        int Max = newLeader(arr,count);
        System.out.println("Process " + Max + " is the new elected leader!!!");
    }


    public static void printArr(int[] arr , int count){
        System.out.print("[ ");
        for(int i = 0 ; i < count ; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    public static int newLeader(int[] arr, int count){
        int Max = Integer.MIN_VALUE;
        for(int i = 0 ; i < count ; i++){
            Max = Math.max(Max , arr[i]);
        }
        return Max;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes :");
        int nodes = sc.nextInt();
        int[] isActive = new int[nodes];
        int failed_leader = nodes;

        for(int i = 0 ; i < nodes ; i++){
            isActive[i] = 1;
        }

        isActive[nodes - 1] = 0;  // simulate last process as failed
        System.out.println("Enter the initiator (1 to " + nodes + "): ");
        int initiator = sc.nextInt() - 1;

        System.out.println("Process " + failed_leader + " failed as a leader");
        election(nodes , initiator , isActive);
    }
}
