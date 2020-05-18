package interview.leetcode.interview.amazon;

import java.util.HashMap;
import java.util.Map;

public class SubarrayWithKDifferentInteger {
	public int subarraysWithKDistinct(int[] A, int K) {
		// this is the trick 
        return atMostK(A, K) - atMostK(A, K-1);
    }
    
    private int atMostK(int[] A, int K) {
        int res = 0;
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0, j = 0; j < A.length; ++j) {
            if (count.getOrDefault(A[j], 0) == 0){
                K--;
            }
            
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            
            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0){
                    K++;
                }
                
                i++;
            }
            
            
            res += j - i + 1;
            System.out.println("k : " + K + "  res : " + res);
        }
        
        return res;
    }
    
    public static void main(String[] args) {
    	int val = new SubarrayWithKDifferentInteger().subarraysWithKDistinct(new int[] {1,2,3}, 3);
    	System.out.println(val);
    }
}
