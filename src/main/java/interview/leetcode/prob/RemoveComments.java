package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Basically remove all the line comment, block comments and empty lines from a code snippet.
 * Jan 31, 2022 11:29:16 PM
 */
public class RemoveComments {
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        boolean blockComment = false;
        
        for(String line : source){
            int len = line.length();
            
            for(int i=0; i<len; i++){
                // if a block comment is already open
                if(blockComment){
                    char ch = line.charAt(i);
                    
                    // look for */ 
                    if(ch == '*' && i < len - 1 && line.charAt(i+1) == '/'){
                        blockComment = false;
                        i++;
                    }
                }
                else{
                    char ch = line.charAt(i);
                    
                    // if line comment is found 
                    if(ch == '/' && i < len - 1 && line.charAt(i+1) == '/'){
                        break;
                    }
                    
                    // if block comment begins 
                    else if(ch == '/' && i < len - 1 && line.charAt(i+1) == '*'){
                        blockComment = true;
                        i++;
                    }
                    
                    // else append the char 
                    else{
                        sb.append(ch);
                    }
                }
            }
            
            // if there is no block comment and sb is not empty
            if(!blockComment && sb.length() > 0){
                result.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        
        return result;
    }
}
