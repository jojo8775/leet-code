package interview.leetcode.prob;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.

Return True if the path crosses itself at any point, that is, if at any time you are on a location you've previously visited. Return False otherwise.

 

Example 1:



Input: path = "NES"
Output: false 
Explanation: Notice that the path doesn't cross any point more than once.
Example 2:



Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.
 

Constraints:

1 <= path.length <= 10^4
path will only consist of characters in {'N', 'S', 'E', 'W}
Accepted
12,407
Submissions
22,213
 * @author jojo
 * Jul 14, 2020  12:50:05 AM
 */
public class PathCrossing {
	public boolean isPathCrossing(String path) {
        int x = 0, y = 0;
        
        Set<String> set = new HashSet<>();
        set.add("0-0");
        
        for(char ch : path.toCharArray()){
            if(ch == 'N'){
                y++;
            }
            else if(ch == 'E'){
                x++;
            }
            else if(ch == 'S'){
                y--;
            }
            else{
                x--;
            }
            
            if(!set.add(x + "-" + y)){
                return true;
            }
        }
        
        return false;
    }
}
