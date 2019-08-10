package interview.leetcode.prob;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].

 * @author jojo
 * Jul 21, 2019 9:47:40 AM
 */
public class FlattenNestedListIterator {
	
	public class NestedIterator implements Iterator<Integer> {

	    private Deque<NestedInteger> queue = new LinkedList<>();
	    private NestedInteger cur;
	    
	    public NestedIterator(List<NestedInteger> nestedList) {
	        for(NestedInteger n : nestedList){
	            queue.offerLast(n);
	        }
	        
	        cur = getNext();
	    }
	    
	    private NestedInteger getNext(){
	        NestedInteger next = null;
	        
	        while(next == null){
	            
	            if(queue.isEmpty()){
	                break;
	            }
	            
	            NestedInteger top = queue.poll();
	            if(top.isInteger()){
	                next = top;
	            }
	            else{
	                List<NestedInteger> list = top.getList();
	                for(int i=list.size() - 1; i>=0; i--){
	                    queue.offerFirst(list.get(i));
	                }
	            }
	        }
	        
	        return next;
	    }

	    @Override
	    public Integer next() {
	        NestedInteger temp = cur;
	        cur = getNext();
	        return temp.getInteger();
	    }

	    @Override
	    public boolean hasNext() {
	        return cur != null;
	    }
	}
	
	public interface NestedInteger {

		boolean isInteger();

		Integer getInteger();

		List<NestedInteger> getList();
	}

}
