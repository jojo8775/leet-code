package interview.leetcode.practice.round3.string;

public class StreamSubstring {
    public int findNumberOfSubstring(String str){
        int[] arr = new int[26];
        
        int maxSubstringLength = 1;
        for(int i=0; i<str.length(); i++){
            if(i > 0 && (str.charAt(i) - str.charAt(i-1) == 1 || str.charAt(i-1) - str.charAt(i) == 25)){
                maxSubstringLength ++;
            }
            else{
                maxSubstringLength = 0;
            }
            
            arr[(int)(str.charAt(i) - 'a')] = Math.max(arr[(int)(str.charAt(i) - 'a')], maxSubstringLength);
        }
        
        int totalSum = 0;
        for(int a : arr){
            totalSum += a;
        }
        
        return totalSum;
    }
}
