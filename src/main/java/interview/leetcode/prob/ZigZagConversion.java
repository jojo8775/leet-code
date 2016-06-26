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
        
        List<List<Character>> ll = new ArrayList<List<Character>>();
        
        //initialized the lists
        for(int i=0; i<numRows; i++){
            ll.add(new ArrayList<Character>());
        }
        
        int idx = 0;
        while(idx < s.length()){
            int lIdx = 0;
            while(idx < s.length() && lIdx<numRows){
                ll.get(lIdx++).add(s.charAt(idx++));
            }
            lIdx--;
            lIdx--;
            while(idx < s.length() && lIdx>0){
                ll.get(lIdx--).add(s.charAt(idx++));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(List<Character> l : ll){
            for(Character c : l){
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
