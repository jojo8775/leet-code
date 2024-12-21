package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.

Return the lexicographically largest repeatLimitedString possible.

A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.

 

Example 1:

Input: s = "cczazcc", repeatLimit = 3
Output: "zzcccac"
Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
The letter 'a' appears at most 1 time in a row.
The letter 'c' appears at most 3 times in a row.
The letter 'z' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.
Example 2:

Input: s = "aababab", repeatLimit = 2
Output: "bbabaa"
Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa". 
The letter 'a' appears at most 2 times in a row.
The letter 'b' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.
 

Constraints:

1 <= repeatLimit <= s.length <= 105
s consists of lowercase English letters.
Accepted
9,625
Submissions
19,384
 * @author jojo
 * Feb 23, 2022 12:51:52 AM
 */
public class ConstructStringWithRepeatLimit {
    public String repeatLimitedString_cleaner(String s, int repeatLimit) {
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b) -> b - a);
        
        int[] arr = new int[26];
        
        for(char ch : s.toCharArray()){
            arr[ch - 'a']++;
        }
        
        for(int i=0; i<26; i++){
            if(arr[i] > 0){
                pq.offer((char)('a' + i));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(!pq.isEmpty()){
            char cur = pq.peek();
            for(int i=0; i<repeatLimit && arr[cur - 'a'] > 0; i++){
                sb.append(cur);
                arr[cur - 'a']--;
            }
            
            if(arr[cur - 'a'] == 0){
                pq.poll();
            }
            else if(arr[cur - 'a'] > 0 && pq.size() == 1){
                break;
            }
            else{
                char temp = pq.poll();
                
                char next = pq.peek();
                sb.append(next);
                arr[next - 'a']--;
                
                if(arr[next - 'a'] == 0){
                    pq.poll();
                }
                
                pq.offer(temp);
            }
        }
        
        return sb.toString();
        
    }
	
    public String repeatLimitedString(String s, int repeatLimit) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        int[] arr = new int[26];
        
        for(char ch : s.toCharArray()){
            arr[ch - 'a']++;
        }
        
        for(int i=0; i<26; i++){
            if(arr[i] > 0){
                pq.offer(new int[]{i, arr[i]});
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        int prevChar = -1;
        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int[] tempTop = null;
            if(top[0] == prevChar){
                tempTop = top;
                
                if(pq.isEmpty()){
                    top = null;
                }
                else{
                    top = pq.poll();
                }
            }
            
            if(top == null){
                break;
            }
            
            int count = tempTop == null ? repeatLimit : 1, charCount = top[1];
            char ch = (char)('a' + top[0]);
            
            prevChar = top[0];
            
            while(count > 0 && charCount > 0){
                sb.append(ch);
                count--;
                charCount--;
            }
            
            if(charCount > 0){
                top[1] = charCount;
                pq.offer(top);
            }
            
            if(tempTop != null){
                pq.offer(tempTop);
            }
        }
        
        return sb.toString();
    }
    
    public String repeatLimitedString2(String s, int repeatLimit) {
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) {
                pq.offer(new int[] { i, arr[i] });
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int[] top = pq.poll();

            int count = Math.min(repeatLimit, top[1]);
            top[1] -= count;

            char ch = (char) ('a' + top[0]);

            while (count > 0) {
                sb.append(ch);
                count--;
            }

            if (top[1] == 0) {
                continue;
            } else {
                if (pq.isEmpty()) {
                    break;
                } else {
                    int[] next = pq.poll();

                    sb.append((char) ('a' + next[0]));

                    next[1]--;
                    if (next[1] > 0) {
                        pq.offer(next);
                    }

                    pq.offer(top);
                }
            }
        }

        return sb.toString();
    }
    
}

