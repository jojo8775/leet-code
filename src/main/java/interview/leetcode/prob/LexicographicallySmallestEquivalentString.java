package interview.leetcode.prob;

/**
 * Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters. For example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.

Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'
Symmetry: 'a' == 'b' implies 'b' == 'a'
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are equivalent strings, and "aab" is the lexicographically smallest equivalent string of S.

Return the lexicographically smallest equivalent string of S by using the equivalency information from A and B.

 

Example 1:

Input: A = "parker", B = "morris", S = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in A and B, we can group their characters as [m,p], [a,o], [k,r,s], [e,i]. The characters in each group are equivalent and sorted in lexicographical order. So the answer is "makkek".
Example 2:

Input: A = "hello", B = "world", S = "hold"
Output: "hdld"
Explanation:  Based on the equivalency information in A and B, we can group their characters as [h,w], [d,e,o], [l,r]. So only the second letter 'o' in S is changed to 'd', the answer is "hdld".
Example 3:

Input: A = "leetcode", B = "programs", S = "sourcecode"
Output: "aauaaaaada"
Explanation:  We group the equivalent characters in A and B as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in S except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 

Note:

String A, B and S consist of only lowercase English letters from 'a' - 'z'.
The lengths of string A, B and S are between 1 and 1000.
String A and B are of the same length.
Accepted
5.1K
Submissions
7.6K

 * @author jojo
 * Feb 7, 2021  5:44:49 PM
 */
public class LexicographicallySmallestEquivalentString {
	public String smallestEquivalentString(String A, String B, String S) {
        int[] graph = new int[26];
        
        for(int i=0; i<26; i++){
            graph[i] = i;
        }
        
        for(int i=0; i<A.length(); i++){
            int p1 = find(graph, (int) A.charAt(i) - 'a');
            int p2 = find(graph, (int) B.charAt(i) - 'a');
            
            if(p1 < p2){
                graph[p2] = p1;
            }
            else{
                graph[p1] = p2;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<S.length(); i++){
            char ch = S.charAt(i);
            int idx = (int) (ch - 'a');
            sb.append((char) ('a' + find(graph, idx)));
        }
        
        return sb.toString();
    }
    
    private int find(int[] graph, int idx){
        while(graph[idx] != idx){
            graph[idx] = graph[graph[idx]];
            idx = graph[idx];
        }
        
        return idx;
    }
}
