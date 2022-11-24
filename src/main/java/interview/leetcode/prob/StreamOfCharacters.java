package interview.leetcode.prob;

/**
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.

For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.

Implement the StreamChecker class:

StreamChecker(String[] words) Initializes the object with the strings array words.
boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
 

Example 1:

Input
["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
[[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
Output
[null, false, false, false, true, false, true, false, false, false, false, false, true]

Explanation
StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
streamChecker.query("a"); // return False
streamChecker.query("b"); // return False
streamChecker.query("c"); // return False
streamChecker.query("d"); // return True, because 'cd' is in the wordlist
streamChecker.query("e"); // return False
streamChecker.query("f"); // return True, because 'f' is in the wordlist
streamChecker.query("g"); // return False
streamChecker.query("h"); // return False
streamChecker.query("i"); // return False
streamChecker.query("j"); // return False
streamChecker.query("k"); // return False
streamChecker.query("l"); // return True, because 'kl' is in the wordlist
 

Constraints:

1 <= words.length <= 2000
1 <= words[i].length <= 200
words[i] consists of lowercase English letters.
letter is a lowercase English letter.
At most 4 * 104 calls will be made to query.
Accepted
79,891
Submissions
154,956
 * @author jojo
 * Nov 24, 2022 2:03:30 PM
 */
public class StreamOfCharacters {
	public static class StreamChecker {
	    Tri head = new Tri();
	    StringBuilder sb = new StringBuilder();
	    int maxLen = 0;
	    
	    public StreamChecker(String[] words) {
	        for(String w : words){
	            Tri cur = head;
	            
	            maxLen = Math.max(maxLen, w.length());
	            
	            for(int i=w.length() - 1; i>=0; i--){
	                if(cur.children[w.charAt(i) - 'a'] == null){
	                    cur.children[w.charAt(i) - 'a'] = new Tri();
	                }
	                
	                cur = cur.children[w.charAt(i) - 'a'];
	            }
	            
	            cur.isWord = true;
	        }
	    }
	    
	    public boolean query(char letter) {
	        sb.append(letter);
	        
	        Tri cur = head;
	        for(int i=sb.length() - 1; i>=0 && cur != null; i--){
	            if(cur.children[sb.charAt(i) - 'a'] == null){
	                return false;
	            }
	            
	            cur = cur.children[sb.charAt(i) - 'a'];
	            
	            if(cur.isWord){
	                break;
	            }
	        }
	        
	        if(sb.length() > maxLen){
	            sb.deleteCharAt(0);
	        }
	        
	        return cur == null ? false : cur.isWord;
	    }
	    
	    private static class Tri{
	        Tri[] children = new Tri[26];
	        boolean isWord = false;
	    }
	}

	/**
	 * Your StreamChecker object will be instantiated and called as such:
	 * StreamChecker obj = new StreamChecker(words);
	 * boolean param_1 = obj.query(letter);
	 */
}
