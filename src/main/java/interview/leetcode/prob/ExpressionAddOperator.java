package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
 * @author jojo
 *
 */
public class ExpressionAddOperator {
	public List<String> addOperators(String num, int target) {
		List<String> result = new ArrayList<>();

		if (num == null || num.length() == 0) {
			return result;
		}

		dfs(result, "", num, target, 0, 0, 0);

		return result;
	}

	private void dfs(List<String> result, String disp, String input, int target, long prevNum, long curSum, int idx){
        if(target == curSum && idx == input.length()){
            result.add(disp);
            return;
        }
        
        if(idx == input.length()){
            return;
        }
        
        for(int i=idx; i<input.length(); i++){
            String curStr = input.substring(idx, i+1);
            if(curStr.length() > 1 && curStr.charAt(0) == '0'){
                break;
            }
            
            long curNum  = Long.parseLong(curStr);
            
            if(disp.isEmpty()){
                dfs(result, curStr, input, target, curNum, curNum, i + 1);
            }
            else{
                //multiply
                dfs(result, disp + '*' + curStr, input, target, curNum * prevNum, curSum - prevNum + curNum*prevNum, i+1);
                //add
                dfs(result, disp + '+' + curStr, input, target, curNum, curSum + curNum, i+1);
                
                //substract
                dfs(result, disp + '-' + curStr, input, target, -curNum, curSum - curNum, i+1);
            }
        }
    }

	public static void main(String[] args) {
		List<String> result = new ExpressionAddOperator().addOperators("123", 6);
		for (String s : result) {
			System.out.println(s);
		}
	}
}
