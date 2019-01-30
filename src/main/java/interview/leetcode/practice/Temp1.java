package interview.leetcode.practice;

public class Temp1 {
	public String addStrings(String num1, String num2) {
        int i=num1.length(), j = num2.length(), sum = 0, carry = 0;
        
        StringBuilder sb = new StringBuilder();
        while(i > 0) {
        	sum = (int) (num1.charAt(--i) - '0');
        	if(j > 0) {
        		sum += (int) (num2.charAt(--j) - '0');
        	}
        	
        	carry = calculate(sb, sum, carry);
        }
        
        while(j > 0) {
        	sum = (int) (num2.charAt(--j) - '0');
        	carry = calculate(sb, sum, carry);
        }
        
        if(carry > 0) {
        	sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
	
	private int calculate(StringBuilder sb, int sum, int carry) {
		sum += carry;
		carry = sum/10;
		sum = sum%10;
		sb.append(sum);
		
		return carry;
	}
	
	public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        
        int[] arr = new int[num1.length() + num2.length()];
        
        for(int i=num1.length() - 1, k = arr.length -1; i>=0; i--, k--){
            int carry = 0, idx = k, n1 = (int) (num1.charAt(i) - '0');
            
            for(int j = num2.length() - 1; j>=0; j--){
                int n2 = (int) (num2.charAt(j) - '0');
                arr[idx] += (n1 * n2);
                arr[idx] += carry;
                carry = arr[idx] / 10;
                arr[idx] = arr[idx] % 10;
                idx--;
            }
            
            if(carry > 0){
                arr[idx] = carry;
            }
        }
        
        StringBuilder sb = new StringBuilder();
    
//        print(arr);
        
        boolean firstDigitFound = false;
        for(int i=0; i<arr.length; i++){
            if(firstDigitFound){
                sb.append(arr[i]);
            }
            else if(arr[i] > 0){
                firstDigitFound = true;
                sb.append(arr[i]);
            }
        }
        
        return sb.toString();
    }
	
	private void print(int[] arr) {
		for(int n : arr) {
			System.out.print(n + ",");
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(new Temp1().addStrings("0", "9"));
//		System.out.println(new Temp1().addStrings("98", "9"));
//		
		System.out.println(new Temp1().multiply("32534", "3435"));
		System.out.println(new Temp1().multiply("1", "1"));
	}
}
