import mpi.MPI;

import java.util.Arrays;

public class DistributedArraySum {
    public static void main(String[] args) throws Exception{
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int unitSize = 5;
        int root = 0;
        int[] globalData = new int [unitSize * size];
        int[] localData = new int [unitSize];
        int[] intermediateData = new int [size];

        if(rank == root) {
            int total_elements = unitSize * size;
            System.out.println("Total Number of elements : " + total_elements);
            System.out.println("Adding Elements from 1 to " +  total_elements + " to the globalData array");
            for(int i = 0; i < total_elements; i++) {
                globalData[i] = i + 1;
            }
        }

        //  Scatter data to processes
        MPI.COMM_WORLD.Scatter(globalData, 0, unitSize, MPI.INT,
                localData, 0, unitSize, MPI.INT, root);

        System.out.println("Process " + rank + " received data: " + Arrays.toString(localData));
        for(int i = 1; i < unitSize; i++)
            localData[0] += localData[i];

        System.out.println("Intermediate sum at process " + rank + " is " + localData[0]);
        System.out.println("========================================================");


        MPI.COMM_WORLD.Gather(localData, 0, 1, MPI.INT,
                intermediateData, 0, 1, MPI.INT, root);

        if(rank == root) {
            int total_sum = 0;
            for(int i = 0; i < size; i++) {
                total_sum += intermediateData[i];
            }
            System.out.println("Final sum : " + total_sum);
        }

        MPI.Finalize();
    }

}
