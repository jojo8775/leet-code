package interview.leetcode.practice;

public class ZigZag {
    public String convert(String s, int numRows) {
        if(numRows < 1){
            return "";
        }
        
        if(numRows == 1){
            return s;
        }
        
        StringBuilder[] arr = new StringBuilder[numRows];
        int idx = 0, offset = 1;
        
        for(char ch : s.toCharArray()){
            if(arr[idx] == null){
                arr[idx] = new StringBuilder();
            }
            
            arr[idx].append(ch);
            idx += offset;
            
            if(idx<0){
                idx = 1;
                offset = 1;
            }
            else if(idx == numRows){
                idx = numRows - 2;
                offset = -1;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(StringBuilder entry : arr){
            if(entry == null){
                continue;
            }
            
            sb.append(entry);
        }
        
        return sb.toString();
    }
}
