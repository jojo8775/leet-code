package interview.leetcode.prob;

public class LongestValidParentheses {
	public int find(String str){
		int obCount = 0, len = 0;
		char[] cArr = str.toCharArray();
		
		for(int i=0; i<cArr.length; i++){
			if(cArr[i] == '('){
				obCount ++;
			}
			else{
				obCount --;
				if(obCount < 0){
					cArr[i] = '#';
					obCount = 0;
				}
			}
		}
		
		int cbCount = 0, t=0, result = 0;
		for(int i=cArr.length - 1; i>=0; i--){
			if(cArr[i] == ')'){
				cbCount ++;
			}
			else if(cArr[i] == '('){
				cbCount --;
				if(cbCount < 0){
					cArr[i] = '#';
					cbCount = 0;
				}
			}
			
			t = cArr[i] == '#' ? 0 : (t+1);
			result = Math.max(t, result);
		}
		
		return result;
	}
}
