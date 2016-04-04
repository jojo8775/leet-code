package interview.leetcode.tushar.dynamicprograming;

import java.util.ArrayList;
import java.util.List;

public class TextJustification
{
	public static void main(String[] args){
		String[] strArr = new String[5];
		strArr[0] = "tushar";
		strArr[1] = "roy";
		strArr[2] = "loves";
		strArr[3] = "to";
		strArr[4] = "code";
		
		List<String> result = textJustification(strArr, 10);
		for(String str : result){
			System.out.println(str);
		}
	}
	
	private static List<String> textJustification(String[] strArr, int lineLength){
		int[][] grid = new int[strArr.length][strArr.length];
		
		for(int i=0; i<strArr.length; i++){
			grid[i][i] = (int) Math.pow((lineLength - strArr[i].length()), 2);
		}
		
		for(int i=1; i<grid.length; i++){
			int k=0;
			for(int j=i; j<grid.length; j++){
				int blankSpaceCount = getBlanckSpaceCount(k,j, lineLength, strArr);
				if(blankSpaceCount < 0){
					grid[k++][j] = Integer.MAX_VALUE;
				}
				else{
					grid[k++][j] = (int) Math.pow(blankSpaceCount, 2);
				}
			}
		}
		
		int[] arr1 = new int[grid.length];
		int[] arr2 = new int[grid.length];
		
		for(int i=arr1.length - 1; i>=0; i--){
			arr1[i] = Integer.MAX_VALUE;
			for(int j=arr1.length - 1; j>=i; j--){
				int sum = Integer.MAX_VALUE;
				if(j == arr2.length - 1){
					sum = grid[i][j];
				}
				else if(grid[i][j] != Integer.MAX_VALUE){
					sum = grid[i][j] + arr1[j+1];
				}
				
				if(arr1[i] > sum){
					arr1[i] = sum;
					arr2[i] = j + 1;
				}
			}
		}
		
		int refIndex = 0;
		
		List<String> result = new ArrayList<String>();
		while(refIndex < arr2.length){
			int beg = refIndex;
			refIndex = arr2[refIndex];
			
			StringBuilder sb = new StringBuilder();
			sb.append(strArr[beg++]);
			while(beg < refIndex){
				sb.append(" ").append(strArr[beg++]);
			}
			result.add(sb.toString());
		}
		
		return result;
	}
	
	private static int getBlanckSpaceCount(int begin, int end, int lineLength, String[] strArr){
		lineLength -= strArr[begin++].length();
		while(lineLength >= 0 && begin <= end){
			lineLength -= 1;
			lineLength -= strArr[begin++].length();
		}
		
		if(lineLength >= 0){
			return lineLength;
		}
		
		return -1;
	}
}
