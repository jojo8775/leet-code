package interview.leetcode.tushar.dynamicprograming;

public class MaxRectriangleSubMatrixArea
{
	public static void main(String[] args){
		int[][] grid = new int[4][5];
		grid[0] = getArr(2,1,-3,-4,5);
		grid[1] = getArr(0,6,3,4,1);
		grid[2] = getArr(2,-2,-1,4,-5);
		grid[3] = getArr(-3,3,1,0,3);
		
		findMaxArea(grid);
	}
	
	private static int[] getArr(int ... i){
		return i;
	}
	
	private static void findMaxArea(int[][] grid){
		
		int maxUp = 0,maxDown = 0,maxArea = Integer.MIN_VALUE,maxLeft = 0,maxRight = 0;
		
		int[] areaHolder = new int[grid.length];
		
		for(int i=0; i<grid[0].length; i++){
			for(int j=i; j<grid[0].length; j++){
				for(int k=0; k<grid.length; k++){
					if(i==j){
						areaHolder[k] = grid[k][j];
					}
					else{
						areaHolder[k] += grid[k][j];
					}
				}
				
				int[] result = findMaxSubSet(areaHolder);
				if(maxArea < result[2]){
					maxArea = result[2];
					maxUp = result[0];
					maxDown = result[1];
					maxLeft = i;
					maxRight = j;
				}
			}
		}
		
		System.out.println("Max Area: " + maxArea);
		System.out.println("Co-ordinates: " + "(" + maxUp + "," + maxLeft + ") (" + maxDown + "," + maxRight + ")");
	}
	
	private static int[] findMaxSubSet(int[]  arr){
		
		int maxBeg = 0, maxEnd = 0, maxSum = Integer.MIN_VALUE, curBeg = 0, curEnd = 0, curMax = Integer.MIN_VALUE;
		
		for(int i=0 ; i<arr.length; i++){
			if(curMax < 0){
				curMax = arr[i];
				curBeg = i;
				curEnd = i;
			}
			else{
				curMax += arr[i];
				curEnd = i;
			}
			
			if(curMax > maxSum){
				maxSum = curMax;
				maxBeg = curBeg;
				maxEnd = curEnd;
			}
		}
		
		int[] result = {maxBeg,maxEnd, maxSum};
		return result;
	}
}
