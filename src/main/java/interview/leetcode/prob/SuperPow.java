package interview.leetcode.prob;

/**
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

Result: 1024
 * @author jojo
 *
 */
public class SuperPow {
	private static final int MOD = 1337;

	// we are going to use the formula
	// ab mod c = (a mod c) * (b mod c) * mod c
	//
	public int superPow(int a, int[] b) {
		if (a == 1) {
			return 1;
		}

		int len = 0, result = 1;
		while (len < b.length) {
			result = (pow(result, 10) * pow(a, b[len++])) % MOD;
		}

		return result;
	}

	// this is same as find Pow problem.
	private int pow(int a, int b) {
		if (b == 1 || a==1) {
			return a;
		}

		if (b == 0) {
			return 1;
		}

		int mid = b / 2;
		int val = pow(a, mid) % MOD;
		int result = (val * val) % MOD;

		if (mid % 2 == 0) {
			return result;
		}

		return (result * a) % MOD;
	}
	
	public static void main(String[] args){
		System.out.println(new SuperPow().superPow(2, new int[]{3}));;
	}
}
