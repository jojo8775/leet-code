package interview.leetcode.tushar.dynamicprograming;

public class LongestPalindromSubSequence
{
	public static void main(String[] args){
		findLongestPalindromSubSequence("aagbdbaca");
	}
	
	private static void findLongestPalindromSubSequence(String str){
		int[][] grid = new int[str.length()][str.length()];
		
		for(int i=0; i<str.length(); i++){
			grid[i][i] = 1;
		}
		
		for(int i=1; i<str.length(); i++){
			int k = 0;
			for(int j=i; j<str.length(); j++){
				if(str.charAt(k) == str.charAt(j)){
					if(j-k == 1){
						grid[k][j] = 2;
					}
					else{
						grid[k][j] = 2 + grid[k+1][j-1];
 					}
				}
				else{
					grid[k][j] = Math.max(grid[k][j-1], grid[k+1][j]);
				}
				
				k++;
			}
		}
		
		System.out.println(grid[0][str.length() - 1]);
		
		char[] ch = new char[grid[0][str.length() - 1]];
		int x=grid.length-1, y=0;
		int charIndex = 0;
		while(grid[y][x] != 0){
			if(x>0 && grid[y][x] == grid[y][x-1]){
			}
			else{
				ch[charIndex] = str.charAt(x);
				ch[ch.length - charIndex - 1] = str.charAt(x);
				charIndex ++;
				y++;
			}
			
			x--;
		}
		
		System.out.println(String.valueOf(ch));
		printBoard(grid);
	}
	
	private static void printBoard(int[][] arr){
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[0].length; j++){
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
