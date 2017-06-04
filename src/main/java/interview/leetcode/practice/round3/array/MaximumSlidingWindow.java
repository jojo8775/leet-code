package interview.leetcode.practice.round3.array;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaximumSlidingWindow {
    public int[] findMax(int[] arr, int k){
        Deque<Integer> dQueue = new LinkedList<Integer>();
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i<arr.length; i++){
            while(!dQueue.isEmpty() && dQueue.peekFirst() < arr[i]){
                dQueue.pollFirst();
            }
            
            dQueue.offerFirst(arr[i]);
            if(i >= k){
                list.add(dQueue.peekLast());
                if(arr[i-k+1] == dQueue.peekLast()){
                    dQueue.pollLast();
                }
            }
        }
        
        int[] result = new int[list.size()];
        for(int i=0; i<result.length; i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
    
    public static void main(String[] args){
        int[] result = new MaximumSlidingWindow().findMax(new int[] {1,3,-1,-3,5,3,6,7}, 3);
        for(int n : result){
            System.out.print(n + ", ");
        }
    }
}
