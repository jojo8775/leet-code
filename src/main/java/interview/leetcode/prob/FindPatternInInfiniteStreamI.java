package interview.leetcode.prob;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * You are given a binary array pattern and an object stream of class InfiniteStream representing a 0-indexed infinite stream of bits.

The class InfiniteStream contains the following function:

int next(): Reads a single bit (which is either 0 or 1) from the stream and returns it.
Return the first starting index where the pattern matches the bits read from the stream. For example, if the pattern is [1, 0], the first match is the highlighted part in the stream [0, 1, 0, 1, ...].

 

Example 1:

Input: stream = [1,1,1,0,1,1,1,...], pattern = [0,1]
Output: 3
Explanation: The first occurrence of the pattern [0,1] is highlighted in the stream [1,1,1,0,1,...], which starts at index 3.
Example 2:

Input: stream = [0,0,0,0,...], pattern = [0]
Output: 0
Explanation: The first occurrence of the pattern [0] is highlighted in the stream [0,...], which starts at index 0.
Example 3:

Input: stream = [1,0,1,1,0,1,1,0,1,...], pattern = [1,1,0,1]
Output: 2
Explanation: The first occurrence of the pattern [1,1,0,1] is highlighted in the stream [1,0,1,1,0,1,...], which starts at index 2.
 

Constraints:

1 <= pattern.length <= 100
pattern consists only of 0 and 1.
stream consists only of 0 and 1.
The input is generated such that the pattern's start index exists in the first 105 bits of the stream.
Accepted
1,152
Submissions
1,977
 * Nov 21, 2024 - 11:34:35 PM
 * Jojo 
 */
public class FindPatternInInfiniteStreamI {
	// using rolling hash 
    public int findPattern(InfiniteStream infiniteStream, int[] pattern) {
        int hashp = 0;
        for (int i = 0; i < pattern.length; i++) {
            hashp = hashp * 2 + pattern[i];
        }
        int hashs = 0;
        for (int i = 0; i < pattern.length; i++) {
            hashs = hashs * 2 + infiniteStream.next();
        }

        int idx = 0;
        int base = (int)Math.pow(2, pattern.length - 1);
        while (true) {
            if (hashs == hashp) {
                return idx;
            }
            hashs -= (hashs / base) * base;
            hashs = hashs * 2 + infiniteStream.next();
            idx++;
        }
    }
    
    public int findPattern_sw(InfiniteStream infiniteStream, int[] pattern) {
        int idx = 0, patternSum = 0, len = pattern.length;
        
        int seqSum = 0;
        
        for(int n : pattern){
            patternSum += n;
        }
        
        Deque<Integer> deque = new LinkedList<>();
        
        while(idx < len-1){
            int num = infiniteStream.next();
            seqSum += num;
            deque.offerLast(num);
            idx++;
        }
        
        boolean foundPattern = false;
        
        while (!foundPattern){
            int num = infiniteStream.next();
            seqSum += num;
            deque.offerLast(num);
            idx++;
            
            if(seqSum == patternSum){
                if(areEqual(deque, pattern)){
                    foundPattern = true;
                    break;
                }
            }
            
            seqSum -= deque.pollFirst();
        }
        
        return idx - len;
    }
    
    private boolean areEqual(Deque<Integer> deque, int[] arr){
        int idx = 0;
        for(int n : deque){
            if(n != arr[idx++]){
                return false;
            }
        }
        
        return true;
    }
}
