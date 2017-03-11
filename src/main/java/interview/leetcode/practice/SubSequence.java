package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubSequence {
    public List<String> getSubSequenceString(String s1, List<String> inputStream) {
        List<String> result = new ArrayList<String>();
        char[] cArr = s1.toCharArray();
        for(String s2 : inputStream){
            if(isSubSequence(cArr, s2)){
                result.add(s2);
            }
        }
        
        return result;
    }

    private boolean isSubSequence(char[] s1, String s2) {
        Map<Character, List<Integer>> cache = new HashMap<>();

        char[] cArr = s2.toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            cache.getOrDefault(cArr[i], new ArrayList<Integer>()).add(i);
        }

        int prevPos = -1;
        for (int i = 0; i < s1.length; i++) {
            int nextPosition = binarySearch(prevPos, cache.get(s1[i]), 0, cArr.length);

            if (nextPosition == -1) {
                return false;
            }

            prevPos = nextPosition;
        }

        return true;
    }

    private int binarySearch(int target, List<Integer> list, int beg, int end) {
        if (list == null) {
            return -1;
        }

        while (beg < end) {
            int mid = beg + (end - beg) / 2;

            if (mid > beg) {
                beg = mid + 1;
            } else {
                end = mid;
            }
        }

        return beg == end ? -1 : beg;
    }
}
