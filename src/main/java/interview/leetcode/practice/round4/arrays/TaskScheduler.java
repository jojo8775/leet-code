package interview.leetcode.practice.round4.arrays;

import java.util.PriorityQueue;

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
        int idleCycle = 0;
        while(!queue1.isEmpty()){
            PriorityQueue<Node> queue2 = new PriorityQueue<>((Node n1, Node n2) -> {
                if(n1.count == n2.count){
                    return n1.val - n2.val;
                }

                return n2.count - n1.count;
            });
            idleCycle = 1;
            while(!queue1.isEmpty()){
               Node top = queue1.poll();
               if(timeIndex[top.val - 'A'] == 0 || cpuCycle - timeIndex[top.val - 'A'] > n){
                   timeIndex[top.val - 'A'] = cpuCycle;
                   System.out.println("Cpu cycle : " + cpuCycle + " Task: " + top.val);
                   cpuCycle++;
                   top.count--;
                   idleCycle = 0;
                   
                   if(top.count > 0){
                       queue2.offer(top);
                   }
                   break;
               }
               else{
                   queue2.offer(top);
               } 
            }
            
            while(!queue2.isEmpty()){
                queue1.offer(queue2.poll());
            }
            
            if(idleCycle == 1){
                System.out.println("Cpu cycle : " + cpuCycle + " Task: idle");
            }
            
            cpuCycle += idleCycle;
        }
        
        return cpuCycle - 1 - idleCycle;
    }
    
    private static class Node{
        int count = 1;
        char val;
        
        public Node(char val){
            this.val = val;
        }
    }
}
