package interview.leetcode.practice.round4.arrays;

public class FlipGameII {
    public boolean canWin(String s) {
        return findStrategy(s.toCharArray());
    }
    
    private boolean findStrategy(char[] cArr){
        for(int i=1; i<cArr.length; i++){
            if(cArr[i] == '+' && cArr[i-1] == '+'){
                cArr[i] = '-';
                cArr[i-1] = '-';
                
                if(!findStrategy(cArr)){
                    cArr[i] = '+';
                    cArr[i-1] = '+';
                    return true;
                }
                
                cArr[i] = '+';
                cArr[i-1] = '+';
            }
        }
        
        return false;
    }
}
