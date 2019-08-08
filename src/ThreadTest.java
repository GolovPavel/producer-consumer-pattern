import java.util.ArrayList;
import java.util.List;

public class ThreadTest {
    public static void main(String[] args) {
        List<Integer> queue = new ArrayList<>();
        int queueSize = 4;

        Thread thread1 = new Thread(new Producer(queue, queueSize));
        Thread thread2 = new Thread(new Consumer(queue));

        thread1.start();
        thread2.start();
    }
}
