package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

 * @author jojo
 *
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        Node[] nodes = new Node[26];
        
        for(char task : tasks){
            if(nodes[task - 'A'] == null){
                nodes[task - 'A'] = new Node(task);
            }
            else{
                nodes[task - 'A'].count++;
            }
        }
        
        PriorityQueue<Node> queue1 = new PriorityQueue<>((Node n1, Node n2) -> {
            if(n1.count == n2.count){
                return n1.val - n2.val;
            }
            
            return n2.count - n1.count;
        });
        for(int i=0; i<26; i++){
            if(nodes[i] != null){
                queue1.offer(nodes[i]);
            }
        }
        
        int[] timeIndex = new int[26];
        int cpuCycle = 1;
        while(!queue1.isEmpty()){
            List<Node> list = new ArrayList<>();
            int idleCycle = n + 1;
            while(idleCycle > 0 && !queue1.isEmpty()){
               Node top = queue1.poll();
               if(timeIndex[top.val - 'A'] == 0 || cpuCycle - timeIndex[top.val - 'A'] > n){
                   timeIndex[top.val - 'A'] = cpuCycle;
                   cpuCycle++;
                   idleCycle --;
                   top.count--;
                   
                   if(top.count > 0){
                       list.add(top);
                   }
               }
               else{
                   list.add(top);
               } 
            }
            
            for(Node node : list){
                queue1.offer(node);
            }
            
            if(queue1.isEmpty()){
                break;
            }
        
            // this takes account of any necessary idle cycle 
            cpuCycle += idleCycle;
        }
        
        return cpuCycle - 1;
    }
    
    private static class Node{
        int count = 1;
        char val;
        
        public Node(char val){
            this.val = val;
        }
    }
}
