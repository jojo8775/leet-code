package interview.leetcode.tushar.dynamicprograming;

public class InterleavingString {
	public static void main(String[] args) {
		System.out.println(isValid("aab", "axy", "aaxaby"));
	}

	private static boolean isValid(String s1, String s2, String r) {
		int n1 = s1.length(), n2 = s2.length();
		boolean[][] arr = new boolean[n1 + 1][n2 + 1];
		arr[0][0] = true;

		// assigning zeroth row
		for (int i = 1; i < arr[0].length; i++) {
			arr[0][i] = arr[0][i - 1] && r.charAt(i - 1) == s2.charAt(i - 1);
		}

		// assigning zeroth col
		for (int i = 1; i < arr.length; i++) {
			arr[i][0] = arr[i-1][0] && r.charAt(i - 1) == s1.charAt(i - 1);
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length; j++) {
				char ch = r.charAt(i + j - 1);
				arr[i][j] = (ch == s1.charAt(i - 1) && arr[i - 1][j]) || (ch == s2.charAt(j - 1) && arr[i][j - 1]);
			}
		}

		print(arr);

		return arr[s1.length()][s2.length()];
	}

	private static void print(boolean[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
