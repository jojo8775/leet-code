package interview.leetcode.prob;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
 * @author jojo
 *
 */
public class CountAndSay {
    public String countAndSay(int n) {
        //base case
        if(n == 0){
            return "";
        }
        
        //initial string
        String result = "1";
        
        while(n-- > 1){
            int count = 1;
            char prev = result.charAt(0);
            StringBuilder sb = new StringBuilder();
            
            for(int i=1; i<result.length(); i++){
                if(prev == result.charAt(i)){
                    count++;
                }
                else{
                    sb.append(count).append(prev);
                    count = 1;
                    prev = result.charAt(i);
                }
            }
            
            sb.append(count).append(prev);
            
            result = sb.toString();
        }
        
        return result;
    }
	
	public static void main(String[] args){
		System.out.println(new CountAndSay().countAndSay(1));
	}
}
