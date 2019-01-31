package interview.leetcode.practice;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        
        if(strs.length == 1){
            return strs[0];
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<strs[0].length(); i++){
            boolean endWordReached = false;
            char ch = '\0';
            for(int j=0; j<strs.length; j++){
                if(i == strs[j].length()){
                    endWordReached = true;
                    break;
                }
                
                if(j == 0){
                    ch = strs[j].charAt(i);
                }
                else if (ch != strs[j].charAt(i)){
                    endWordReached = true;
                    break;
                }
            }
            
            if(!endWordReached){
                break;
            }
            else{
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
}
