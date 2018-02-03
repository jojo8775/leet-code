package interview.leetcode.practice.round4.stack;

import java.util.ArrayList;
import java.util.List;

public class LetterCombination {
    public List<String> findSubstring(String str, int k){
        List<String> result = new ArrayList<>();
        
        int count = k-1;
        int[] arr = new int[256];
        
        for(int i=0; i<k-1; i++){
            if(arr[str.charAt(i)] ++ == 0){
                count--;
            }
        }
        
        for(int i=k-1, j = 0; i<str.length(); i++, j++){
            if(arr[str.charAt(i)]++ == 0){
                count--;
            }
            
            if(count == 0){
                result.add(str.substring(j, i+1));
            }
            
            if(--arr[str.charAt(j)] == 0){
                count++;
            }
        }
        
        
        return result;
    }
    
    public static void main(String[] args){
        List<String> result = new LetterCombination().findSubstring("jhsbdkcuwabeusdhsjhdjhsdjshdjshhjjhhabsvaaa", 1);
        for(String str : result){
            System.out.println(str);
        }
    }
}
