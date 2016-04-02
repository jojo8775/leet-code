package interview.leetcode.concepts;

/**
 * Implementation of Kandan's algorithm to find the max array subset in linear time. 
 * 
 * @author jojo
 *
 */
public class KandansAlgorith
{
	public static void main(String[] args){
		int[] nums = {-1,-2,3,4,-5,6,-1, 2};
		findMax(nums);
	}
	
	private static int findMax(int[] arr){
		int currentMaxBegin  = 0, currentMaxEnd = 0, currentMaxValue = arr[0];
		int maxBegin = 0, maxEnd = 0, maxValue = currentMaxValue;
		
		for(int i=1; i<arr.length; i++){
			if(currentMaxValue < 0){
				currentMaxValue = arr[i];
				currentMaxBegin = i;
				currentMaxEnd = i;
			}
			else{
				currentMaxValue = currentMaxValue + arr[i];
				currentMaxEnd = i;
			}
			
			if(currentMaxValue > maxValue){
				maxValue = currentMaxValue;
				maxBegin = currentMaxBegin;
				maxEnd = currentMaxEnd;
			}
		}
		
		System.out.println("Max Value: " + maxValue);
		System.out.println("Begin Index: " + (maxBegin + 1));
		System.out.println("End Index: " + (maxEnd + 1));
		
		return maxValue;
	}
}
