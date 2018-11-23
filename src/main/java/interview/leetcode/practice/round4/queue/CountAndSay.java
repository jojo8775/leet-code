package interview.leetcode.practice.round4.queue;

import java.util.LinkedList;
import java.util.Queue;

public class CountAndSay {
	public String countAndSay(int n) {
        if(n == 0){
            return "";
        }
        
        Queue<Integer> queue1 = new LinkedList<Integer>();
        queue1.offer(1);
        
        while(--n > 0){
            Queue<Integer> queue2 = new LinkedList<Integer>();
            int count = 0, cur = queue1.peek();
            
            while(!queue1.isEmpty()){
                if(cur != queue1.peek()){
                    queue2.offer(count);
                    queue2.offer(cur);
                    
                    count = 0;
                }
                
                cur = queue1.poll();
                count++;
            }
            
            queue2.offer(count);
            queue2.offer(cur);
            
            queue1 = queue2;
        }
        
        StringBuilder sb = new StringBuilder();
        while(!queue1.isEmpty()){
            sb.append(queue1.poll());
        }
        
        return sb.toString();
    }
	
	public static void main(String[] args) {
		String result = new CountAndSay().countAndSay(5);
		System.out.println(result);
	}
}
