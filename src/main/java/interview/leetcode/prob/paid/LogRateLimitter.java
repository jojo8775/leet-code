package interview.leetcode.prob.paid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Design a logger system that receive stream of messages along with its
 * timestamps, each message should be printed if and only if it is not printed
 * in the last 10 seconds.
 * 
 * Given a message and a timestamp (in seconds granularity), return true if the
 * message should be printed in the given timestamp, otherwise returns false.
 * 
 * It is possible that several messages arrive roughly at the same time.
 * 
 * Example:
 * 
 * Logger logger = new Logger();
 * 
 * // logging string "foo" at timestamp 1 logger.shouldPrintMessage(1, "foo");
 * returns true;
 * 
 * // logging string "bar" at timestamp 2 logger.shouldPrintMessage(2,"bar");
 * returns true;
 * 
 * // logging string "foo" at timestamp 3 logger.shouldPrintMessage(3,"foo");
 * returns false;
 * 
 * // logging string "bar" at timestamp 8 logger.shouldPrintMessage(8,"bar");
 * returns false;
 * 
 * // logging string "foo" at timestamp 10 logger.shouldPrintMessage(10,"foo");
 * returns false;
 * 
 * // logging string "foo" at timestamp 11 logger.shouldPrintMessage(11,"foo");
 * returns true;
 * 
 * @author jojo
 *
 */
public class LogRateLimitter {

	private Queue<Pair> queue;
	private Set<String> messageSet;

	/** Initialize your data structure here. */
	public LogRateLimitter() {
		queue = new LinkedList<Pair>();
		messageSet = new HashSet<String>();
	}

	/**
	 * Returns true if the message should be printed in the given timestamp,
	 * otherwise returns false. If this method returns false, the message will
	 * not be printed. The timestamp is in seconds granularity.
	 */
	public boolean shouldPrintMessage(int timestamp, String message) {
		while(!queue.isEmpty()){
			if(queue.peek().timeStamp + 10 < timestamp){
				messageSet.remove(queue.peek().message);
				queue.poll();
			}
			else{
				break;
			}
		}
		
		if(messageSet.add(message)){
			queue.offer(new Pair(timestamp, message));
			return true;
		}
		else{
			return false;
		}
	}

	private static class Pair {
		public final int timeStamp;
		public final String message;

		public Pair(int timeStamp, String message) {
			this.timeStamp = timeStamp;
			this.message = message;
		}
	}
}
