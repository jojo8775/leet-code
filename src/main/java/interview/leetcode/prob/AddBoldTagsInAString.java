package interview.leetcode.prob;

/**
 * You are given a string s and an array of strings words.

You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words.

If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag.
If two substrings wrapped by bold tags are consecutive, you should combine them.
Return s after adding the bold tags.

 

Example 1:

Input: s = "abcxyz123", words = ["abc","123"]
Output: "<b>abc</b>xyz<b>123</b>"
Explanation: The two strings of words are substrings of s as following: "abcxyz123".
We add <b> before each substring and </b> after each substring.
Example 2:

Input: s = "aaabbb", words = ["aa","b"]
Output: "<b>aaabbb</b>"
Explanation: 
"aa" appears as a substring two times: "aaabbb" and "aaabbb".
"b" appears as a substring three times: "aaabbb", "aaabbb", and "aaabbb".
We add <b> before each substring and </b> after each substring: "<b>a<b>a</b>a</b><b>b</b><b>b</b><b>b</b>".
Since the first two <b>'s overlap, we merge them: "<b>aaa</b><b>b</b><b>b</b><b>b</b>".
Since now the four <b>'s are consecutive, we merge them: "<b>aaabbb</b>".
 

Constraints:

1 <= s.length <= 1000
0 <= words.length <= 100
1 <= words[i].length <= 1000
s and words[i] consist of English letters and digits.
All the values of words are unique.
 

Note: This question is the same as 758. Bold Words in String.

Accepted
95,503
Submissions
189,521
 * 
 * Dec 1, 2024 - 2:46:52 AM
 * Jojo 
 */
public class AddBoldTagsInAString {
	public String addBoldTag(String s, String[] words) {
        boolean[] trackBoldIdx = new boolean[s.length()];
        
        for(int i=0; i<s.length(); i++){
            for(String w : words){
                int start = s.indexOf(w, i);
                
                while(start != -1){
                    for(int k=start; k < start + w.length(); k++){
                        trackBoldIdx[k] = true;
                    }
                    
                    start = s.indexOf(w, start + 1);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        
        for(int i=0; i<s.length(); i++){
            if(trackBoldIdx[i] == true){
                if(flag == 0){
                    sb.append("<b>");
                    flag = 1;
                }
            }
            else{
                if(flag == 1){
                    sb.append("</b>");
                    flag = 0;
                }
            }
            
            sb.append(s.charAt(i));
        }
        
        if(flag == 1){
            sb.append("</b>");
        }
        
        return sb.toString();
    }
    
    
    public String addBoldTag_1(String s, String[] words) {
        Trie head = new Trie();
        
        for(String w : words){
            Trie cur = head;
            
            for(int i=0; i<w.length(); i++){
                if(cur.children[w.charAt(i)] == null){
                    cur.children[w.charAt(i)] = new Trie();
                }
                
                cur = cur.children[w.charAt(i)];
            }
            
            cur.isEnd = true;
        }
        
        boolean[] matchIdxArr = new boolean[s.length()];
        
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            
            if(head.children[ch] == null){
                continue;
            }
            
            Trie cur = head;
            int j = i, end = i-1;
            
            while(cur != null && j < s.length()){
                cur = cur.children[s.charAt(j)];
                
                if(cur != null && cur.isEnd){
                    end = j;
                }
                
                j++;
            }
            
            j = i;
            while(j <= end){
                matchIdxArr[j++] = true;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        
        //print(matchIdxArr);
        
        for(int i=0; i<matchIdxArr.length; i++){
            if(matchIdxArr[i] == true){
                if(flag == 0){
                    sb.append("<b>");
                    flag = 1;
                }
            }
            else if(flag == 1){
                sb.append("</b>");
                flag = 0;
            }
            
            sb.append(s.charAt(i));
        }
        
        if(flag == 1){
            sb.append("</b>");
        }
        
        return sb.toString();
    }
    
    private static class Trie{
        Trie[] children = new Trie[256];
        boolean isEnd = false;
    }
    
    private void print(boolean[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }
}
