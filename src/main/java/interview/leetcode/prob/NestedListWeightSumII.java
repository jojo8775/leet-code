package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import javafx.util.Pair;

/**
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer.

The weight of an integer is maxDepth - (the depth of the integer) + 1.

Return the sum of each integer in nestedList multiplied by its weight.

 

Example 1:


Input: nestedList = [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
Example 2:


Input: nestedList = [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1.
1*3 + 4*2 + 6*1 = 17
 

Constraints:

1 <= nestedList.length <= 50
The values of the integers in the nested list is in the range [-100, 100].
The maximum depth of any integer is less than or equal to 50.
Accepted
100,983
Submissions
150,413
 * @author jojo
 * Jan 2, 2022 11:01:19 PM
 */
public class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Stack<Pair<Integer, NestedInteger>> stack = new Stack<>();
        for(NestedInteger n : nestedList){
            stack.push(new Pair<Integer, NestedInteger>(1, n));
        }
        
        List<Pair<Integer, NestedInteger>> list = new ArrayList<>();
        
        while(!stack.isEmpty()){
            Pair<Integer, NestedInteger> top = stack.pop();
            int depth = top.getKey();
            NestedInteger ni = top.getValue();
            
            if(ni.isInteger()){
                list.add(new Pair<Integer, NestedInteger>(depth, ni));
            }
            else{
                for(NestedInteger n : ni.getList()){
                    stack.push(new Pair<Integer, NestedInteger>(depth + 1, n));
                }
            }
        }
        
        if(list.isEmpty()){
            return 0;
        }
        
        int max = list.stream().mapToInt(x -> x.getKey()).max().getAsInt();
        
        list = list.stream().map(x -> new Pair<Integer, NestedInteger>(max - x.getKey() + 1, x.getValue())).collect(Collectors.toList());
        
        int[] result = {0};
        list.stream().forEach(x -> result[0] += (x.getKey() * x.getValue().getInteger()));
        
        return result[0];
    }
	
	public interface NestedInteger {
		// @return true if this NestedInteger holds a single integer, rather than a
		// nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds a
		// single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// Set this NestedInteger to hold a single integer.
		public void setInteger(int value);

		// Set this NestedInteger to hold a nested list and adds a nested integer to it.
		public void add(NestedInteger ni);

		// @return the nested list that this NestedInteger holds, if it holds a nested
		// list
		// Return empty list if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}
}
