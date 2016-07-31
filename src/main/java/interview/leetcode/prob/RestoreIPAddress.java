package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * @author jojo
 *
 */
public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        
        if(s == null || s.length() < 4){
            return result;
        }
        
        find(result, s, 0, new ArrayList<String>(), 3);
        
        return result;
    }
    
    private void find(List<String> result, String s, int idx, List<String> list, int dotCount){
        if(dotCount < 0 && idx != s.length()){
            return;
        }
        
        if(dotCount < 0 && idx == s.length()){
            StringBuilder sb = new StringBuilder();
            sb.append(list.get(0));
            
            for(int i=1; i<list.size(); i++){
                sb.append(".").append(list.get(i));
            }
            
            result.add(sb.toString());
            return;
        }
        
        int count = 0, num = -1;
        for(int i=idx; i<s.length(); i++){
            // cannot have a number starting with 0
            if(num == 0){
                break;
            }
            
            num = Integer.valueOf(s.substring(idx, i+1));
            if(num < 256){
                list.add(String.valueOf(num));
                find(result, s, i+1, list, dotCount - 1);
                list.remove(list.size() - 1);
            }
            
            if(++count > 3){
                break;
            }
        }
    }
    
    public static void main(String[] args){
//    	List<String> result = new RestoreIPAddress().restoreIpAddresses("25525511135");
    	List<String> result = new RestoreIPAddress().restoreIpAddresses("0000");   	
    	for(String str : result){
    		System.out.println(str);
    	}
    }
}
