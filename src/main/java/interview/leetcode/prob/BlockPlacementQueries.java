package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.

You are given a 2D array queries, which contains two types of queries:

For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin. It is guaranteed that there is no obstacle at distance x when the query is asked.
For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to place a block of size sz anywhere in the range [0, x] on the line, such that the block entirely lies in the range [0, x]. A block cannot be placed if it intersects with any obstacle, but it may touch it. Note that you do not actually place the block. Queries are separate.
Return a boolean array results, where results[i] is true if you can place the block specified in the ith query of type 2, and false otherwise.

 

Example 1:

Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]

Output: [false,true,true]

Explanation:



For query 0, place an obstacle at x = 2. A block of size at most 2 can be placed before x = 3.

Example 2:

Input: queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]

Output: [true,true,false]

Explanation:



Place an obstacle at x = 7 for query 0. A block of size at most 7 can be placed before x = 7.
Place an obstacle at x = 2 for query 2. Now, a block of size at most 5 can be placed before x = 7, and a block of size at most 2 before x = 2.
 

Constraints:

1 <= queries.length <= 15 * 104
2 <= queries[i].length <= 3
1 <= queries[i][0] <= 2
1 <= x, sz <= min(5 * 104, 3 * queries.length)
The input is generated such that for queries of type 1, no obstacle exists at distance x when the query is asked.
The input is generated such that there is at least one query of type 2.
Accepted
5,710
Submissions
38,929
 * 
 * Nov 24, 2024 - 4:34:22 PM
 * Jojo 
 */
public class BlockPlacementQueries {
	
	    /*
	    public List<Boolean> getResults_1(int[][] queries) {
	        // Define the upper bound for the Fenwick Tree based on query constraints
	        int n = Math.min(50000, queries.length * 3);
	        List<Boolean> ans = new ArrayList<>(); // Result list for type 2 queries
	        FenwickTree tree = new FenwickTree(n + 1); // Fenwick Tree to track max gaps
	        TreeSet<Integer> obstacles = new TreeSet<>(Arrays.asList(0, n)); // Sentinel values for boundaries

	        // First pass: process type 1 queries to initialize the obstacles set
	        for (int[] query : queries) {
	            if (query[0] == 1) {
	                int x = query[1];
	                obstacles.add(x); // Add each obstacle to the TreeSet
	            }
	        }

	        // Set up initial maximum gaps in the Fenwick Tree using the obstacles
	        Integer prev = null;
	        for (int x : obstacles) {
	            if (prev != null) {
	                // Calculate the gap between consecutive obstacles
	                tree.maximize(x, x - prev);
	            }
	            prev = x; // Update prev to the current obstacle
	        }

	        // Process the queries in reverse to handle dynamic removal of obstacles
	        for (int i = queries.length - 1; i >= 0; i--) {
	            int type = queries[i][0];
	            int x = queries[i][1];

	            if (type == 1) {
	                // For type 1 queries (obstacle removal)
	                obstacles.remove(x); // Remove the obstacle
	                Integer next = obstacles.higher(x); // Find the next obstacle after x
	                Integer prevObstacle = obstacles.lower(x); // Find the previous obstacle before x

	                // Update the Fenwick Tree with the new gap if both previous and next obstacles exist
	                if (next != null && prevObstacle != null) {
	                    tree.maximize(next, next - prevObstacle);
	                }
	            } else {
	                // For type 2 queries (check if a block of size sz can be placed in [0, x])
	                int sz = queries[i][2];
	                Integer next = obstacles.higher(x); // Find the next obstacle after x
	                Integer prevObstacle = obstacles.lower(x); // Find the previous obstacle before x

	                // Check if there's enough space based on the Fenwick Tree or direct gap
	                if (prevObstacle != null) {
	                    // Get the maximum gap from 0 up to prevObstacle and check if it's >= sz
	                    // OR check if the gap between x and prevObstacle is >= sz
	                    ans.add(tree.get(prevObstacle) >= sz || x - prevObstacle >= sz);
	                } else {
	                    ans.add(false); // If there's no previous obstacle, no valid space is available
	                }
	            }
	        }

	        // Reverse the result list as we processed queries in reverse order
	        Collections.reverse(ans);
	        return ans;
	    }

	    private static class FenwickTree {
	        private int[] vals;

	        // Initialize the Fenwick Tree with an array of size n + 1 (1-based indexing)
	        public FenwickTree(int n) {
	            vals = new int[n + 1];
	        }

	        // Updates the Fenwick Tree at index i with the maximum value of 'val'
	        public void maximize(int i, int val) {
	            // Traverse all indices that are influenced by i in the Fenwick Tree
	            while (i < vals.length) {
	                // Update vals[i] to hold the maximum value up to 'val'
	                vals[i] = Math.max(vals[i], val);
	                // Move to the next index influenced by i using lowbit
	                i += lowbit(i);
	            }
	        }

	        // Returns the maximum value in the range [1, i]
	        public int get(int i) {
	            int res = 0;
	            // Traverse all relevant indices up to i
	            while (i > 0) {
	                // Track the maximum value up to index i
	                res = Math.max(res, vals[i]);
	                // Move to the previous index influenced by i using lowbit
	                i -= lowbit(i);
	            }
	            return res;
	        }

	        // Helper function to calculate the lowest set bit of i
	        private int lowbit(int i) {
	            return i & -i;
	        }
	    }
	    */
	    
