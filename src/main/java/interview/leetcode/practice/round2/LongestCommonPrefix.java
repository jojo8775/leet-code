package interview.leetcode.practice.round2;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < strs[0].length(); i++){
            char ch = strs[0].charAt(i);
            
            if(findCommonChar(strs, ch, i)){
                sb.append(ch);
            }
            else{
                break;
            }
        }
        
        return sb.toString();
    }
    
    private boolean findCommonChar(String[] arr, char ch, int idx){
        for(int i=1; i<arr.length; i++){
            if(arr[i].length() <= idx || arr[i].charAt(idx) != ch){
                return false;
            }
        }
        
        return true;
    }
}
