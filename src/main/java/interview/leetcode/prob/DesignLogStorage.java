package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
Note:
There will be at most 300 operations of Put or Retrieve.
Year ranges from [2000,2017]. Hour ranges from [00,23].
Output for Retrieve has no order required.

 * @author jojo
 * Aug 24, 2019 9:33:15 PM
 */
public class DesignLogStorage {
	public class LogSystem {
		private String min = "2000:00:00:00:00:00", max = "2017:12:31:23:59:59";
		private TreeMap<String, List<Integer>> logs = new TreeMap<>();
		private Map<String, Integer> indexes = new HashMap<>();
		
	    public LogSystem() {
	        indexes.put("Year", 4);
	        indexes.put("Month", 7);
	        indexes.put("Day", 10);
	        indexes.put("Hour", 13);
	        indexes.put("Minute", 16);
	        indexes.put("Second", 19);
	    }
	    
	    public void put(int id, String timestamp) {
	        logs.computeIfAbsent(timestamp, v -> new ArrayList<Integer>()).add(id);
	    }
	    
	    public List<Integer> retrieve(String s, String e, String gra) {
	    	int div = indexes.get(gra);
	        String startIndex = s.substring(0, div) + min.substring(div);
	        String endIndex = e.substring(0, div) + max.substring(div);
	        
	        return logs.subMap(startIndex, true, endIndex, true).values().stream().flatMap(x -> x.stream()).collect(Collectors.toList());
	    }
	}
}
