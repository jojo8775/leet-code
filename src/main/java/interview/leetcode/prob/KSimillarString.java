package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:

Input: A = "ab", B = "ba"
Output: 1
Example 2:

Input: A = "abc", B = "bca"
Output: 2
Example 3:

Input: A = "abac", B = "baca"
Output: 2
Example 4:

Input: A = "aabc", B = "abca"
Output: 2
Note:

1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

 * @author jojo
 * Sep 2, 2019 8:48:07 PM
 */
public class KSimillarString {
    public int kSimilarity(String A, String B) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(A);
        visited.add(A);
        
        int steps = 0;
        
        while(!queue.isEmpty()){
            for(int size = queue.size() - 1; size>=0; size--){
                String top = queue.poll();
                
                if(top.equals(B)){
                    return steps;  
                }
                
                for(String n : getNeighbours(top, B)){
                    if(visited.add(n)){
                        queue.offer(n);
                    }
                }
            }
            
            steps++;
        }
        
        return -1;
    }
    
    private List<String> getNeighbours(String s1, String s2){
        int i=0;
        
        char[] cArr = s1.toCharArray();
        
        List<String> result = new ArrayList<>();
        
        for(;i<cArr.length;i++){
            if(cArr[i] != s2.charAt(i)){
                break;
            }
        }
        
        for(int j=i+1; j<cArr.length; j++){
            if(cArr[j] == s2.charAt(i)){
                swap(cArr, i, j);
                result.add(String.valueOf(cArr));
                swap(cArr, i, j);
            }
        }
        
        return result;
    }
    
    private void swap(char[] cArr, int i, int j){
        char temp = cArr[j];
        cArr[j] = cArr[i];
        cArr[i] = temp;
    }
}
