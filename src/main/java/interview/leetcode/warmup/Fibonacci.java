package interview.leetcode.warmup;

import java.math.BigInteger;

public class Fibonacci
{
	public static void main(String[] args){
		printFibbonaci(10);
	}
	
	private static void printFibbonaci(int count){
		int prev = -1, current = 1;
		
		for(int i=0; i<count; i++){
			int temp = current + prev;
			prev = current;
			current = temp;
			
			System.out.print(temp + ", ");
		}
	}
}
