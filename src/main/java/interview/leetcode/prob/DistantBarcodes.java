package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.

 

Example 1:

Input: [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,2,1,2,1]
 

Note:

1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000
 
Accepted
7,163
Submissions
18,123

 * @author jojo
 * Aug 31, 2019 11:43:06 PM
 */
public class DistantBarcodes {
	public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : barcodes){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> {
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            
            return b[1] - a[1];
        });
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        
        int idx = 0;
        while(!queue.isEmpty()){
            int size = 2; // this is two because adjacent barcode cannot be same. 
            List<int[]> list = new ArrayList<>();
            while(size-- > 0 && !queue.isEmpty()){
                int[] top = queue.poll();
                barcodes[idx++] = top[0];
            
                if(--top[1] > 0){
                    list.add(top);
                }
            }
            
            for(int[] e : list){
                queue.offer(e);
            }
        }
        
        return barcodes;
    }
}
