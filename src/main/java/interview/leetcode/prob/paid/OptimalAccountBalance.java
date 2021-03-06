package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 * @author jojo
 *
 */
public class OptimalAccountBalance {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> indivialExpenses = new HashMap<Integer, Integer>();

        for (int[] tran : transactions) {
            indivialExpenses.put(tran[0], indivialExpenses.getOrDefault(tran[0], 0) + (-1 * tran[2]));
            indivialExpenses.put(tran[1], indivialExpenses.getOrDefault(tran[1], 0) + tran[2]);
        }

        List<Integer> transferValues = new ArrayList<Integer>();
        for (int value : indivialExpenses.values()) {
            if (value != 0) {
                transferValues.add(value);
            }
        }
        
        int simpleTransfers = filterSimpleTransfers(transferValues);
        
        return simpleTransfers + minTransfers(transferValues, 0);
    }
    
    private int filterSimpleTransfers(List<Integer> transferValues){
        Collections.sort(transferValues);
        int left = 0, right = transferValues.size() - 1,  count = 0;
        
        while(left < right){
            int val1 = -1 * transferValues.get(left), val2 = transferValues.get(right);
            if(val1 == val2){
                transferValues.remove(right);
                transferValues.remove(left);
                
                count ++;
                right -= 2;
            }
            else if(val1 > val2){
                left++;
            }
            else{
                right--;
            }
        }
        
        return count;
    }
    
    private int minTransfers(List<Integer> deltas, int start) {
        int result = Integer.MAX_VALUE;
        int n = deltas.size();
        
        while (start < n && deltas.get(start) == 0){
            start++;
        }
        
        if (start == n){
            return 0;
        }
        
        for (int i = start + 1; i < n; i++) {
            if ((long) deltas.get(i) * deltas.get(start) < 0) {
                deltas.set(i, deltas.get(i) + deltas.get(start));
                result = Math.min(result, 1 + minTransfers(deltas, start + 1));
                deltas.set(i, deltas.get(i) - deltas.get(start));
            }
        }
        
        return result;
    }
}
