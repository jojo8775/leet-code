package interview.leetcode.tushar.dynamicprograming;

public class MinimumEditDistance
{
	public static void main(String[] args){
		findMinimumEditDistance("azcea","abcdef");
	}
	
	private static void findMinimumEditDistance(String str1, String str2){
		int[][] grid = new int[str1.length() + 1][str2.length() + 1];
		
		for(int i=0; i<=str1.length(); i++){
			for(int j=0; j<=str2.length(); j++){
				if(i==0){
					grid[i][j] = j;
				}
				else if(j==0){
					grid[i][j] = i;
				}
				else {
					int temp = Math.min(grid[i][j-1], grid[i-1][j]);
					temp  = Math.min(temp, grid[i-1][j-1]);
					if(str1.charAt(i-1) != str2.charAt(j-1)){
						grid[i][j] = temp + 1;
					}
					else{
						grid[i][j] = temp;
					}
				}
			}
		}
		
		System.out.println("Max number of edit: " + grid[str1.length()][str2.length()]);
		
		int y = str1.length(), x = str2.length();
		while(y>0 && x>0){
			int temp = Math.min(grid[y][x-1], grid[y-1][x]);
			temp = Math.min(temp, grid[y-1][x-1]);
			
			if((grid[y-1][x-1] == temp) && (grid[y-1][x-1] == grid[y][x])){
				x--;
				y--;
			}
			else if(grid[y-1][x-1] == temp){
				System.out.println("Modify:" + str1.charAt(y-1) + " With " + str2.charAt(x-1));
				x--;
				y--;
			}
			else if(grid[y][x-1] == temp){
				System.out.println("Delete:" + str2.charAt(x-1));
				x--;
			}
		}
	}
}
