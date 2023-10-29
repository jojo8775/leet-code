package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import javafx.util.Pair;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

 

Example 1:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
Example 2:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
Example 3:



Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.
 

Constraints:

2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.
Accepted
122,513
Submissions
224,334
 * @author jojo
 * Oct 22, 2023 11:59:46 AM
 */
public class PathWithMaxProbability {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();

        for(int i=0; i<edges.length; i++){
            int[] e = edges[i];
            graph.computeIfAbsent(e[0], v -> new ArrayList<>()).add(new Pair(e[1], succProb[i]));
            graph.computeIfAbsent(e[1], v -> new ArrayList<>()).add(new Pair(e[0], succProb[i]));
        }

        PriorityQueue<Pair<Double, Integer>> pq = new PriorityQueue<>((a,b) -> Double.compare(b.getKey(), a.getKey()));
        pq.offer(new Pair(1.0, start_node));

        // since we are asked to track the max prob. We need to track this value for each node as we move 
        // from start to end.
        double[] maxProb = new double[n];
        maxProb[start_node] = 1.0;

        while(!pq.isEmpty()){
            Pair<Double, Integer> top = pq.poll();

            double curPriority = top.getKey();
            int curNode = top.getValue();

            if(curNode == end_node){
                return curPriority;
            }

            for(Pair<Integer, Double> nei : graph.getOrDefault(curNode, new ArrayList<Pair<Integer, Double>>())){
                int nextNode = nei.getKey();
                double nextPriority = nei.getValue();

                if(curPriority * nextPriority > maxProb[nextNode]){
                    maxProb[nextNode] = curPriority * nextPriority;
                    pq.offer(new Pair<Double, Integer>(maxProb[nextNode], nextNode));
                }
            }
        }

        return 0.0;
    }
}
