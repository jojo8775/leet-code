package interview.leetcode.practice;

public class ValidPalindrom {
	public boolean isPalindrome(String s) {
        for(int i=0, j=s.length() - 1; i<j; i++) {
        	char ch1 = s.charAt(i);
        	if(!isAlphaNumericChar(ch1)) {
        		continue;
        	}
        	
        	char ch2 = s.charAt(j);
        	while(i<j && !isAlphaNumericChar(ch2)) {
        		ch2 = s.charAt(--j);
        	}
        	
        	j--;
        	
        	if(Character.toLowerCase(ch1) != Character.toLowerCase(ch2)) {
        		return false;
        	}
        }
        
        return true;
    }
	
	private boolean isAlphaNumericChar(char ch) {
		if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(new ValidPalindrom().isPalindrome("A man, a plan, a canal: Panama"));
		System.out.println(new ValidPalindrom().isPalindrome("race a car"));
	}
}
