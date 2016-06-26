package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * @author jojo
 *
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if(s == null || s.isEmpty() || numRows < 2){
            return s;
        }
        
        StringBuilder[] arr = new StringBuilder[numRows];
        
        //initializing the string builders
        for(int i=0; i<numRows; i++){
            arr[i] = new StringBuilder();
        }
        
        int idx = 0;
        while(idx < s.length()){
            int aIdx = 0;
            while(idx < s.length() && aIdx < numRows-1){
                arr[aIdx++].append(s.charAt(idx++));
            }
            
            while(idx < s.length() && aIdx > 0){
                arr[aIdx--].append(s.charAt(idx++));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<numRows; i++){
            sb.append(arr[i].toString());
        }
        
        return sb.toString();
    }
}
