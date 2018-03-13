package interview.leetcode.practice;

import java.util.HashSet;
import java.util.Set;

public class Pract1 {
    public int thirdMax(int[] nums) {
        Integer n1 = null, n2 = null, n3 = null;
        Set<Integer> set = new HashSet<>();
        for(int nn : nums){
            Integer n = nn;
            if(n3 == null || n3 < n){
                set.remove(n1);
                set.add(n);
                n1 = n2;
                n2 = n3;
                n3 = n;
            }
            else if(!set.contains(n) && (n2 == null || n2 < n)){
                set.remove(n1);
                set.add(n);
                n1 = n2;
                n2 = n;
            }
            else if(!set.contains(n) && (n1 == null || n1 < n)){
                set.remove(n1);
                set.add(n);
                n1 = n;
            }
        }
        
        return n1 == null? n3 : n1;
    }
    
    public static void main(String[] args){
        System.out.println(new Pract1().thirdMax(new int[]{-2147483648,-2147483648,-2147483648,-2147483648,1,1,1}));
    }
}
