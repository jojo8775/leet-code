package interview.leetcode.practice.round2;

public class IntToRoman {
    public String intToRoman(int num) {
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            int idx = findNextIdx(num, val);
            sb.append(roman[idx]);
            num -= val[idx];
        }
        
        return sb.toString();
    }
    
    private int findNextIdx(int num, int[] val){
        int idx = 0;
        while(num < val[idx]){
            idx++;
        }
        
        return idx;
    }
}
