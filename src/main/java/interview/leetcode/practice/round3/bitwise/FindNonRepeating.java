package interview.leetcode.practice.round3.bitwise;

public class FindNonRepeating {
    public int findNumber(int[] arr){
        int result = 0;
        for(int n : arr){
            result ^= n;
        }
        
        return result;
    }
    
    public static void main(String[] args){
        System.out.println(new FindNonRepeating().findNumber(new int[]{2,1,34,2,1}));
    }
}
