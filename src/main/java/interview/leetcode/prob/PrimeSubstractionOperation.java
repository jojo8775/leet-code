package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a 0-indexed integer array nums of length n.

You can perform the following operation as many times as you want:

Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then subtract p from nums[i].
Return true if you can make nums a strictly increasing array using the above operation and false otherwise.

A strictly increasing array is an array whose each element is strictly greater than its preceding element.

 

Example 1:

Input: nums = [4,9,6,10]
Output: true
Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums becomes [1,9,6,10].
In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
After the second operation, nums is sorted in strictly increasing order, so the answer is true.
Example 2:

Input: nums = [6,8,11,12]
Output: true
Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.
Example 3:

Input: nums = [5,8,3]
Output: false
Explanation: It can be proven that there is no way to perform operations to make nums sorted in strictly increasing order, so the answer is false.
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 1000
nums.length == n
Accepted
37,972
Submissions
78,890
 * 
 * Nov 10, 2024 - 10:11:27 PM
 * Jojo 
 */
public class PrimeSubstractionOperation {
	 public boolean primeSubOperation(int[] nums) {
	        int max = nums[0];
	        
	        for(int i=1; i<nums.length; i++){
	            max = Math.max(max, nums[i]);
	        }
	        
	        List<Integer> primes = getPrimes(max);
	        
	        for(int i=0; i<nums.length; i++){
	            int diff = 0;
	            
	            if(i == 0){
	                diff = nums[i];
	            }
	            else{
	                diff = nums[i] - nums[i-1];
	            }
	            
	            if(diff <= 0){
	                return false;
	            }
	            
	            int maxPrime = findMaxPrime(primes, diff);
	            
	            nums[i] -= maxPrime;
	            
	            /*
	            System.out.println("Main arr:");
	            System.out.println("num:" + nums[i]);
	            System.out.println("=========");
	            */
	        }
	        
	        return true;
	    }
	    
	    private List<Integer> getPrimes(int max){
	        boolean[] primes = new boolean[max + 1];
	        Arrays.fill(primes, true);
	        
	        primes[1] = false;
	        
	        for(int i=2; i * 1<primes.length; i++){
	            if(primes[i]){
	                for(int j=i*i; j<primes.length; j+=i){
	                    primes[j] = false;
	                }
	            }
	        }
	        
	        List<Integer> result = new ArrayList<>();
	        for(int i=0; i<primes.length; i++){
	            if(primes[i]){
	                result.add(i);
	            }
	        }
	        
	        /*
	        System.out.println("Printing primes: ");
	        for(int p : result){
	            System.out.print( p + ",");
	        }
	        
	        System.out.println("\n===========");
	        */
	        
	        return result;
	    }
	    
	    private int findMaxPrime(List<Integer> primes, int target){
	        int left = 0, right = primes.size()-1;
	        
	        while(left <= right){
	            int mid = left + (right - left)/2;
	            
	            if(primes.get(mid) == target){
	                right = mid - 1;
	            }
	            else if(primes.get(mid) < target){
	                left = mid + 1;
	            }
	            else{
	                right = mid - 1;
	            }
	        }
	        
	        /*
	        System.out.println("Printing max prime: ");
	        System.out.println( "t: " + target + "  pos: " + right + "  val: " + primes.get(right));
	        System.out.println("===========");
	        */
	        
	        return primes.get(right);
	    }
}
