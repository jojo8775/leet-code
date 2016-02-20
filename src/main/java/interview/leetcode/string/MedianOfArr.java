package interview.leetcode.string;

public class MedianOfArr
{
	public static void main(String[] args)
	{
		MedianOfArr prob = new MedianOfArr();

		int[] a1 = {};
		int[] a2 = { 2, 3 };

		System.out.println(prob.findMedianSortedArrays(a1, a2));
	}

	/**
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)).
	 * 
	 * This is m+n runtime. This can be done in a faster way.
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2)
	{
		int[] resultArr = new int[nums1.length + nums2.length];

		int nums1Count = 0, nums2Count = 0, resultCount = 0;

		while (nums1Count < nums1.length)
		{
			if (nums2Count < nums2.length && nums1[nums1Count] > nums2[nums2Count])
			{
				resultArr[resultCount++] = nums2[nums2Count++];
			}
			else
			{
				resultArr[resultCount++] = nums1[nums1Count++];
			}
		}

		while (nums2Count < nums2.length)
		{
			resultArr[resultCount++] = nums2[nums2Count++];
		}

		if (resultArr.length == 1)
		{
			return resultArr[0];
		}

		if (resultArr.length % 2 == 1)
		{
			return (double) resultArr[(resultArr.length - 1) / 2];
		}
		else
		{
			return (double) (resultArr[(resultArr.length - 1) / 2] + resultArr[((resultArr.length - 1) / 2) + 1]) / 2;
		}
	}
}
