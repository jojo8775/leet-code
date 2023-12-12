package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.

Return the string that represents the kth largest integer in nums.

Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.

 

Example 1:

Input: nums = ["3","6","7","10"], k = 4
Output: "3"
Explanation:
The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
The 4th largest integer in nums is "3".
Example 2:

Input: nums = ["2","21","12","1"], k = 3
Output: "2"
Explanation:
The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
The 3rd largest integer in nums is "2".
Example 3:

Input: nums = ["0","0"], k = 2
Output: "0"
Explanation:
The numbers in nums sorted in non-decreasing order are ["0","0"].
The 2nd largest integer in nums is "0".
 

Constraints:

1 <= k <= nums.length <= 104
1 <= nums[i].length <= 100
nums[i] consists of only digits.
nums[i] will not have any leading zeros.
Accepted
56,565
Submissions
126,094
 * @author jojo
 * Dec. 4, 2023 11:30:20 p.m.
 */
public class FindKthLargestIntegerInArray {
	public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            int l1 = a.length(), l2 = b.length();
            if(l1 != l2){
                return l1 - l2;
            }
            
            return a.compareTo(b);
        });
        
        for(int i=0; i<nums.length; i++){
            String n = nums[i];
            pq.offer(n);
            
            if(i >= k){
                pq.poll();
            }
        }
        
        return pq.peek();
    }
    
    
    
    public String kthLargestNumber_qs(String[] nums, int k) {
        int beg = 0, end = nums.length - 1;
        
        while(beg <= end){
            int idx = quickSelect(nums, beg, end);
            
            if(idx == k-1){
                return nums[idx];
            }
            
            if(idx > k - 1){
                end = idx - 1;
            }
            else{
                beg = idx + 1;
            }
        }
        
        return "";
    }
    
    private int quickSelect(String[] nums, int beg, int end){
        int pivotIdx = getRandom(beg, end);
        
        String pivotVal = nums[pivotIdx];
        
        swap(nums, pivotIdx, end);
        pivotIdx = end;
        end--;
        
        while(beg <= end){
            int val = compareStr(nums[beg], pivotVal);
            //System.out.println("compare-: s1=" + nums[beg] + "   s2=" + pivotVal + "   val=" + val);
            if(val < 0){
                swap(nums, beg, end);
                end--;
            }
            else{
                beg++;
            }
        }
        
        swap(nums, beg, pivotIdx);
        
        return beg;
    }
    
    private int getRandom(int beg, int end){
        return beg + (int)(Math.random() * (end - beg + 1));
    }
    
    private int compareStr(String s1, String s2){
        int l1 = s1.length(), l2 = s2.length();
        if(l1 != l2){
            return l1 - l2;
        }
        
        return s1.compareTo(s2);
    }
    
    private void swap(String[] nums, int i, int j){
        if(i == j){
            return;
        }
        
        String temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
