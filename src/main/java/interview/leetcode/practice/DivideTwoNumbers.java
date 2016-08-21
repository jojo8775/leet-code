package interview.leetcode.practice;

public class DivideTwoNumbers {
	public int divideNumbers(int divident, int divisor) {
		if(divisor == 0 || (divident == Integer.MIN_VALUE && divisor == -1)){
			return Integer.MAX_VALUE;
		}
		
		long pDivident = divident;
		long pDivisor = divisor;

		if (pDivident < 0) {
			pDivident *= -1;
		}

		if (pDivisor < 0) {
			pDivisor *= -1;
		}

		int result = 0;
		while (pDivident >= pDivisor) {
			int leftShift = 0;
			// this is performing n = a*2^0 + a*2^1 + a*2^2 ....
			while (pDivident >= (pDivisor << leftShift)) {
				leftShift++;
			}

			// bringing back the index by one
			leftShift--;

			// adding result
			result = result + (1 << leftShift);

			// reducing divident
			pDivident = pDivident - (pDivisor << leftShift);
		}

		if(divisor < 1 && divident > 0 || divisor > 0 && divident < 0){
			result *= -1;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(new DivideTwoNumbers().divideNumbers(15, 2));
	}
}
