package interview.leetcode.prob;

/**
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.

 

Example 1:

Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Example 2:


Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
 

Constraints:

n == dominoes.length
1 <= n <= 105
dominoes[i] is either 'L', 'R', or '.'.
Accepted
107,846
Submissions
189,279
 * @author jojo
 * Nov 20, 2022 2:44:27 PM
 */
public class PushDominoes {
	public String pushDominoes(String dominoes) {
        String d = "L" + dominoes + "R";
        
        StringBuilder sb = new StringBuilder();
        
        int len = d.length();
        
        for(int i=0, j=1; j<len; j++){
            if(d.charAt(j) == '.'){
                continue;
            }
            
            int diff = j - i - 1;
            
            if(i > 0){
                sb.append(d.charAt(i));
            }
            
            if(d.charAt(i) == d.charAt(j)){
                for(int k=0; k<diff; k++){
                    sb.append(d.charAt(j));
                }
            }
            else if(d.charAt(i) == 'L' && d.charAt(j) == 'R'){
                for(int k=0; k<diff; k++){
                    sb.append('.');
                }
            }
            //else if(d.charAt(i) == 'R' && d.charAt(j) == 'L'){
            else{
                int k =0;
                
                while(k < diff/2){
                    sb.append(d.charAt(i));
                    k++;
                }
                
                if(diff%2 == 1){
                    sb.append('.');
                    k++;
                }
                
                while(k < diff){
                    sb.append(d.charAt(j));
                    k++;
                }
            }
            
            i = j;
        }
        
        return sb.toString();
    }
}
