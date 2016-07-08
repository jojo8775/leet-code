package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

public class ValidPalindrom {
	public boolean isPalindrome(String s) {
        int a = 'a', A = 'A', z = 'z', Z = 'Z', zero = '0', nine='9';
        List<Character> list = new ArrayList<Character>();
        
        for(char c : s.toCharArray()){
            int temp = c;
            if((temp>=a && temp<=z) || (temp>=A && temp<=Z)){
                list.add(Character.toUpperCase(c));
            }
            else if(temp >= zero && temp <=nine){
            	list.add(c);
            }
        }
        
        for(int i=0, j=list.size() - 1; i<j; i++, j--){
        	if(!list.get(i).equals(list.get(j))){
        		return false;
        	}
        }
        
        return true;
    }
	
	public static void main(String[] args){
		System.out.println(new ValidPalindrom().isPalindrome("a."));
	}
}
