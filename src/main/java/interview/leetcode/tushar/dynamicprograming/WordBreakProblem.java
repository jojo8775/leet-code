package interview.leetcode.tushar.dynamicprograming;

import java.util.HashSet;
import java.util.Set;

public class WordBreakProblem
{
	private static Set<String> dictionary = new HashSet<String>();
	
	public static void main(String[] args){
		dictionary.add("I");
		dictionary.add("A");
		dictionary.add("AM");
		dictionary.add("ACE");
		
		System.out.println(isValid("IMAC"));
		System.out.println(isValid("IAMACE"));
	}
	
	private static boolean isValid(String s){
		
		boolean[][] arr = new boolean[s.length()][s.length()];
		
		for(int i=0; i<s.length(); i++){
			arr[i][i] = dictionary.contains(s.substring(i,i+1));
		}
		
		for(int i=1; i<s.length(); i++){
			int k = 0;
			for(int j=i; j<s.length(); j++){
				boolean isWord = dictionary.contains(s.substring(k,j+1));
				
				if(!isWord){
					int count = k;
					while(count < j){
						isWord = arr[k][count] && arr[count+1][j];
						if(isWord){
							break;
						}
						count++;
					}
				}
				
				arr[k][j] = isWord;
				
				k++;
			}
		}
		
		return arr[0][s.length() - 1];
	}
}
