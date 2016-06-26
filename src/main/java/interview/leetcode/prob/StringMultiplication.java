package interview.leetcode.prob;

/**
 * 
 * @author jojo
 *
 */
public class StringMultiplication {
	public String multiply(String num1, String num2){
		num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        
        int[] arr = new int[num1.length() + num2.length()];
        
        //storing the multiplication of each position
        for(int i=0; i<num1.length(); i++){
            for(int j=0; j<num2.length(); j++){
                arr[i+j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        
        //calculating the real value
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i=0; i<arr.length; i++){
            int temp = arr[i] + carry;
            arr[i] = temp%10;
            carry = temp/10;
            sb.append(arr[i]);
        }
        
        sb.reverse();
        
        //removing 0's from front
        int idx = 0;
        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) == '0'){
                idx++;
            }
            else{
                break;
            }
        }
        
        //handling when the result is 0
        if(idx == sb.length()){
        	return "0";
        }
        
        return sb.substring(idx).toString();
	}
	
	public static void main(String[] args){
		System.out.println(new StringMultiplication().multiply("12", "12"));
	}
}
