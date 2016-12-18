package interview.leetcode.prob.paid;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
 * @author jojo
 *
 */
public class MovingAveragefromDataStream {
    private final int size;
    private int sum = 0;
    private Queue<Integer> queue = new LinkedList<Integer>();
    
    /** Initialize your data structure here. */
    public MovingAveragefromDataStream(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        if(queue.size() >= size){
            sum -= queue.poll();
        }
        
        queue.offer(val);
        sum += val;
        
        return ((double) sum)/queue.size();
    }
}
