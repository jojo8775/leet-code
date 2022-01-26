package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class Prac5 {
	public List<String> generateParenthesis(int n) {
	    List<String> result = new ArrayList<>();
	    generate(result, 0, 0, n, 0, new char[n*2]);

	    return result;
	}

	private void generate(List<String> result, int obc, int cbc, int n, int idx, char[] carr){
	    if(idx == carr.length){
	        result.add(String.valueOf(carr));
	        return;
	    }

	    if(obc < n){
	        carr[idx] = '(';
	        generate(result, obc + 1, cbc, n, idx + 1, carr);
	    }

	    if(obc > cbc){
	        carr[idx] = ')';
	        generate(result, obc, cbc + 1, n, idx + 1, carr);
	    }
	}
	
	public static void main(String[] args) {
		List<String> result = new Prac5().generateParenthesis(3);
		result.stream().forEach(x -> System.out.println(x));
	}
}