	    // refer to this youtube link to understand this FD tree 
	    // https://www.youtube.com/watch?v=uSFzHCZ4E-8&ab_channel=StableSort 
	    public List<Boolean> getResults(int[][] queries) {
	        int len = (int)(1e5 * 5);
	        
	         TreeSet<Integer>  obstacles = new TreeSet<>();
	         obstacles.add(0);
	         obstacles.add(len);
	         
	         for(int i=0; i<queries.length; i++){
	             if(queries[i][0] == 1){
	                obstacles.add(queries[i][1]);
	             }
	         }
	         
	        FredwrickTree fTree = new FredwrickTree(len);
	         
	        Integer prev = null;
	        for(int entry : obstacles){
	            if(prev != null){
	                fTree.setMax(entry, entry - prev);
	            }
	            
	            prev = entry;
	        }
	        
	        List<Boolean> result = new ArrayList<>();
	        
	        for(int i=queries.length - 1; i>=0; i--){
	            int[] q = queries[i];
	            
	            int type = q[0], x = q[1], sz = type == 1 ? 0 : q[2];
	            
	            if(type == 1){
	                Integer prevObstacle = obstacles.lower(x);
	                Integer nextObstacle = obstacles.higher(x);
	                
	                if(prevObstacle != null && nextObstacle != null){
	                    fTree.setMax(nextObstacle, nextObstacle - prevObstacle);
	                }
	                
	                obstacles.remove(x);
	            }
	            else{
	                Integer prevObstacle = obstacles.lower(x);
	                
	                if(prevObstacle != null){
	                    result.add(fTree.getMax(x) >= sz || x - prevObstacle >= sz);
	                }
	                else{
	                    result.add(false);
	                }
	            }
	        }
	        
	        return result.reversed();
	    }
	    
	    private static class FredwrickTree{
	        int[] arr;
	        
	        public FredwrickTree(int n){
	            this.arr = new int[n + 1];
	        }
	        
	        private void setMax(int idx, int val){
	            // popagating all the values 
	            while(idx < arr.length){
	                arr[idx] = Math.max(arr[idx], val);
	                idx += getLowest1Bit(idx);
	            }
	        }
	        
	        private int getMax(int idx){
	            int max = 0;
	            
	            // scanning the entire section of 0 - idx for max section
	            while(idx > 0){
	                max = Math.max(arr[idx], max);
	                
	                idx -= getLowest1Bit(idx);
	            }
	            
	            return max;
	        }
	        
	        private int getLowest1Bit(int n){
	            return n & -n;
	        }
	    }
	    
	   
	    /*
	     int[] bit = new int[50001];

	    public List<Boolean> getResults(int[][] queries) {
	        List<Boolean> res = new LinkedList<>();
	        TreeSet<Integer> blocks = new TreeSet<>();
	        blocks.add(0);      // keep 0 as leftside boundary

	        for (int[] query: queries) {
	            if (query[0] == 1) blocks.add(query[1]);
	        }

	        // calculate each blocks gap: between [block_k, block_k+1]
	        Iterator<Integer> it = blocks.iterator();
	        Integer prev = it.next();
	        while (it.hasNext()) {
	            Integer next = it.next();
	            update(next, next - prev);
	            prev = next;
	        }

	        // iterate from the end to start
	        for (int i = queries.length - 1; i >= 0; i--) {
	            int type = queries[i][0], x = queries[i][1], sz = type == 1? 0: queries[i][2];
	            
	            if (type == 1) {
	                if (blocks.higher(x) != null && blocks.lower(x) != null) {
	                    update(blocks.higher(x), blocks.higher(x) - blocks.lower(x));
	                }
	                blocks.remove(x);
	            } else {
	                res.add(
	                    (blocks.lower(x) != null && x - blocks.lower(x) >= sz)
	                    || getMax(x) >= sz
	                );
	            }
	        }
	        return res.reversed();
	    }

	    private void update(int idx, int val) {
	        for (; idx < 50001; idx = idx | (idx + 1)) {
	            bit[idx] = Math.max(bit[idx], val);
	        }
	    }

	    private int getMax(int r) {
	        //System.out.println("r: " + r);
	        
	        int ret = 0;
	        for (; r >= 0; r = (r & (r + 1)) - 1) {
	            ret = Math.max(ret, bit[r]);
	        }
	        return ret;
	    }
	    */
	
}
