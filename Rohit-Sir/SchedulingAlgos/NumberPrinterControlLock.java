import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NumberPrinterControlLock {
    private static volatile boolean running = false;
    private static volatile boolean stop = false;

    private static final Lock lock = new ReentrantLock();
    private static final Condition pausedCondition = lock.newCondition();
    private static volatile boolean paused = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Thread printerThread = new Thread(() -> {
            int num = 1;
            while (!stop) {
                lock.lock();
                try {
                    while (paused) {
                        pausedCondition.await();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }

                if (running && !stop) {
                    System.out.println(num++);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        printerThread.start();

        System.out.println("Commands: start | pause | resume | stop");
        while (true) {
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "start":
                    if (!running) {
                        running = true;
                        paused = false;
                        System.out.println("Started printing numbers...");
                    } else {
                        System.out.println("Already started.");
                    }
		    break;

                case "pause":
                    if (running && !paused) {
                        lock.lock();
                        try {
                            paused = true;
                        } finally {
                            lock.unlock();
                        }
                        System.out.println("Paused.");
                    } else {
                        System.out.println("Not running or already paused.");
                    }
                    break;

                case "resume":
                    if (running && paused) {
                        lock.lock();
                        try {
                            paused = false;
                            pausedCondition.signal(); 
  			} finally {
                            lock.unlock();
                        }
                        System.out.println("Resumed printing...");
                    } else {
                        System.out.println("Not paused.");
                    }
                    break;

                case "stop":
                    stop = true;
                    lock.lock();
                    try {
                        pausedCondition.signalAll();
                    } finally {
                        lock.unlock();
                    }
                    System.out.println("Stopping program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
