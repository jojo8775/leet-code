package interview.leetcode.prob;

/**
 * (This problem is an interactive problem.)

You may recall that an array A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.

You can't access the mountain array directly.  You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

 

Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
Example 2:

Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.
 

Constraints:

3 <= mountain_arr.length() <= 10000
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9
Accepted
24,396
Submissions
67,571
 * @author jojo
 * Mar 13, 2021  7:11:18 PM
 */
public class FindInMountainArray {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        int peakIdx = findPeakIdx(mountainArr, 0, len - 1);
        
        int peak = mountainArr.get(peakIdx);
        System.out.println("PeakIdx: " + peakIdx + "  PeakVal: " + peak);
        
        if(peak == target){
            return peakIdx;
        }
        
        int leftIdx = binarySearch(mountainArr, 0, peakIdx - 1, target);
        
        return leftIdx == -1 ? reverseBinarySearch(mountainArr, peakIdx + 1, len - 1, target) : leftIdx;
    }
    
    private int findPeakIdx(MountainArray mountainArr, int beg, int end){
        while(beg < end){
            int mid = beg + (end - beg)/2;
            
            if(mountainArr.get(mid) <= mountainArr.get(mid + 1)){
                beg = mid + 1;
            }
            else{
                end = mid;
            }
        }
        
        return end;
    }
    
    private int binarySearch(MountainArray mountainArr, int beg, int end, int target){
        System.out.println("BS : beg: " + beg + "     end: " + end);
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            int val = mountainArr.get(mid);
            
            if(val == target){
                return mid;
            }
            
            if(val < target){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return -1;
    }
    
    private int reverseBinarySearch(MountainArray mountainArr, int beg, int end, int target){
        System.out.println("RBS : beg: " + beg + "     end: " + end);
        while(beg <= end){
            int mid = beg + (end - beg)/2;
            
            int val = mountainArr.get(mid);
            
            if(val == target){
                return mid;
            }
            
            if(val > target){
                beg = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        
        return -1;
    }
    
    private interface MountainArray{
    	int length();
    	int get(int idx);
    }
}
