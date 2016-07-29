package interview.leetcode.prob;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 * @author jojo
 *
 */
public class ExcelSheetColumnTile {
	public String convertToTitle(int n) {
        if(n <= 0){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(n!=0){
            int i  = 'A' + (--n%26); 
            sb.append((char) i);
            
            n /= 26;
        }
        
        return sb.reverse().toString();
    }
	
	public static void main(String[] args){
		System.out.println(new ExcelSheetColumnTile().convertToTitle(52));
	}
}
