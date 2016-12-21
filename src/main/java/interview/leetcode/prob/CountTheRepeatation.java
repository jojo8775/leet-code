package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Define S = [s,n] as the string S which consists of n connected strings s. For
 * example, ["abc", 3] ="abcabcabc".
 * 
 * On the other hand, we define that string s1 can be obtained from string s2 if
 * we can remove some characters from s2 such that it becomes s1. For example,
 * “abc” can be obtained from “abdbec” based on our definition, but it can not
 * be obtained from “acbbe”.
 * 
 * You are given two non-empty strings s1 and s2 (each at most 100 characters
 * long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the
 * strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer
 * M such that [S2,M] can be obtained from S1.
 * 
 * Example:
 * 
 * Input: s1="acb", n1=4 s2="ab", n2=2
 * 
 * Return: 2
 * 
 * @author jojo
 *
 */
public class CountTheRepeatation {
    public int getMaxRepetitions_E(String s1, int n1, String s2, int n2) {
        if (!verifyInput(s1, s2)) {
            return 0;
        }

        int totalOccurrence = 0;
        String remaining = "", str = s1;
        boolean loopFound = false;
        Map<String, Integer> cycleIndex = new HashMap<String, Integer>();
        ArrayList<Integer> occurenceCountAtEachCycle = new ArrayList<Integer>();

        for (int i = 0; i <= n1; i++) {
            StringBuilder sb = new StringBuilder();
            totalOccurrence += getRemaining(str, s2, sb);
            remaining = sb.toString();
            // if ((k=stringList.indexOf(remaining))!=-1){
            if (cycleIndex.containsKey(remaining)) {
                loopFound = true;
                break;
            }

            cycleIndex.put(remaining, i);

            occurenceCountAtEachCycle.add(totalOccurrence);

            str = remaining + s1; // append s1 to make a new string
        }

        if (!loopFound) {
            return totalOccurrence / n2; // if there is no loop
        }

        int indexBeforeLoop = cycleIndex.get(remaining);

        int occurrenceCountInLoopCycle = totalOccurrence - occurenceCountAtEachCycle.get(indexBeforeLoop);

        // represents the number of cycles required to complete a loop
        int loopCycleLength = occurenceCountAtEachCycle.size() - indexBeforeLoop;

        // resetting the total occurrence to cycle before loop happened
        totalOccurrence = occurenceCountAtEachCycle.get(indexBeforeLoop);

        // removing cycles from n1 before loop started. Adding 1 as n1 is 1
        // based and other index are 0 based
        n1 -= (indexBeforeLoop + 1);

        // total occurrence is number of occurrence before loop + number of
        // occurrence in loops for the rest of n1
        totalOccurrence += occurrenceCountInLoopCycle * (n1 / loopCycleLength);

        // if loop cycle terminated prematurely then the remaining cycles should
        // be counted as cycled without loop
        n1 %= loopCycleLength;

        totalOccurrence += occurenceCountAtEachCycle.get(indexBeforeLoop + n1)
                - occurenceCountAtEachCycle.get(indexBeforeLoop);

        return totalOccurrence / n2;
    }

    // check if s1 contains all s2 characters
    private boolean verifyInput(String s1, String s2) {
        boolean[] arr = new boolean[26];

        for (char ch : s1.toCharArray()) {
            arr[ch - 'a'] = true;
        }

        for (char ch : s2.toCharArray()) {
            if (!arr[ch - 'a']) {
                return false;
            }
        }

        return true;
    }

    // get remain string after s1 obtains s2, return the matching count
    private int getRemaining(String s1, String s2, StringBuilder remaining) {
        int count = 0, lastMatchIdx = -1, s2Idx = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(s2Idx)) {
                if (++s2Idx == s2.length()) {
                    s2Idx = 0;
                    count++;
                    lastMatchIdx = i;
                }
            }
        }
        remaining.append(s1.substring(lastMatchIdx + 1));
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new CountTheRepeatation().getMaxRepetitions_E("aabaabaab", 3, "aba", 2));
    }
}
