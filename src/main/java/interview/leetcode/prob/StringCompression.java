package interview.leetcode.prob;

/**
 * Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


Follow up:
Could you solve it using only O(1) extra space?


Example 1:
Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
Example 2:
Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
Example 3:
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
Note:
All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.
 * @author jojo
 *
 */
public class StringCompression {
    public int compress(char[] chars) {
        if(chars.length < 2){
            return chars.length;
        }
        
        int idx1=0, idx2=0, len= chars.length;
        
        for(int i=1; i<len; i++){
            if(chars[idx2] != chars[i]){
                chars[idx1++] = chars[idx2];
                if((i-idx2) > 1){
                    int diff = i - idx2;
                    for(char ch : String.valueOf(diff).toCharArray()){
                        chars[idx1++] = ch;
                    }
                }
                
                idx2 = i;
            }
        }
        
        chars[idx1++] = chars[idx2];
        if((len - idx2) > 1){
            int diff = len - idx2;

            for(char ch : String.valueOf(diff).toCharArray()){
                chars[idx1++] = ch;
            }
        }
        
        return idx1;
    }
    
    public int compress_2(char[] chars) {
        int count = 1, idx = 0;
        
        for(int i=1; i<chars.length; i++){
            if(chars[i-1] == chars[i]){
                count++;
            }
            else{
                chars[idx++] = chars[i-1];
                if(count > 1){
                    String str = String.valueOf(count);
                    for(char c : str.toCharArray()){
                        chars[idx++] = c;
                    }
                }
                
                count = 1;
            }
        }
        
        chars[idx++] = chars[chars.length - 1];
        if(count > 1){
            String str = String.valueOf(count);
            for(char c : str.toCharArray()){
                chars[idx++] = c;
            }
        }
        
        return idx;
    }
}
