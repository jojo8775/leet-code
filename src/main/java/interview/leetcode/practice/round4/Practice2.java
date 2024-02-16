package interview.leetcode.practice.round4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class Practice2 {
	public List<String> findItinerary(List<List<String>> tickets) {

        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for(List<String> entry : tickets){
            graph.computeIfAbsent(entry.get(0), v -> new PriorityQueue<String>((a,b) -> a.compareTo(b))).offer(entry.get(1));
        }

        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        List<String> result = new LinkedList<>();

        while(!stack.isEmpty()){
            while(graph.get(stack.peek()) != null && !graph.get(stack.peek()).isEmpty()){
                stack.push(graph.get(stack.peek()).poll());
            }
            
            result.add(0, stack.pop());
        }

        return result;
    }
}
