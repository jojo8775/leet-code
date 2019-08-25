package interview.leetcode.prob;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 
On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.

We store logs in timestamp order that describe when a function is entered or exited.

Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.

A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.

The CPU is single threaded which means that only one function is being executed at a given time unit.

Return the exclusive time of each function, sorted by their function id.

 

Example 1:



Input:
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3, 4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time. 
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
 

Note:

1 <= n <= 100
Two functions won't start or end at the same time.
Functions will always log when they exit.
 * @author jojo
 * Aug 25, 2019 1:45:41 AM
 */
public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
		Stack<Log> stack = new Stack<>();
		int[] result = new int[n];
		for (String content : logs) {
			Log log = new Log(content);
			if (log.isStart) {
				stack.push(log);
			} 
			else {
				Log top = stack.pop();
				result[top.id] += (log.time - top.time + 1 - top.subDuration);
				if (!stack.isEmpty()) {
					stack.peek().subDuration += (log.time - top.time + 1);
				}
			}
		}

		return result;
	}

	public static class Log {
		public int id;
		public boolean isStart;
		public int time;
		public int subDuration;

		public Log(String content) {
			String[] strs = content.split(":");
			id = Integer.valueOf(strs[0]);
			isStart = strs[1].equals("start");
			time = Integer.valueOf(strs[2]);
			subDuration = 0;
		}
	}

	public static void main(String[] args) {
		int[] result = new ExclusiveTimeOfFunctions().exclusiveTime(2,
				Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"));

		for (int n : result) {
			System.out.println(n);
		}
	}
}
