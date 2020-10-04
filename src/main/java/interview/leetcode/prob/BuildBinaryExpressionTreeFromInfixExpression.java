package interview.leetcode.prob;

import java.util.Deque;
import java.util.LinkedList;

/**
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with 2 children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).

For each internal node with operator o, the infix expression that it represents is (A o B), where A is the expression the left subtree represents and B is the expression the right subtree represents.

You are given a string s, an infix expression containing operands, the operators described above, and parentheses '(' and ')'.

Return any valid binary expression tree, which its in-order traversal reproduces s after omitting the parenthesis from it (see examples below).

Please note that order of operations applies in s. That is, expressions in parentheses are evaluated first, and multiplication and division happen before addition and subtraction.

Operands must also appear in the same order in both s and the in-order traversal of the tree.

 

Example 1:



Input: s = "2-3/(5*2)+1"
Output: [+,-,1,2,/,null,null,null,null,3,*,null,null,5,2]
Explanation: The inorder traversal of the tree above is 2-3/5*2+1 which is the same as s without the parenthesis. The tree also produces the correct result and its operands are in the same order as they appear in s.
The tree below is also a valid binary expression tree with the same inorder traversal as s:

The third tree below however is not valid. Although it produces the same result and is equivalent to the above trees, its inorder traversal doesn't produce s and its operands are not in the same order as s.

Example 2:



Input: s = "3*4-2*5"
Output: [-,*,*,3,4,2,5]
Explanation: The tree above is the only valid tree whose inorder traversal produces s.
Example 3:

Input: s = "1+2+3+4+5"
Output: [+,+,5,+,4,null,null,+,3,null,null,1,2]
Explanation: The tree [+,+,5,+,+,null,null,1,2,3,4] is also one of many other valid trees.
 

Constraints:

1 <= s.length <= 105
s consists of digits and the characters '+', '-', '*', '/', '(', and ')'.
Operands in s are exactly 1 digit.
It is guaranteed that s is a valid expression.
Accepted
363
Submissions
565
 * @author jojo
 * Oct 3, 2020  11:01:50 PM
 */
public class BuildBinaryExpressionTreeFromInfixExpression {
	public Node expTree(String s) {
        Deque<Node> nodes = parseInput(s);
        nodes = connect(nodes, '*', '/');
        nodes = connect(nodes, '+', '-');
        
        return nodes.pollFirst();
    }
    
    private Deque<Node> parseInput(String str){
        
        int i = 0, len = str.length();
        Deque<Node> nodes = new LinkedList<>();
        
        while(i<len){
            char ch1 = str.charAt(i);
            if(ch1 == '('){
                int j = i + 1;
                int bracketCnt = 1;
                
                while(bracketCnt != 0){
                    char ch2 = str.charAt(j++);
                    if(ch2 == ')'){
                        bracketCnt--;
                    }
                    else if(ch2 == '('){
                        bracketCnt++;
                    }
                }
                
                Node exp = expTree(str.substring(i + 1,j - 1));
                
                if(exp != null){
                    nodes.offer(exp);
                }
                
                i = j;
            }
            else{
                nodes.offer(new Node(ch1));
                i++;
            }
        }
        
        return nodes;
    }
    
    private Deque<Node> connect(Deque<Node> nodes, char ch1, char ch2){
        if(nodes.isEmpty()){
            return nodes;
        }
        
        Deque<Node> nodes2 = new LinkedList<>();
        nodes2.offer(nodes.poll());
        
        while(!nodes.isEmpty()){
            Node n = nodes.poll();
            if(n.left == null && (n.val == ch1 || n.val == ch2)){
                n.left = nodes2.pollLast();  // head of the queue
                n.right = nodes.poll();
            }
            
            nodes2.offer(n);
        }
        
        return nodes2;
    }
    
    private static class Node{
    	public char val;
    	public Node left = null, right = null;
    	
    	public Node(char val) {
    		this.val = val;
    	}
    }
}
