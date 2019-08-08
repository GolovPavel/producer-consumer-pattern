import java.util.List;

public class Consumer implements Runnable {

    private final List<Integer> queue;

    public Consumer(List<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            while (queue.size() == 0) {
                synchronized (queue) {
                    try {
                        System.out.println("Consumer: queue is empty. Waiting...");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            consume();
        }
    }

    private void consume() {
        synchronized (queue) {
            System.out.printf("Consumer: retrieved %d value\n", queue.remove(0));
            queue.notifyAll();
        }
    }
}
