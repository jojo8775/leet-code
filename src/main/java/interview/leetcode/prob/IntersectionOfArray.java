package interview.leetcode.prob;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 * @author jojo
 *
 */
public class IntersectionOfArray {
	public int[] intersection(int[] num1, int[] num2) {
        Arrays.sort(num1);
        Arrays.sort(num2);
        
        Set<Integer> set = new HashSet<Integer>();
        
        int idx = 0;
        for(int i=0; i<num1.length; i++){
            while(idx < num2.length && num2[idx] < num1[i]){
                idx++;
            }
            
            if(idx == num2.length){
                break;
            }
            
            if(num1[i] == num2[idx]){
                set.add(num1[i]);
            }
        }
        
        int[] result = new int[set.size()];
        
        int j = 0;
        for(int i : set){
        	result[j++] = i;
        }
        
        return result;
    }
	
	public static void main(String[] args){
		int[] a1 = {1,2};
		int[] a2 = {2,1};
		
		int[] r = new IntersectionOfArray().intersection(a1, a2);
		System.out.println(r.length);
	}
}
