package interview.leetcode.prob;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.

 * @author jojo
 *
 */
public class PalindromNumber {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        
        int rev = 0, temp = x;
        while(temp!=0){
            rev = (rev * 10) + temp%10;
            temp /= 10;
        }
        
        return rev == x;
    }
    
    public static void main(String[] args){
    	System.out.println(new PalindromNumber().isPalindrome(1));
    }
}
