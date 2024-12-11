package interview.leetcode.prob;

/**
 * You are given an integer mountain array arr of length n where the values increase to a peak element and then decrease.

Return the index of the peak element.

Your task is to solve it in O(log(n)) time complexity.

 

Example 1:

Input: arr = [0,1,0]

Output: 1

Example 2:

Input: arr = [0,2,1,0]

Output: 1

Example 3:

Input: arr = [0,10,5,2]

Output: 1

 

Constraints:

3 <= arr.length <= 105
0 <= arr[i] <= 106
arr is guaranteed to be a mountain array.
Accepted
910,561
Submissions
1,338,619
 * 
 * 
 * Dec 1, 2024 - 1:19:05 PM
 * Jojo 
 */
public class PeakIndexInAMountainArray {
	public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1, mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (arr[mid] < arr[mid + 1])
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
    
    public int peakIndexInMountainArray_1(int[] A) {
        int i=A.length/2;
        while((i!=0)&&(i!=A.length))
        {
            if((A[i-1]<A[i])&&(A[i+1]<A[i])) return i;
            else if(A[i-1]>A[i]) i--;
            else i++;
        }
        return 0;
    }
}
