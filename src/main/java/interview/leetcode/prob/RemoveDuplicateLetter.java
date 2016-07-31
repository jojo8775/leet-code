package interview.leetcode.prob;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"


 * @author jojo
 *
 */
public class RemoveDuplicateLetter {
	public String removeDuplicateLetters(String s) {
        if(s == null || s.isEmpty()){
            return s;
        }
        
        //recording each character count
        int[] arr = new int[26];
        for(int i=0; i<s.length(); i++){
            int idx = (int)(s.charAt(i) - 'a');
            arr[idx] += 1;
        }
        
        Stack<Character> stack = new Stack<Character>();
        Set<Character> set = new HashSet<Character>();
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < s.length(); i++){
            char ch = s.charAt(i);
            int idx = (int) (ch - 'a');
            
            //since relative order needs to be maintained and we are 
            //suppose to give back smalled lexicographic order
            if(!stack.contains(ch)) {
                while (!stack.isEmpty() && ch <= stack.peek() && arr[stack.peek()-'a'] >= 1){
                    stack.pop();
                }
                
                stack.push(ch);
            }
            
            arr[idx] -= 1;
        }
        
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        
        return sb.reverse().toString();
    }
	
	public static void main(String[] args){
		System.out.println(new RemoveDuplicateLetter().removeDuplicateLetters("bcabc"));
	}
}
