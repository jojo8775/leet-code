package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.

 

Example 1:

Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: 
The tuples in this example are:
["joe", 1, "home"]
["joe", 2, "about"]
["joe", 3, "career"]
["james", 4, "home"]
["james", 5, "cart"]
["james", 6, "maps"]
["james", 7, "home"]
["mary", 8, "home"]
["mary", 9, "about"]
["mary", 10, "career"]
The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 

Note:

3 <= N = username.length = timestamp.length = website.length <= 50
1 <= username[i].length <= 10
0 <= timestamp[i] <= 10^9
1 <= website[i].length <= 10
Both username[i] and website[i] contain only lowercase characters.
It is guaranteed that there is at least one user who visited at least 3 websites.
No user visits two websites at the same time.
 * @author jojo
 * Nov 25, 2019 10:42:06 PM
 */
public class AnalyzeUserWebsiteVisitPattern {
	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		List<List<String>> tuples = new ArrayList<>();
		
		for(int i=0; i<username.length; i++) {
			tuples.add(Arrays.asList(username[i], String.valueOf(timestamp[i]), website[i]));
		}
		
		// sorting based on the time stamp
		Collections.sort(tuples, (a, b) -> Integer.valueOf(a.get(1)) - Integer.valueOf(b.get(1)));
		
		Map<String, List<String>> userSiteMap = new HashMap<>();
		for(int i=0; i < tuples.size(); i++) {
			List<String> entry = tuples.get(i);
			
			List<String> values = userSiteMap.get(entry.get(0));
			if(values == null) {
				values = new ArrayList<>();
				userSiteMap.put(entry.get(0), values);
			}
			
			values.add(entry.get(2));
		}
		
		int maxSize = 0; 
		String maxPattern = "";
		
		Map<String, Integer> patternFreq = new HashMap<>();
		for(String key : userSiteMap.keySet()) {
			List<String> sites = userSiteMap.get(key);
			Set<String> visited = new HashSet<>();
			if(sites.size() < 3) {
				continue;
			}
			
			for(int i=0; i<sites.size() - 2; i++) {
				for(int j=i+1; j<sites.size() - 1; j++) {
					for(int k=j+1; k<sites.size(); k++) {
						String pattern = sites.get(i) + "," + sites.get(j) + "," + sites.get(k);
						if(visited.add(pattern)) {
							patternFreq.put(pattern, patternFreq.getOrDefault(pattern, 0) + 1);
							
							if(patternFreq.get(pattern) > maxSize) {
								maxSize = patternFreq.get(pattern);
								maxPattern = pattern;
							}
							else if(patternFreq.get(pattern) == maxSize && maxPattern.compareTo(pattern) > 0) {
								maxPattern = pattern;
							}
						}
					}
				}
			}
		}
		
		return Arrays.asList(maxPattern.split(","));
	}
}
