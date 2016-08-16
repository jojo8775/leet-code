package interview.leetcode.prob;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 * @author jojo
 *
 */
public class MinStack {
	// stores the actual number
    private Stack<Integer> stack1;
    // stores the index of min number
    private Stack<Integer> stack2;
    
    
    /** initialize your data structure here. */
    public MinStack() {
        this.stack1 = new Stack<Integer>();
        this.stack2 = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack1.push(x);
        if(stack2.isEmpty()){
            stack2.push(0);
        }
        else if(stack1.get(stack2.peek()) > x){
            stack2.push(stack1.size() - 1);
        }
    }
    
    public void pop() {
        int top = stack1.pop();
        if(stack2.peek() == stack1.size()){
            stack2.pop();
        }
    }
    
    public int top() {
        return stack1.peek();
    }
    
    public int getMin() {
        return stack1.get(stack2.peek());
    }
}
