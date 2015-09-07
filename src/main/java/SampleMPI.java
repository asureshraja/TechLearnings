/**
 * Created by suresh on 7/9/15.
 */
import mpi.*;
public class SampleMPI {
    public static void main(String[] args) throws MPIException {

        MPI.Init(args);
        Comm comm = MPI.COMM_WORLD;
        double data[]= {0.0,1.0,2.0,3.0,4.0};
        int me = comm.getRank();
        if (me == 0) {
            comm.send(data, 5, MPI.DOUBLE, 1, 1);
        } else if (me == 1) {
            double data1[]={10.0,11.0,12.0,13.0,14.0};
            Status status = comm.recv(data1, 5, MPI.DOUBLE, MPI.ANY_SOURCE, 1);
            int count = status.getCount(MPI.DOUBLE);
            int src = status.getSource();


            System.out.println("Received " + count +" values from "+ data1[0]);

        }
        MPI.COMM_WORLD.scatter(data,5,MPI.DOUBLE,);
        MPI.Finalize();

    }
}
