package interview.leetcode.tushar.assorted;

public class BitComplement
{
	public static void main(String[] args){
		int num = 8; 
		
		int twosComplement = findTwosComplement(num);
		twosComplement = num & twosComplement;
		num = num - twosComplement;
		
		System.out.println(num);
	}
	
	private static int findTwosComplement(int num){
		num = ~num;
		num +=1;
		
		return num;
	}
}
