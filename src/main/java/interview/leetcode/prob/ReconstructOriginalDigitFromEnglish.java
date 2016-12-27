package interview.leetcode.prob;

/**
 * 423. Reconstruct Original Digits from English   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 5174
Total Submissions: 12374
Difficulty: Medium
Contributors: Admin
Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"
 * @author jojo
 *
 */
public class ReconstructOriginalDigitFromEnglish {
    public String originalDigits(String s) {
        int[] digits = new int[10];

        for(char ch : s.toCharArray()){
            // z is unique to only 0
            if(ch == 'z'){
                digits[0]++;
            }
            // o will have 0,1,2,4 hits
            if(ch == 'o'){
                digits[1]++;
            }
            // w is unique to only 2
            if(ch == 'w'){
                digits[2]++;
            }
            // h will have 8,3 hits
            if(ch == 'h'){
                digits[3]++;
            }
            // u is unique to only 4
            if(ch == 'u'){
                digits[4]++;
            }
            // f will have 4, 5 hits 
            if(ch == 'f'){
                digits[5]++;
            }
            // x is unique to only 6
            if(ch == 'x'){
                digits[6]++;
            }
            // this will have 6, 7 hits
            if(ch == 's'){ 
                digits[7]++;
            }
            // g is unique to only 8
            if(ch == 'g'){
                digits[8]++;
            }
            // i will have 5,6,8,9 hits
            if(ch == 'i'){
                digits[9]++;
            }
        }
        
        // getting true count of 5
        digits[5] = digits[5] - digits[4];
        
        // getting true count of 7
        digits[7] = digits[7] - digits[6];
        
        // getting true count of 3
        digits[3] = digits[3] - digits[8];
        
        // getting true count of 1
        digits[1] = digits[1] - digits[0] - digits[2] - digits[4];
        
        // getting true count of 9
        digits[9] = digits[9] - digits[5] - digits[6] - digits[8];
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<10; i++){
            for(int j=0; j<digits[i]; j++){
                sb.append(i);
            }
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args){
        System.out.println(new ReconstructOriginalDigitFromEnglish().originalDigits("owoztneoer"));
    }
}
