package interview.leetcode.practice.round4.arrays;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k < 2){
            return nums;
        }
        
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0; i<k-1; i++){
            add(deque, nums[i]);
        }
        
        int[] arr = new int[nums.length - k + 1];
        for(int i=0; i<arr.length; i++){
            add(deque, nums[i+k-1]);
            arr[i] = deque.peekFirst();
            if(deque.peekFirst() == nums[i]){
                deque.pollFirst();
            }
        }
        
        return arr;
    }
    
    private void add(Deque<Integer> deque, int num){
        while(!deque.isEmpty()){
            if(deque.peekLast() < num){
                deque.pollLast();
            }
            else{
                break;
            }
        }
        
        deque.offer(num);
    }
    
    public static void main(String[] args){
        int[] result = new SlidingWindowMaximum().maxSlidingWindow(new int[]{1,3,1,2,0,5}, 3);
        for(int n : result){
            System.out.print(n + ", ");
        }
    }
}
