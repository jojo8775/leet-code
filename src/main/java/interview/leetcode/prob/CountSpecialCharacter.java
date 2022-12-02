package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * We call a positive integer special if all of its digits are distinct.

Given a positive integer n, return the number of special integers that belong to the interval [1, n].

 

Example 1:

Input: n = 20
Output: 19
Explanation: All the integers from 1 to 20, except 11, are special. Thus, there are 19 special integers.
Example 2:

Input: n = 5
Output: 5
Explanation: All the integers from 1 to 5 are special.
Example 3:

Input: n = 135
Output: 110
Explanation: There are 110 integers from 1 to 135 that are special.
Some of the integers that are not special are: 22, 114, and 131.
 

Constraints:

1 <= n <= 2 * 109
Accepted
8,039
Submissions
22,134
 * @author jojo
 * Nov 29, 2022 3:04:18 PM
 */
public class CountSpecialCharacter {
    public int countSpecialNumbers(int N) {
        // Transform N + 1 to arrayList
         ArrayList<Integer> nums = new ArrayList<Integer>();
         for (int x = N + 1; x > 0; x /= 10){
             nums.add(0, x % 10);
         }

         // Count the number with digits < N
         // e.g: if the number is 2424 then we should get all the possible combinations till 242 
         int res = 0, len = nums.size();
         for (int i = 1; i < len; i++){
             res += 9 * getUniqueDigitPermutation(9, i - 1);
         }

         // Count the number with same prefix
         HashSet<Integer> seen = new HashSet<>();
         for (int i = 0; i < len; i++) {
             // cannot assign digit '0' to the first place thats why j starts at 1 when i == 0 
             for (int j = i > 0 ? 0 : 1; j < nums.get(i); ++j){ 
                 if (!seen.contains(j)){
                     // 9 - i : represents the previously assigned digits 
                     // len - i - 1 : represents the remaining length of the number.
                     res += getUniqueDigitPermutation(9 - i, len - i - 1);
                 }
             }

             // the possible number of permutation will be same if we assign digit 1 to first position vs digit 2 in the first position. 
             // for this reason if i is visited we need to teminate. 
             if (seen.contains(nums.get(i))){
                 break;  
             } 

             // assign a digit to n place. 
             seen.add(nums.get(i));
         }

         return res;
     }


     // this computes the maximum combimation of unique digits for a 
     // given length of a number. 
     // e.g: 
     // num length = 1  --- Count: 9 (1 .. 9 digits)
     // num length = 2  --- Count: 9 * 9 = 81 (1 to 9 for the first place and 0 to 9 except the chosen onde from first place)
     // num length = 3  --- Count: 9 * 9 * 8 = 648 (number available for third place is 0 to 9 but cannot can only use 8 becuase two are taken for first and second place)
     // num length = 3  --- Count: 9 * 9 * 8 * 7 = 4536 (4th is 7 because other three are taken)
     // so on... 
     public int getUniqueDigitPermutation(int m, int n) {
         return n == 0 ? 1 : getUniqueDigitPermutation(m, n - 1) * (m - n + 1);
     }
}
