package interview.leetcode.tushar.dynamicprograming;

public class MinimumNumberOfJumps
{
	public static void main(String[] args){
		int[] arr = {2,3,1,1,2,4,2,0,1,1};
		System.out.println(findNumberOfJumps(arr));
	}
	
	private static int findNumberOfJumps(int[] nums){
		
		int[] arr = new int[nums.length];
		int[] refArr = new int[nums.length];
		
		for(int i=1; i<nums.length; i++){
			
			arr[i] = Integer.MAX_VALUE;
			for(int j=0; j<i; j++){
				if(nums[j] >= (i-j)){
					int prevValue = arr[i];
					arr[i] = Math.min(arr[i], arr[j] + 1);
					
					if(prevValue != arr[i]){
						refArr[i] = j;
					}
				}
			}
		}

		int[] jumps = new int[arr[nums.length - 1]];
		
		int refIndex=refArr.length - 1, jumpsIndex=jumps.length-1;
		
		while(refArr[refIndex] != 0){
			jumps[jumpsIndex--] = refArr[refIndex];
			refIndex = refArr[refIndex];
		}
		
		while(jumpsIndex < jumps.length){
			System.out.print(jumps[jumpsIndex++] + ", ");
		}
		System.out.println();
		
		return arr[nums.length - 1];
	}
}
