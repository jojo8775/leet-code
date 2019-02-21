package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddress {
	public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backTrack(result, s, 0, new ArrayList<>());
        
        return result;
    }
    
    private void backTrack(List<String> result, String s, int idx, List<Integer> ipParts){
        if(ipParts.size() == 4 && idx != s.length()){
            return;
        }
        
        if(ipParts.size() == 4 && idx == s.length()){
            StringBuilder sb = new StringBuilder(String.valueOf(ipParts.get(0)));
            
            for(int i=1; i<4; i++){
                sb.append(".").append(ipParts.get(i));
            }
            
            result.add(sb.toString());
            return;
        }
        
        if(ipParts.size() == 4){
            return;
        }
        
        int num = 0;
        for(int i=idx; i<s.length(); i++){
            num *= 10;
            num += Integer.valueOf(s.charAt(i) - '0');
            
            if(idx != i && (num < 10 || num >= 256)){
                break;
            }
            
            ipParts.add(num);
            backTrack(result, s, i+1, ipParts);
            ipParts.remove(ipParts.size() - 1);
        }
    }
    
    public static void main(String[] args) {
    	List<String> result = new RestoreIpAddress().restoreIpAddresses("010010");
    	result.forEach(e -> System.out.println(e));
    }
}
