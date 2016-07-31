package interview.leetcode.prob;

/**
 * Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
 * @author jojo
 *
 */
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        if(s == null || s.isEmpty()){
            return 0;
        }
        
        int result = (int) (s.charAt(0) - 'A' + 1);
        int index = 1;
        
        while(index < s.length()){
            result = (result * 26) + (int) (s.charAt(index++) - 'A' + 1);
        }
        
        return result;
    }
	
	public static void main(String[] args){
		System.out.println(new ExcelSheetColumnNumber().titleToNumber("BAB"));
	}
}
