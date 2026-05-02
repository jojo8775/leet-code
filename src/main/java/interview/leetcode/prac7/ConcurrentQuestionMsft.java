package interview.leetcode.prac7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentQuestionMsft {
	 
    public static void main(String[] args) {
        var prob = new ConcurrentQuestionMsft();
 
        try {
            prob.process();
        } catch (Exception ex) {
            System.out.println("exception: " + ex.toString());
        }
    }
 
    private final Queue<Task> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private volatile boolean shutdown = false;
    private static final int NUM_WORKERS = 15;
    private final List<Thread> workers = new ArrayList<>();
 
    public void process() throws InterruptedException {
 
        System.out.println("Starting all the workers.");
        spinupWorkers();
 
        Thread submitter1 = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                submit("A-task", i);
            }
        });
        Thread submitter2 = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                submit("B-task", i);
            }
        });
        Thread submitter3 = new Thread(() -> {
            for (int i = 1; i <= 20; i++) {
                submit("C-task", i);
            }
        });
 
        System.out.println("Starting all the submit jobs");
        submitter1.start();
        submitter2.start();
        submitter3.start();
 
        submitter1.join();
        submitter2.join();
        submitter3.join();
 
        System.out.println("Sleeping till the simulation is complete");
        Thread.sleep(10000);
 
        System.out.println("Invoking shutdown");
        shutdown();
        
        System.out.println("Waiting on drain");
        awaitTermination();
        
        System.out.println("Program end");
    }
 
    public void spinupWorkers() {
        for (int i = 0; i < NUM_WORKERS; i++) {
            final int workerId = i;
            Thread worker = new Thread(() -> runWorker(workerId), "worker-" + i);
            //worker.setDaemon(true);
            worker.start();
            workers.add(worker);
        }
    }
    
    public void awaitTermination() throws InterruptedException {
        for (Thread w : workers) {
            w.join();
        }
    }
 
    private void runWorker(int workerId) {
        while (true) {
            Task task;
 
            // ---- Critical section: only touch the queue under the lock ----
            lock.lock();
            try {
                while (queue.isEmpty() && !shutdown) {
                    try {
                        notEmpty.await(); // idle, no CPU usage
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
 
                // Drain remaining tasks even after shutdown is signaled.
                if (queue.isEmpty() && shutdown) {
                    return;
                }
 
                task = queue.poll();
            } finally {
                lock.unlock();
            }
            // ---- End critical section ----
 
            // Execute OUTSIDE the lock so workers actually run in parallel.
            System.out.println("Worker " + workerId + " processing task "
                    + task.prefix + task.taskId);
            try {
                task.execute();
            } catch (Exception ex) {
                // do nothing
            }
        }
    }
 
    public void submit(String prefix, int id) {
        lock.lock();
        try {
            queue.offer(new Task(prefix, id));
            notEmpty.signal(); // wake one idling worker
        } finally {
            lock.unlock();
        }
    }
 
    public void shutdown() {
        lock.lock();
        try {
            shutdown = true;
            notEmpty.signalAll(); // wake all workers so they can exit
        } finally {
            lock.unlock();
        }
    }
 
    private static class Task {
        int taskId;
        String prefix;
 
        Task(String prefix, int taskId) {
            this.taskId = taskId;
            this.prefix = prefix + "-";
        }
 
        public void execute() throws InterruptedException {
            System.out.println("task id: " + prefix + taskId + " : Processing Task");
            System.out.println("task id: " + prefix + taskId + " : Sleeping for : "
                    + (taskId % 5) + " seconds");
            Thread.sleep((taskId % 5) * 1000L); // actually sleep in seconds
            System.out.println("task id: " + prefix + taskId + " : Woke up --> end.");
        }
    }
}