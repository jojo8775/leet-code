package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]

 * @author jojo
 *
 */
public class FindKPairsWithSmallestSums {
	public List<int[]> kSmallestPairs_old(int[] nums1, int[] nums2, int k) {
		PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> a[0] + a[1] - b[0] - b[1]);
		List<int[]> res = new ArrayList<int[]>();

		if (nums1.length == 0 || nums2.length == 0 || k == 0) {
			return res;
		}

		for (int i = 0; i < nums1.length && i < k; i++) {
			que.offer(new int[] { nums1[i], nums2[0], 0 });
		}
		
		while (k-- > 0 && !que.isEmpty()) {
			int[] cur = que.poll();
			res.add(new int[] { cur[0], cur[1] });
			if (cur[2] == nums2.length - 1){
				continue;
			}
			
			que.offer(new int[] { cur[0], nums2[cur[2] + 1], cur[2] + 1 });
		}
		return res;
	}
	
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<int[]>();
        
        if(nums1.length == 0 || nums2.length == 0 || k == 0){
            return result;
        }
        
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a,b) -> a[0] + a[1] - b[0] - b[1]);
        
        for(int i=0; i<nums1.length; i++){
            heap.offer(new int[] {nums1[i], nums2[0], 0});
        }
        
        while(k-- > 0 && !heap.isEmpty()){
            int[] top = heap.poll();
            result.add(new int[]{top[0], top[1]});
            
            if(top[2] < nums2.length - 1){
                heap.offer(new int[] {top[0], nums2[top[2] + 1], top[2] + 1});
            }
        }
        
        return result;
    }
	
	public static void main(String[] args){
		List<int[]> result = new FindKPairsWithSmallestSums().kSmallestPairs(new int[]{1,7,11}, new int[] {2,4,6}, 3);
		
		for(int[] a : result){
			for(int i : a){
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}
}
