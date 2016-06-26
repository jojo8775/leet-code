package interview.leetcode.prob;

/**
 * Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
 * @author jojo
 *
 */
public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        int result = 0, num1 = 0, num2 = 0, idx1=0, idx2 = 0;
        while(true){
            int ref = idx1;
            idx1 = getEndIndex(version1, idx1);
            if(idx1 == -1){
                num1 = 0;
            }
            else{
                num1 = Integer.valueOf(version1.substring(ref, idx1++));
            }
            
            ref = idx2;
            idx2 = getEndIndex(version2, idx2);
            if(idx2 == -1){
                num2 = 0;
            }
            else{
                num2 = Integer.valueOf(version2.substring(ref, idx2++));
            }
            
            if(idx1 == -1 && idx2 == -1){
                break;
            }
            else if(num1 < num2){
                result = -1;
                break;
            }
            else if(num1 > num2){
                result = 1;
                break;
            }
        }
        
        return result;
    }
    
    private int getEndIndex(String s, int idx){
        if(idx < 0 || idx >= s.length()){
            return -1;
        }
        
        while(idx < s.length()){
            char ch = s.charAt(idx);
            if(ch == '.'){
                return idx;
            }
                
            idx ++;
        }
        
        return idx;
    }
}
