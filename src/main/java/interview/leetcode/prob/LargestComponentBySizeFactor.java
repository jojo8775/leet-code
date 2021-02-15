package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of unique positive integers A, consider the following graph:

There are A.length nodes, labelled A[0] to A[A.length - 1];
There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.

 

Example 1:

Input: [4,6,15,35]
Output: 4

Example 2:

Input: [20,50,9,63]
Output: 2

Example 3:

Input: [2,3,6,7,4,12,21,39]
Output: 8

Note:

1 <= A.length <= 20000
1 <= A[i] <= 100000
Accepted
25,297
Submissions
70,065
 * @author jojo
 * Feb 10, 2021  10:41:24 PM
 */
public class LargestComponentBySizeFactor {
    public int largestComponentSize(int[] A) {
        int len = A.length;
        
        UN un = new UN(len);
        
        // k : factor, v : index
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<len; i++){
            int val = A[i];
            
            for(int j=2; j*j <= val; j++){
                if(val % j == 0){
                    if(!map.containsKey(j)){
                        map.put(j, i);
                    }
                    else {
                        un.union(i, map.get(j));
                    }
                    
                    if(!map.containsKey(val/j)){
                        map.put(val/j, i);
                    }
                    else{
                        un.union(i, map.get(val/j));
                    }
                }
            }
            
            // val can be a factor too. e.g. if we have 2, 3, 4 and entries 2 itself is a factor for 1st and third index
            if(!map.containsKey(val)){
                map.put(val, i);
            }
            else{
                un.union(i, map.get(val));
            }
        }
        
        return un.getMax();
    }
    
    private static class UN{
        private int[] parent, size;
        private int len, maxLen = 0;
        public UN(int len){
            this.len = len;
            
            parent = new int[len];
            for(int i=0; i<len; i++){
                parent[i] = i;
            }
            
            size = new int[len];
            Arrays.fill(size, 1);
        }
        
        public void union(int idx1, int idx2){
            int p1 = findParent(idx1);
            int p2 = findParent(idx2);
            
            if(p1 != p2){
                parent[p1] = p2;
                size[p2] += size[p1];
                
                maxLen = Math.max(maxLen, size[p2]);
            }
        }
        
        private int findParent(int idx){
            while(idx != parent[idx]){
                parent[idx] = parent[parent[idx]];
                idx = parent[idx];
            }
            
            return idx;
        }
        
        public int getMax(){
            return maxLen;
        }
    }
}
