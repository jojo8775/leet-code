package interview.leetcode.tushar.dynamicprograming;

public class BinaryNumbersWithoutWithoutConsecutiveOnes
{
	public static void main(String[] args){
		System.out.println(findNumbers(4));
	}
	
	private static int findNumbers(int n){
		
		if(n==1){
			return 2;
		}
		
		if(n == 2){
			return 3;
		}
		
		int current = 3, prev = 2;
		
		for(int i=3; i<=n; i++){
			int temp = current;
			current = current + prev;
			prev = temp;
		}
		
		return current;
	}
}
