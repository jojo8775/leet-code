package interview.leetcode.warmup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeString {
 // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        
        for(String str : strs){
            if(needsEncoding(str)){
                sb.append(encodeString(str));
            }
            else{
                sb.append(str);
            }
            
            sb.append("*#*");
        }
        
        return  sb.toString();
    }
    
    private boolean needsEncoding(String str){
        for(char ch : str.toCharArray()){
            if(ch == '#'){
                return true;
            }
        }
        
        return false;
    }
    
    private String encodeString(String str){
        StringBuilder sb = new StringBuilder();
        for(char ch : str.toCharArray()){
            if(ch == '#'){
                sb.append("##");
            }
            else{
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        int count = 0, idx = 2;
        while(idx < s.length()){
            char ch1 = s.charAt(idx - 2), ch2 = s.charAt(idx - 1), ch3 = s.charAt(idx);
            if(ch1 == '*' && ch2 == '#' && ch3 == '*'){
                result.add(sb.toString());
                sb = new StringBuilder();
                count = 0;
                idx += 3;
            }
            else{
                if(ch1 == '#' && count == 0){
                    count = 1;
                }
                else{
                    sb.append(ch1);
                    count = 0;
                }
                idx++;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args){
        EncodeAndDecodeString e = new EncodeAndDecodeString();
        
        List<String> result = e.decode(e.encode(Arrays.asList("tfgeasuikry", "kziwyehgfuyw3333##fvd,jfygbsdj", ",dhjfvbuweyb2@s8473shjy3")));
        
        for(String s : result){
            System.out.println(s);
        }
    }
}
