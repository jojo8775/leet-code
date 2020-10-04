package interview.leetcode.practice.round4;

import java.util.Arrays;

public class DifferentWaysToSplitStringInPrimeNumbers {
	public int countPrimeStrings(String inputString) {
		if (inputString.isEmpty()) {
			return 0;
		}

		int mod = (int) 1e9 + 7;

		int maxPrimeNumber = (int) 1e6 + 1;

		boolean[] primes = new boolean[1000001];
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;

		for (int i = 2; i * i < maxPrimeNumber; i++) {
			if (primes[i]) {
				for (int j = i + i; j < maxPrimeNumber; j += i) {
					primes[j] = false;
				}
			}
		}

		int[] dp = new int[inputString.length() + 1];
		dp[0] = 1; // base case

		for (int i = 1; i <= inputString.length(); i++) {
			for (int j = Math.max(0, i - 6); j < i; j++) {
				int num = Integer.parseInt(inputString.substring(j, i));

				if (primes[num]) {
					dp[i] = (dp[j] + dp[i]) % mod;
				}
			}
		}

		return dp[dp.length - 1];
	}

	public static void main(String[] args) {
		int val = new DifferentWaysToSplitStringInPrimeNumbers().countPrimeStrings("11373");
		System.out.println(val);
	}
}
