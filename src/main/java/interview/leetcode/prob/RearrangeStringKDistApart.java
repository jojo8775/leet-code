package interview.leetcode.prob;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a non-empty string str and an integer k, rearrange the string such that
 * the same characters are at least distance k from each other.
 * 
 * All input strings are given in lowercase letters. If it is not possible to
 * rearrange the string, return an empty string "".
 * 
 * Example 1: str = "aabbcc", k = 3
 * 
 * Result: "abcabc"
 * 
 * The same letters are at least distance 3 from each other. Example 2: str =
 * "aaabc", k = 3
 * 
 * Answer: ""
 * 
 * It is not possible to rearrange the string. Example 3: str = "aaadbbcc", k =
 * 2
 * 
 * Answer: "abacabcd"
 * 
 * Another possible answer is: "abcabcda"
 * 
 * The same letters are at least distance 2 from each other.
 * 
 * @author jojo
 *
 */
public class RearrangeStringKDistApart {
    public String rearrangeString(String str, int k) {
        // if k == 0 then the input String is valid no re-arrangement is
        // required.
        if (k == 0) {
            return str;
        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>((int[] a1, int[] a2) -> {
            // if frequency is same then sort based on char
            if (a2[1] == a1[1]) {
                return a1[0] - a2[0];
            }

            // sort based on max frequency
            return a2[1] - a1[1];
        });

        // calculating the frequency of each char (Runtime is O(N))
        int[] arr = new int[26];
        for (char c : str.toCharArray()) {
            arr[(c - 'a')]++;
        }

        // populating the queue (Ideally the runtime is nlogn but since n == 26
        // so its o(1))
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                priorityQueue.offer(new int[] { i, arr[i] });
            }
        }

        // Stored characters which can be used for later use.
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean cannotBeArranged = false;

        StringBuilder result = new StringBuilder();

        // since priority queue at max can be 26 in length so iterating through
        // this is O(1)
        while (!priorityQueue.isEmpty() && !cannotBeArranged) {

            // Runtime is O(k)
            for (int i = 0; i < k; i++) {

                if (priorityQueue.isEmpty()) {
                    // if there is no charaters to be used for later use (queue
                    // is empty) then we obtained result.
                    if (!queue.isEmpty()) {
                        cannotBeArranged = true;
                    }
                    break;
                }

                int[] top = priorityQueue.poll();
                result.append((char) (top[0] + 'a'));

                if (--top[1] > 0) {
                    queue.offer(top);
                }
            }

            // at max this can be K size so runtime is klogk
            while (!queue.isEmpty()) {
                priorityQueue.offer(queue.poll());
            }
        }

        return cannotBeArranged ? "" : result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new RearrangeStringKDistApart().rearrangeString("aabbcc", 3));
        // int i = 0;
        // System.out.println((char) (i + 'a'));
    }
}
