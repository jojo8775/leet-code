package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Temp {
	private List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
        Collections.sort(a, (i,j) -> i[1] - j[1]);
        Collections.sort(b, (i,j) -> i[1] - j[1]);
        List<int[]> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        int m = a.size();
        int n = b.size();
        int i =0;
        int j =n-1;
        while(i<m && j >= 0) {
            int sum = a.get(i)[1] + b.get(j)[1];
            if(sum > target) {
                 --j;
            } else {
                if(max <= sum) {
                    if(max < sum) {
                        max = sum;
                        result.clear();
                    }
                    result.add(new int[]{a.get(i)[0], b.get(j)[0]});
                    int index = j-1;
                    while(index >=0 && b.get(index)[1] == b.get(index+1)[1]) {
                         result.add(new int[]{a.get(i)[0], b.get(index--)[0]});
                    }
                }
                ++i;
            }
        }
        return result;
    }
	
	public static void main(String[] args) {
		int[][] a1 = {{1, 2}, {2, 4}, {3, 6}};
		int[][]	b1 = {{1, 2}};
		int	target1 = 7;
		
		Temp tt = new Temp();
		
		for(int[] ele : tt.getPairs(tt.blaa(a1), tt.blaa(b1), target1)) {
			System.out.println(Arrays.toString(ele));
		}
		System.out.println("----------------------------");
		int[][] a2 = {{1, 3}, {2, 5}, {3, 7}, {4, 10}};
		int[][] b2 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
		int target2 = 10;
		for(int[] ele : tt.getPairs(tt.blaa(a2), tt.blaa(b2), target2)) {
			System.out.println(Arrays.toString(ele));
		}
		System.out.println("----------------------------");
		int[][] a3 =  {{1, 3}, {2, 5}, {3,7},{4,10}};
		int[][] b3 =  {{1, 2}, {2, 3}, {3,4}, {4,5}};
		int target3 = 10;
		for(int[] ele : tt.getPairs(tt.blaa(a3), tt.blaa(b3), target3)) {
			System.out.println(Arrays.toString(ele));
		}
	}
	
	private List<int[]> blaa(int[][] a){
		List<int[]> l = new ArrayList<>();
		for(int[] n : a) {
			l.add(n);
		}
		
		return l;
	}
}
