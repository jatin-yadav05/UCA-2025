import java.util.*;
public class ShortestJobFirst implements Scheduler {
    Map<Task, Thread> taskThreadMap;
    public ShortestJobFirst(){
        this.taskThreadMap = new HashMap<>();
    }
    public void execute(List<Task> taskList) throws InterruptedException{
        PriorityQueue<Task> queue = new PriorityQueue<>((a,b)-> a.getBurstTime()-b.getBurstTime());
        for (Task t : taskList) {
            Thread thread = new Thread(t);
            thread.start();
            taskThreadMap.put(t, thread);
        }
        int currentTime = 0;
        int completed = 0;
        int idx = 0;
        
        while(completed<taskList.size()){
            while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                queue.offer(taskList.get(idx));
                idx++;
            }
            if (queue.isEmpty()) {
                currentTime++;
                Thread.sleep(100);
                continue;
            }
            Task currentTask = queue.poll();
            while (!currentTask.isCompleted()) {
                currentTask.resume(); // Execute one unit
                Thread.sleep(100);
                currentTime++;

                while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                    queue.offer(taskList.get(idx));
                    idx++;
                }
            }
            if (currentTask.isCompleted()) {
                currentTask.calculateTimes(currentTime);
                completed++;
                System.out.printf("Task %d is completed at this time %d", currentTask.getTaskId(), currentTime);
            }
        }

        for (Thread t : taskThreadMap.values()) {
            t.join();
        }
        printStatistic(taskList);
    }
    private void printStatistic(List<Task> taskList){
        System.out.println("\nTask | Completion | Turnaround | Waiting");
        for (Task t : taskList) {
            System.out.printf(" %2d  |     %2d     |     %2d     |    %2d\n",
                    t.getTaskId(), t.getCompletionTime(),
                    t.getTurnaroundTime(), t.getWaitingTime());
        }
    }
}

