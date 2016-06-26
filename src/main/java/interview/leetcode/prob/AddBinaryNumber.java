package interview.leetcode.prob;

public class AddBinaryNumber {
	public String addBinary(String a, String b) {
        int carry = 0, aLen = a.length() - 1, bLen = b.length() - 1, temp = 0;
        StringBuilder sb = new StringBuilder();
        
        while(aLen >=0){
            temp = a.charAt(aLen--) - '0';
            temp += carry;
            
            if(temp > 1){
                carry = 1;
                temp = 0;
            }
            else{
                carry = 0;
            }
            
            if(bLen >=0){
                temp += (b.charAt(bLen--) - '0');
                
                if(temp > 1){
                    carry = 1;
                    temp = 0;
                }
            }
            
            sb.append(temp);
        }
        
        while(bLen >= 0){
            temp = b.charAt(bLen--) - '0';
            temp += carry;
            
            if(temp > 1){
                carry = 1;
                temp = 0;
            }
            else{
                carry = 0;
            }
            
            sb.append(temp);
        }
        
        if(carry > 0){
            sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
	
	public static void main(String[] args){
//		System.out.println(new AddBinaryNumber().addBinary("1", "111"));
//		System.out.println(new AddBinaryNumber().addBinary("11", "11"));
		System.out.println(new AddBinaryNumber().addBinary("11", "1"));
	}
}
