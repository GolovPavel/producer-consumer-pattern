import java.util.List;
import java.util.stream.IntStream;

public class Producer implements Runnable {

    private final List<Integer> queue;
    private int size;

    public Producer(List<Integer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        IntStream.range(0, 8).forEach(i -> {
            while (queue.size() == size) {
                synchronized (queue) {
                    try {
                        System.out.println("Producer: queue is full. Waiting...");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            produce(i);
        });
    }

    private void produce(int i) {
        synchronized (queue) {
            queue.add(i);
            queue.notifyAll();
            System.out.printf("Producer: %d added to queue\n", i);
        }
    }
}
