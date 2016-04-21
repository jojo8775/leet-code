package interview.leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class Permutation
{
	public static void main(String[] args){
		findPermutation("", "aaabc", new HashSet<String>());
		System.out.println("Count: " + count);
	}

	private static int count;
	private static void findPermutation(String soFar, String rest, Set<String> set){
		if(rest.isEmpty()){
			System.out.println(soFar);
			count++;
			return;
		}
		
		for(int i=0; i<rest.length(); i++){
			String updatedSoFar = soFar + rest.charAt(i);
			String remaining = rest.substring(0,i) + rest.substring(i+1, rest.length());
			
			if(!set.contains(updatedSoFar)){
				set.add(updatedSoFar);
				findPermutation(updatedSoFar, remaining, set);
			}
		}
	}
}
