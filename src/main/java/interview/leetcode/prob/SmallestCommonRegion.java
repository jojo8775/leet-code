package interview.leetcode.prob;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * You are given some lists of regions where the first region of each list directly contains all other regions in that list.

If a region x contains a region y directly, and region y contains region z directly, then region x is said to contain region z indirectly. Note that region x also indirectly contains all regions indirectly containd in y.

Naturally, if a region x contains (either directly or indirectly) another region y, then x is bigger than or equal to y in size. Also, by definition, a region x contains itself.

Given two regions: region1 and region2, return the smallest region that contains both of them.

It is guaranteed the smallest region exists.

 

Example 1:

Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
Output: "North America"
Example 2:

Input: regions = [["Earth", "North America", "South America"],["North America", "United States", "Canada"],["United States", "New York", "Boston"],["Canada", "Ontario", "Quebec"],["South America", "Brazil"]], region1 = "Canada", region2 = "South America"
Output: "Earth"
 

Constraints:

2 <= regions.length <= 104
2 <= regions[i].length <= 20
1 <= regions[i][j].length, region1.length, region2.length <= 20
region1 != region2
regions[i][j], region1, and region2 consist of English letters.
The input is generated such that there exists a region which contains all the other regions, either directly or indirectly.
A region cannot be directly contained in more than one region.
 * 
 * chiranjeebnandy
 * Sep 17, 2025  2025  10:27:52 PM
 */

public class SmallestCommonRegion {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> map = new HashMap<>();

        for(List<String> entry : regions){
            // given in the question first region directly contains all other regions 
            String parent = entry.get(0);
            for(int i=1; i<entry.size(); i++){
                map.put(entry.get(i), parent);
            }
        }

        List<String> path1 = getPath(region1, map);
        List<String> path2 = getPath(region2, map);

        int i=0, j=0;
        String lca = "";

        // terminating when the ancentor is not common anymore.
        while(i < path1.size() && j < path2.size() && path1.get(i).equals(path2.get(j))){
            lca = path1.get(i);
            j++;
            i++;
        }

        return lca;
    }

    private List<String> getPath(String str, Map<String, String> map){
        List<String> list = new LinkedList<>();
        list.add(str);

        while(map.containsKey(str)){
            list.add(0, map.get(str));
            str = map.get(str);
        }

        return list;
    }
}
