package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class PalindromPermutation {
    public List<String> generatePalindromes(String s) {
        int[] arr = new int[256];
        for(char ch : s.toCharArray()){
            arr[ch]++;
        }
        
        int oddCount = 0;
        String oddChar = "";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<256; i++){
            if(arr[i] > 0){
                if(arr[i]%2 != 0){
                    oddCount++;
                    oddChar = String.valueOf((char) i); 
                }
                
                for(int j=0; j<arr[i]/2; j++){
                    sb.append((char) i);
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        if(oddCount > 1){
            return result;
        }
        
        permute(result, new StringBuilder(), sb.toString(), oddChar, new boolean[sb.length()]);
        return result;
    }
    
    private void permute(List<String> result, StringBuilder sb, String str, String oddChar, boolean[] visited){
        if(sb.length() == str.length()){
            StringBuilder temp = new StringBuilder(sb);
            result.add(temp.toString() + oddChar + temp.reverse().toString());
            return;
        }
        
        for(int i=0; i<str.length(); i++){
            if(visited[i] || (i>0 && str.charAt(i) == str.charAt(i-1) && !visited[i-1])){
                continue;
            }
            
            sb.append(str.charAt(i));
            visited[i] = true;
            
            permute(result, sb, str, oddChar, visited);
            
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
}
