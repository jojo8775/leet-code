package interview.leetcode.practice;

public class KMPAlgorithm {
	private int findIndex(String s1, String s2) {
		int[] pattern = new int[s1.length()];
		
		for(int j=0, i=1; i<pattern.length; i++) {
			while(s1.charAt(i) != s1.charAt(j) && j > 0) {
				j = pattern[j-1];
			}
			
			if(s1.charAt(i) == s1.charAt(j)) {
				pattern[i] = j+1;
				j++;
			}
		}
		
		for(int idx = 0, i=0; i<s2.length(); i++) {
			
			while(s1.charAt(idx) != s2.charAt(i) && idx > 0) {
				idx = pattern[idx - 1];
			}
			
			if(s1.charAt(idx) == s2.charAt(i)) {
				idx++;
			}
			
			if(idx == pattern.length) {
				return i - idx + 1;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		int result = new KMPAlgorithm().findIndex("aabc", "ccdssaabcdsdses");
		
		System.out.println(result);
	}
	
}


