package interview.leetcode.practice.round3.recursion;

import java.util.Arrays;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()){
            return false;
        }
        
        int[] arr1 = new int[26], arr2 = new int[26];
        
        for(int i=0; i<s1.length(); i++){
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }
        
       if(Arrays.equals(arr1, arr2)){
            return true;
        }
        
        for(int i=s1.length(), j=0; i<s2.length(); i++, j++){
            arr2[s2.charAt(j) - 'a']--;
            arr2[s2.charAt(i) - 'a']++;
            
            if(Arrays.equals(arr1, arr2)){
                return true;
            }
        }
        
        return false;
    }
}
