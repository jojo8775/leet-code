package interview.leetcode.tushar.dynamicprograming;

public class MaximumSumIncreasingSequence
{
	public static void main(String[] args){
		int[] nums = {4,6,1,3,8,4,6};
		System.out.println(findMaxSum(nums));
	}
	
	private static int findMaxSum(int[] nums){
		
		int[] arr = new int[nums.length];
		int[] refArr = new int[nums.length];
		
		int maxValue = 0, maxIndex = 0, seqCount=0;
		arr[0] = nums[0];
		refArr[0] = -1;
		
		for(int i=1; i<nums.length; i++){
			refArr[i] = -1;
			for(int j=0; j<i; j++){
				if(nums[j] < nums[i]){
					arr[i] = nums[i] + arr[j];
					if(maxValue < arr[i]){
						maxValue = arr[i];
						maxIndex = i;
						refArr[i] = j;
						seqCount++;
					}
				}
			}
		}
		
		int[] seq = new int[seqCount];
		
		while(maxIndex != -1){
			seq[--seqCount] = maxIndex;;
			maxIndex = refArr[maxIndex];
		}
		
		while(seqCount < seq.length){
			System.out.print(seq[seqCount++] + ", ");
		}
		
		System.out.println();
		
		return maxValue;
	}
}
