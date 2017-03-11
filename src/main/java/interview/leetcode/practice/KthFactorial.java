package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class KthFactorial {
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
        System.out.println(new KthFactorial().getPermutation(3, 4));
    }
}
