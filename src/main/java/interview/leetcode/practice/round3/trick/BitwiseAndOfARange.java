package interview.leetcode.practice.round3.trick;

public class BitwiseAndOfARange {
    public int calculate(int m, int n){
        int leftShift = 0;
        // every odd number will end with 1 and even number will end with 0
        while(m != 0 && m != n){
            m = m >> 1;
            n = n >> 1;
            leftShift++;
        }
        
        // if smaller number is 0 then the remaining bit of bigger number is of no use
        if(m == 0){
            return 0;
        }
        
        // return the common numbers which are not 0
        return m << leftShift;
    }
}
