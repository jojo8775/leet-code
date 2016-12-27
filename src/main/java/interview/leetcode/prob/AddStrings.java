package interview.leetcode.prob;

/**
 * Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * @author jojo
 *
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0, sum =  0;
        
        while(i>=0){
            sum = (int) (num1.charAt(i--) - '0');
            if(j>=0){
                sum += (int) (num2.charAt(j--) - '0');
            }
            
            carry = calculate(sb, carry, sum);
        }
        
        while(j>=0){
            sum = (int) (num2.charAt(j--) - '0');
            carry = calculate(sb, carry, sum);
        }
        
        if(carry > 0){
            sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
    
    private int calculate(StringBuilder sb, int carry, int sum){
        sum += carry;
        carry = sum/10;
        sum %= 10;
        
        sb.append(sum);
        return carry;
    }
    
    public static void main(String[] args){
        System.out.println(new AddStrings().addStrings("9", "99"));
    }
}
