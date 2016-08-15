package interview.leetcode.prob;

/**
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit
 * once, otherwise return -1.
 * 
 * @author jojo
 *
 */
public class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start = 0; // assumed start
		int end = gas.length;

		while (start < end) {
			int i = start;
			// if this condition is true then car is draining gas and it cannot
			// be the start
			if (gas[i] - cost[i] < 0) {
				start = i + 1;
				continue;
			}

			int sum = 0;
			while (i < (end + start)) {
				int pos = i % end;
				sum = sum + (gas[pos] - cost[pos]);
				if (sum < 0) {
					// chose the start wrongl
					start = i + 1;
					break;
				} else {
					// found start;
					if (start == (i + 1) % end) {
						return start;
					}
				}

				i++;
			}
		}

		return -1;
	}
}
