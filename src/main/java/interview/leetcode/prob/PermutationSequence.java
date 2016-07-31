package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 * @author jojo
 *
 */
public class PermutationSequence {
	/**
	 * Reference https://discuss.leetcode.com/topic/17348/explain-like-i-m-five-java-solution-in-o-n
	 */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n+1];
        
        int sum = 1;
        
        //initializing the factorial values
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum; 
        }
        
        List<Integer> numbers = new ArrayList<Integer>();
        //initializing the numbers
        for(int i=0; i<n; i++){
            numbers.add(i+1);
        }
        
        //making k as zero based
        k--;
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=1; i<=n; i++){
            int index = k/factorial[n-i]; 
            sb.append(numbers.get(index));
            numbers.remove(index);
            k -= index*factorial[n-i];
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args){
    	System.out.println(new PermutationSequence().getPermutation(4, 23));
    }
}
