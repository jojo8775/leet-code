package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You are given an integer array groups, where groups[i] represents the size of the ith group. You are also given an integer array elements.

Your task is to assign one element to each group based on the following rules:

An element j can be assigned to a group i if groups[i] is divisible by elements[j].
If there are multiple elements that can be assigned, assign the element with the smallest index j.
If no element satisfies the condition for a group, assign -1 to that group.
Return an integer array assigned, where assigned[i] is the index of the element chosen for group i, or -1 if no suitable element exists.

Note: An element may be assigned to more than one group.

 

Example 1:

Input: groups = [8,4,3,2,4], elements = [4,2]

Output: [0,0,-1,1,0]

Explanation:

elements[0] = 4 is assigned to groups 0, 1, and 4.
elements[1] = 2 is assigned to group 3.
Group 2 cannot be assigned any element.
Example 2:

Input: groups = [2,3,5,7], elements = [5,3,3]

Output: [-1,1,0,-1]

Explanation:

elements[1] = 3 is assigned to group 1.
elements[0] = 5 is assigned to group 2.
Groups 0 and 3 cannot be assigned any element.
Example 3:

Input: groups = [10,21,30,41], elements = [2,1]

Output: [0,1,0,1]

Explanation:

elements[0] = 2 is assigned to the groups with even values, and elements[1] = 1 is assigned to the groups with odd values.

 

Constraints:

1 <= groups.length <= 105
1 <= elements.length <= 105
1 <= groups[i] <= 105
1 <= elements[i] <= 105
Seen this question in a real interview before?
1/5
Yes
No
Accepted
8K
Submissions
44.3K
Acceptance Rate
18.1%
 * 
 * Feb 8, 2025 - 8:58:13 PM
 * Jojo 
 */
public class AssignElementsToGroupsWithConstraints {
	public int[] assignElements(int[] groups, int[] elements) {
        Map<Integer, Integer> map1 = new HashMap<>();

        for(int i=0; i<elements.length; i++){
            map1.putIfAbsent(elements[i], i);
        }

        int[] result = new int[groups.length];
        
        for(int i=0; i<groups.length; i++){
            int num = groups[i];

            int minIdx = Integer.MAX_VALUE;

            for(int k=1; k*k <= num; k++){
                if(num % k == 0){
                    if(map1.containsKey(k)){
                        minIdx = Math.min(minIdx, map1.get(k));
                    }

                    if(map1.containsKey(num / k)){
                        minIdx = Math.min(minIdx, map1.get(num / k));
                    }
                }
            }

            result[i] = minIdx == Integer.MAX_VALUE ? -1 : minIdx;
        }

        return result;
    }

    // this is TLE
    public int[] assignElements_1(int[] groups, int[] elements) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<groups.length; i++){
            map.put(i, groups[i]);
        }

        int[] arr = new int[groups.length];
        Arrays.fill(arr, -1);

        Set<Integer> visited = new HashSet<>();
        
        for(int i=0; i<elements.length; i++){
            int e = elements[i];

            if(visited.contains(e) || visited.contains(1)){
                continue;
            }

            boolean seen = false;
            for(int k=2; k*k <= e; k++){
                if(e % k == 0){
                    if(visited.contains(k) || visited.contains(e/k)){
                        seen = true;
                        break;
                    }
                }
            }

            if(seen){
                continue;
            }

            visited.add(e);

            List<Integer> keysToDel = new ArrayList<>();
            
            for(int key : map.keySet()){
                int val = map.get(key);

                if(val % e == 0){
                    arr[key] = i;
                    keysToDel.add(key);
                }
            }

            for(int key : keysToDel){
                map.remove(key);
            }
        }

        return arr;
    }
}
