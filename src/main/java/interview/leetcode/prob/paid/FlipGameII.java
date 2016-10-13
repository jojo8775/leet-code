package interview.leetcode.prob.paid;

import java.util.HashMap;
import java.util.Map;

/**
 * You are playing the following Flip Game with your friend: Given a string that
 * contains only these two characters: + and -, you and your friend take turns
 * to flip two consecutive "++" into "--". The game ends when a person can no
 * longer make a move and therefore the other person will be the winner.
 * 
 * Write a function to determine if the starting player can guarantee a win.
 * 
 * For example, given s = "++++", return true. The starting player can guarantee
 * a win by flipping the middle "++" to become "+--+".
 * 
 * Follow up: Derive your algorithm's runtime complexity.
 * 
 * Show Company Tags Show Tags Show Similar Problems
 * 
 * @author jojo
 *
 */
public class FlipGameII {
	public boolean canWin(String s) {
		return movePossible(s.toCharArray(), new HashMap<String, Boolean>());
	}

	// think about if next move is possible in this game.
	private boolean movePossible(char[] cArr, Map<String, Boolean> map) {
		// this map acts as a memorization to prevent re-processing previous
		// computed string
		if (map.containsKey(String.valueOf(cArr))) {
			return map.get(String.valueOf(cArr));
		}

		for (int i = 1; i < cArr.length; i++) {
			if (cArr[i] == '+' && cArr[i - 1] == '+') {
				cArr[i] = '-';
				cArr[i - 1] = '-';

				boolean nextMove = movePossible(cArr, map);
				map.put(String.valueOf(cArr), nextMove);

				// back tracking
				cArr[i] = '+';
				cArr[i - 1] = '+';

				// if next move is not possible then this current player will
				// win
				if (!nextMove) {
					return true;
				}
			}
		}

		// there is no possible move available so the current player lost
		map.put(String.valueOf(cArr), false);
		return false;
	}
}
