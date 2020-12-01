import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {

    static AtomicInteger x = new AtomicInteger(0);
    static AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int tempX = x.getAndIncrement();
                if (tempX >= 999999) break;
                if ((tempX % 10) + (tempX / 10) % 10 + (tempX / 100) % 10 == (tempX / 1000)
                        % 10 + (tempX / 10000) % 10 + (tempX / 100000) % 10) {
                    System.out.println(tempX);
                    count.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }

    // При запуске без потоков: count = 55251
    // При запуске с потоками: count = 55251, полагаю, работает правильно
}
