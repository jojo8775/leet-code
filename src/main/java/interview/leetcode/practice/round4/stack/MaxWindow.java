package interview.leetcode.practice.round4.stack;

import java.util.Deque;
import java.util.LinkedList;

public class MaxWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dQueue = new LinkedList<>();
        
        for(int i=0; i<k-1; i++){
            if(dQueue.isEmpty()){
                dQueue.offerFirst(nums[i]);
            }
            else{
                add(dQueue, nums[i]);
            }
        }
        
        int[] result = new int[nums.length - (k - 1)];
        
        for(int i=k-1, j = 0; i<nums.length; i++, j++){
            add(dQueue, nums[i]);
            result[j] = dQueue.peekFirst();
            
            if(nums[j] == dQueue.peekLast()){
                dQueue.pollLast();
            }
        }
        
        return result;
    }
    
    private void add(Deque<Integer> dQueue, int num){
        while(!dQueue.isEmpty() && dQueue.peekLast() < num){
            dQueue.pollLast();
        }
        
        dQueue.offerFirst(num);
    }
    
    public static void main(String[] args){
        int[] result = new MaxWindow().maxSlidingWindow(new int[]{3,3,5,5,6,7}, 3);
        
        for(int i : result){
            System.out.print(i + ",");
        }
    }
}
