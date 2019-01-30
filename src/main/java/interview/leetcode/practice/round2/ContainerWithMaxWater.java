package interview.leetcode.practice.round2;

public class ContainerWithMaxWater {
	public int maxArea(int[] height) {
		int left = 0, right = height.length - 1, result = 0;
		
		while(left < right) {
			int min = Math.min(height[left], height[right]);
			result = Math.max(result, min * (right - left));
			
			if(height[left] < height[right]) {
				left ++;
			}
			else {
				right --;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = {1,8,6,2,5,4,8,3,7};
		System.out.println(new ContainerWithMaxWater().maxArea(arr));
	}
}
