package interview.leetcode.prob;

/**
 * You are given two strings s and t. In one step, you can append any character to either s or t.

Return the minimum number of steps to make s and t anagrams of each other.

An anagram of a string is a string that contains the same characters with a different (or the same) ordering.

 

Example 1:

Input: s = "leetcode", t = "coats"
Output: 7
Explanation: 
- In 2 steps, we can append the letters in "as" onto s = "leetcode", forming s = "leetcodeas".
- In 5 steps, we can append the letters in "leede" onto t = "coats", forming t = "coatsleede".
"leetcodeas" and "coatsleede" are now anagrams of each other.
We used a total of 2 + 5 = 7 steps.
It can be shown that there is no way to make them anagrams of each other with less than 7 steps.
Example 2:

Input: s = "night", t = "thing"
Output: 0
Explanation: The given strings are already anagrams of each other. Thus, we do not need any further steps.
 

Constraints:

1 <= s.length, t.length <= 2 * 105
s and t consist of lowercase English letters.
Accepted
11,565
Submissions
17,338
 * @author jojo
 * Feb 27, 2022 12:15:18 AM
 */
public class MinimumNumberOfStepsToMakeTwoStringsAnagramII {
    public int minSteps(String s, String t) {
        int[] arr = new int[26];
        
        for(int i=0; i<s.length(); i++){
            arr[s.charAt(i) - 'a']++;
        }
        
        for(int i=0; i<t.length(); i++){
            arr[t.charAt(i) - 'a']--;
        }
        
        int changes = 0;
        
        for(int i=0; i<26; i++){
            changes += Math.abs(arr[i]);
        }
        
        return changes;
    }
    
//     public int minSteps(String s, String t) {
//         char[] cArr1 = s.toCharArray();
//         char[] cArr2 = t.toCharArray();
        
//         Arrays.sort(cArr1);
//         Arrays.sort(cArr2);
        
//         int l1 = s.length(), l2 = t.length(), i=0, j=0, change = 0;
        
//         while(i < l1 && j < l2){
//             if(cArr1[i] == cArr2[j]){
//                 i++;
//                 j++;
//                 continue;
//             }
            
//             change++;
            
//             if(cArr1[i] <= cArr2[j]){
//                 i++;
//             }
//             else{
//                 j++;
//             }
//         }
        
//         return change + (l1 - i) + (l2 - j);
//     }
}
