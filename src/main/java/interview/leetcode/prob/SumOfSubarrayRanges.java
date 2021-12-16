package interview.leetcode.prob;

import java.math.BigInteger;
import java.util.Stack;

public class SumOfSubarrayRanges {
	public long subArrayRanges(int[] A) {
        int n = A.length, j, k;
        long res = 0;
        
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i <= n; i++) {
            while (!s.isEmpty() && A[s.peek()] > (i == n ? Integer.MIN_VALUE : A[i])) {
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                res -= (long)A[j] * (i - j) * (j - k);

            }
            s.push(i);
        }
        
        long min = findMinSum(A);
        System.out.println(min + "  Lees:" + res);
        
        s.clear();
        for (int i = 0; i <= n; i++) {
            while (!s.isEmpty() && A[s.peek()] < (i == n ? Integer.MAX_VALUE : A[i])) {
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                res += (long)A[j] * (i - j) * (j - k);

            }
            s.push(i);
        }
        
        long max = findMaxSum(A, res);
        System.out.println(max);
        
        return res;
        
//         int len = nums.length;
//         long sum = 0L;
        
//         for(int i=0; i<len; i++){
//             int min = nums[i], max = nums[i];
//             for(int j=i; j<len; j++){
//                 min = Math.min(min, nums[j]);
//                 max = Math.max(max, nums[j]);
                
//                 sum += (max - min);
//             }
//         }
        
//         return sum;
    }
    
    private long findMinSum(int[] arr){
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        int len = arr.length;
        int[] left = new int[len], right = new int[len];
        
        for(int i=0; i<len; i++){
            int count = 1;
            
            while(!s1.isEmpty() && s1.peek()[0] >= arr[i]){
                count += s1.pop()[1];
            }
            
            s1.push(new int[]{arr[i], count});
            left[i] = count;
        }
        
        for(int i=len - 1; i>=0; i--){
            int count = 1;
            
            while(!s2.isEmpty() && s2.peek()[0] > arr[i]){
                count += s2.pop()[1];
            }
            
            s2.push(new int[]{arr[i], count});
            right[i] = count;
        }
        
        BigInteger result = BigInteger.ZERO;
        for(int i=0; i<len; i++){
        	BigInteger bb = BigInteger.valueOf((long)(arr[i] * left[i] * right[i]));
            result.add(bb); 
        }
        
        System.out.println(result.toString());
        return Long.valueOf(result.toString());
    }
    
    private long findMaxSum(int[] arr, long result){
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        int len = arr.length;
        int[] left = new int[len], right = new int[len];
        
        for(int i=0; i<len; i++){
            int count = 1;
            
            while(!s1.isEmpty() && s1.peek()[0] <= arr[i]){
                count += s1.pop()[1];
            }
            
            s1.push(new int[]{arr[i], count});
            left[i] = count;
        }
        
        for(int i=len - 1; i>=0; i--){
            int count = 1;
            
            while(!s2.isEmpty() && s2.peek()[0] < arr[i]){
                count += s2.pop()[1];
            }
            
            s2.push(new int[]{arr[i], count});
            right[i] = count;
        }
        
        for(int i=0; i<len; i++){
            result += (long)(arr[i] * left[i] * right[i]);
            
            // System.out.println("max value: " + result + "  Arr:" + arr[i] + "  left:" + left[i] + "   right:" + right[i]);
        }
        
        return result;
    }
}
