package interview.leetcode.prob;

/**
 * Convert decimal to hex. Negative numbers should be treated as 2's complement.
 * @author jojo
 *
 */
public class NumberToHex {
    public String toHex(int num) {
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            sb.append(map[num & 15]);
            num = num >>> 4;
        }
        
        return sb.reverse().toString();
    }
    
    public static void main(String[] args){
        int n = -1;
        n = ~n;
        System.out.println(n);
    }
}
