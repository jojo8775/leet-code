package interview.leetcode.tushar.dynamicprograming;

public class LongestPalindromSubstring
{
	public static void main(String[] args){
		System.out.println(findLongestPalindrom("agabdba"));;
	}
	
	private static String findLongestPalindrom(String s){
		
		int[][] arr = new int[s.length()][s.length()];
		
		for(int i=0; i<s.length(); i++){
			arr[i][i] = 1;
		}
		
		for(int i=1; i<s.length(); i++){
			int k = 0;
			for(int j=i; j<s.length(); j++){
				if(s.charAt(k) == s.charAt(j)){
					if(j-k == 1){
						arr[k][j] = 2;
					}
					else{
						if(s.charAt(k+1) == s.charAt(j-1)){
							arr[k][j] = 2 + arr[k+1][j-1];
						}
						else{
							arr[k][j] = Math.max(arr[k+1][j], arr[k][j-1]);
						}
					}
				}
				else{
					arr[k][j] = Math.max(arr[k+1][j], arr[k][j-1]);
				}
				k++;
			}
		}
		
		char[] cArr = new char[arr[0][arr.length - 1]];
		int x = s.length() - 1;
		while(x>0 && arr[0][x] == arr[0][x-1]){
			x--;
		}
		
		int count = arr[0][x];
		while(count > 0){
			cArr[--count] = s.charAt(x--);
		}
		
		printBoard(arr);
		return String.valueOf(cArr);
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
