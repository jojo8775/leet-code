package interview.leetcode.tushar.dynamicprograming;

public class FindPatternWithWildCharacter
{
	public static void main(String[] args){
		System.out.println(patternMatch("abcde", "a*e"));
		System.out.println(patternMatch("abcde", "a*c?e"));
	}
	
	private static boolean patternMatch(String text, String pattern){
		boolean[][] grid = new boolean[text.length() + 1][pattern.length() + 1];
		
		grid[0][0] = true;
		
		for(int i=1; i<grid.length; i++){
			grid[i][0] = false;
		}
		
		for(int i=1; i<grid[0].length; i++){
			grid[0][i] = pattern.charAt(i-1) == '*' ? grid[0][i-1] : false;
		}
		
		for(int i=1; i<grid.length; i++){
			for(int j=1; j<grid[0].length; j++){
				if(text.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?'){
					grid[i][j] = grid[i-1][j-1];
				}
				else if(pattern.charAt(j-1) == '*'){
					grid[i][j] = grid[i-1][j] || grid[i][j-1];
				}
				else{
					grid[i][j] = false;
				}
			}
		}
		
		return grid[text.length()][pattern.length()];
	}
}