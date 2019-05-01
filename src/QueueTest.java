import java.util.*;

public class QueueTest {

    static Queue<Integer> q = new LinkedList<>();
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        for(int i = 0; i < 2345610; i++){

            Random rand = new Random();

            int n = rand.nextInt(100000000) + 1;
            q.add(n);
            list.add(n);
        }

        long startTime = System.nanoTime();
        while(list.size() != 0){
            list.remove(0);
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " with list");

        startTime = System.nanoTime();
        while(q.size() != 0){
            q.poll();
        }
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime +" with queue");


    }


}
