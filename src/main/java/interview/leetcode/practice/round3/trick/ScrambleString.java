package interview.leetcode.practice.round3.trick;

import java.util.ArrayList;
import java.util.List;

public class ScrambleString {
    public List<Integer> greycode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);

        for (int i = 0; i < n; i++) {
            int len = result.size();
            for (int j = len - 1; j >= 0; j--) {
                result.add(result.get(j) | (1 << i));
            }
        }

        return result;
    }

    public static void main(String[] args){
        List<Integer> result = new ScrambleString().greycode(4);
        for(int i : result){
            System.out.println(Integer.toBinaryString(i));
        }
    }
}
