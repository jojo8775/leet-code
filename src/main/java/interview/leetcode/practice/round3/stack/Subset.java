package interview.leetcode.practice.round3.stack;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public List<List<Integer>> findSubsets(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();

        int prevIdx = 0;
        result.add(new ArrayList<Integer>());
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] != arr[i - 1]) {
                prevIdx = 0;
            }

            int len = result.size();
            for (int j = prevIdx; j < len; j++) {
                List<Integer> entry = new ArrayList<>(result.get(j));
                entry.add(arr[i]);
                result.add(entry);
            }

            prevIdx = len;
        }

        return result;
    }
    
    public static void main(String[] args){
        List<List<Integer>> result = new Subset().findSubsets(new int[] {1,2,2});
        for(List<Integer> list : result){
            for(int n : list){
                System.out.print(n + ", ");
            }
            System.out.println();
        }
    }
}
