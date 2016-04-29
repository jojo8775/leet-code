package interview.leetcode.hackersrank;

import java.math.BigInteger;

public class Fibbonacci
{
	public static void main(String[] args){
		findNumber(BigInteger.valueOf(0),BigInteger.valueOf(1), 10);
	}
	
	// T(n+2) = (T(n+2))^2 + T(N)
	private static void findNumber(BigInteger a, BigInteger b, int n){
		for(int i=2; i<n; i++){
			BigInteger temp = b;
			b = b.pow(2).add(a);
			a = temp;
		}

		System.out.println(b);
	}
}
