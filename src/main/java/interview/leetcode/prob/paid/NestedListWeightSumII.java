package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        return findSum(nestedList, 0);
    }
    
    private int findSum(List<NestedInteger> nestedList, int prevLevelSum){
        if(nestedList.isEmpty()){
            return 0;
        }
        
        int currentLevelSum = 0;
        List<NestedInteger> nextLevel = new ArrayList<NestedInteger>();
        
        for(NestedInteger nestedInteger : nestedList){
            if(nestedInteger.isInteger()){
                currentLevelSum += nestedInteger.getInteger();
            }
            else{
                nextLevel.addAll(nestedInteger.getList());
            }
        }
        
        currentLevelSum = prevLevelSum + currentLevelSum;
        currentLevelSum += findSum(nextLevel, currentLevelSum);
        
        return currentLevelSum;
    }
    
    public int depthSumInverse_Iterative(List<NestedInteger> nestedList){
        int currentLevelSum = 0,  prevLevelSum = 0;
        while(!nestedList.isEmpty()){
            List<NestedInteger> nextLevel = new ArrayList<NestedInteger>();
            for(NestedInteger nestedInteger : nestedList){
                if(nestedInteger.isInteger()){
                    currentLevelSum += nestedInteger.getInteger();
                }
                else{
                    nextLevel.addAll(nestedInteger.getList());
                }
            }
            
            prevLevelSum += currentLevelSum;
            nestedList = nextLevel;
        }
        
        return prevLevelSum;
    }
    
    
    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather
        // than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds
        // a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested
        // integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a
        // nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
