/**
 *
 */
package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.

A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:

age[y] <= 0.5 * age[x] + 7
age[y] > age[x]
age[y] > 100 && age[x] < 100
Otherwise, x will send a friend request to y.

Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.

Return the total number of friend requests made.

 

Example 1:

Input: ages = [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: ages = [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: ages = [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 

Constraints:

n == ages.length
1 <= n <= 2 * 104
1 <= ages[i] <= 120
Accepted
77,362
Submissions
165,614
 * Author: jojo
 * Dec. 30, 2023 - 4:31:14 p.m.
 */
public class FriendsOfAppropriateAges {
	public int numFriendRequests(int[] ages) {
        // creating a map because there are 2000 ages and only 120 unique age 
        Map<Integer, Integer> ageMap = new HashMap<>();
        
        for(int age : ages){
            ageMap.put(age, ageMap.getOrDefault(age, 0) + 1);
        }
        
        int result = 0;
        
        for(int a : ageMap.keySet()){
            for(int b : ageMap.keySet()){
                if(request(a,b)){
                    // if there are 2 count of a age and 3 count of b age then total friend connection will be 2 * 3 = 6
                    // if a and b are same then same person cannot sent self request 
                    // so deducting 1
                    result += (ageMap.get(a) * (ageMap.get(b) - (a == b ? 1 : 0)));
                }
            }
        }
        
        return result;
    }
    
    // this relation is copied from the problem statement. 
    private boolean request(int a, int b){
        return !(b <= (0.5 * a) + 7 || b > a || b > 100 && a < 100);
    }
}
