package interview.leetcode.prob.paid;

import java.util.List;

/**
 * Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?
Two variables is all you need. Try with x and y.
Beware of empty rows. It could be the first few rows.
To write correct code, think about the invariant to maintain. What is it?
The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
Not sure? Think about how you would implement hasNext(). Which is more complex?
Common logic in two different places should be refactored into a common method.
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 * @author jojo
 *
 */
public class Flatten2DVector {
    private List<List<Integer>> input;
    private int entryIdx = 0, listIdx = -1;
    private Integer current = null;
    
    public Flatten2DVector(List<List<Integer>> vec2d) {
        this.input = vec2d;
        for(int i=0; i<input.size(); i++){
            for(int j=0; j < input.get(i).size(); j++){
                if(current == null){
                    listIdx = i;
                    entryIdx = j + 1;
                    current = input.get(i).get(j);
                }
                else{
                    break;
                }
            }
            
            if(current != null){
                break;
            }
        }
    }

    public Integer next() {
        if(current == null){
            return null;
        }
        
        Integer next = null;
        while(listIdx < input.size()){
            List<Integer> list = input.get(listIdx);
            while(entryIdx < list.size()){
                next = list.get(entryIdx++);
                break;
            }
            
            if(next != null){
                break;
            }
            
            entryIdx = 0;
            listIdx ++;
        }
        
        Integer temp = current;
        current = next;
        return temp;
    }

    public boolean hasNext() {
        if(current == null){
            return false;
        }
        
        return true;
    }
}
