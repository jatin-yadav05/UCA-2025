public class Task implements Runnable {
    private int taskId;
    public int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int priority;
    private int completionTime;
    private int waitingTime;
    private int turnaroundTime;

    private final Object lock = new Object();
    private boolean isPaused = true;

    public Task(int taskId, int arrivalTime, int burstTime, int priority) {
        this.taskId = taskId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }

    public void resume() {
        synchronized (lock) {
            isPaused = false;
            lock.notify();
        }
    }

    public void pause() {
        synchronized (lock) {
            isPaused = true;
        }
    }

    @Override
    public void run() {
        try {
            while (remainingTime > 0) {
                synchronized (lock) {
                    while (isPaused) {
                        lock.wait();
                    }
                }

                remainingTime--;
                System.out.printf("Task %d executing… Remaining=%d\n", taskId, remainingTime);
                // Thread.sleep(100);      // 100 ms = one unit

                pause();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isCompleted() {
        return remainingTime == 0;
    }

    public void calculateTimes(int currentTime) {
        this.completionTime = currentTime;
        this.turnaroundTime = completionTime - arrivalTime;
        this.waitingTime = turnaroundTime - burstTime;
    }

    public int getTaskId() { return taskId; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public int getPriority() { return priority; }
    public int getCompletionTime() { return completionTime; }
    public int getWaitingTime() { return waitingTime; }
    public int getTurnaroundTime() { return turnaroundTime; }

    @Override
    public String toString() {
        return String.format("Task %d: Arrival=%d, Burst=%d, Priority=%d, Completion=%d, Waiting=%d, Turnaround=%d",
                taskId, arrivalTime, burstTime, priority, completionTime, waitingTime, turnaroundTime);
    }
}

