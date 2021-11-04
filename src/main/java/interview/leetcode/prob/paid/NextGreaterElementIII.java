package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit
 * integer which has exactly the same digits existing in the integer n and is
 * greater in value than n. If no such positive 32-bit integer exists, you need
 * to return -1.
 * 
 * Example 1: Input: 12 Output: 21 Example 2: Input: 21 Output: -1
 * 
 * @author jojo
 *
 */
public class NextGreaterElementIII {
	// this is same as next greater permutation. 
	public int nextGreaterElement_adv(int n) {
        List<Integer> list = new ArrayList<>();
        while(n !=0 ){
            list.add(n % 10);
            n = n/10;
        }
        
        reverse(list, 0, list.size() - 1);
        
        int idx1 = list.size() - 1;
        
        while(idx1 >= 1){
            if(list.get(idx1 - 1) < list.get(idx1)){
                idx1--;
                break;
            }
            
            idx1--;
        }
        
        int idx2 = list.size() - 1;
        
        while(idx2 > idx1){
            if(list.get(idx2) > list.get(idx1)){
                break;
            }
            
            idx2--;
        }
        
        if(idx1 == 0 && idx2 == 0){
            return -1;
        }
        
        int temp = list.get(idx2);
        list.set(idx2, list.get(idx1));
        list.set(idx1, temp);
        
        reverse(list, idx1 + 1, list.size() - 1);
        
        long result = 0;
        for(int e : list){
            result *= 10;
            result += e;
        }
        
        return result > Integer.MAX_VALUE ? -1 : (int) result;
    }
    
    private void reverse(List<Integer> list, int beg, int end){
        while(beg < end){
            int temp = list.get(beg);
            list.set(beg, list.get(end));
            list.set(end, temp);
            
            beg++;
            end--;
        }
    }
	
	
    public int nextGreaterElement(int n) {
        String str1 = String.valueOf(n);
        String str2 = String.valueOf(Integer.MAX_VALUE);

        if (str1.length() > str2.length() && str1.compareTo(str2) > 0) {
            return -1;
        }

        char[] cArr = str1.toCharArray();

        int idx1 = cArr.length - 1;
        while (idx1 > 0) {
            if (cArr[idx1] > cArr[idx1 - 1]) {
                idx1--;
                break;
            }

            idx1--;
        }

        int idx2 = cArr.length - 1;
        while (idx2 > idx1) {
            if (cArr[idx2] > cArr[idx1]) {
                break;
            }

            idx2--;
        }

        if (idx1 == 0 && idx2 == 0) {
            return -1;
        }

        char temp = cArr[idx1];
        cArr[idx1] = cArr[idx2];
        cArr[idx2] = temp;

        reverse(cArr, idx1 + 1);

        long val = Long.valueOf(String.valueOf(cArr));
        if (val > Integer.MAX_VALUE) {
            return -1;
        }

        return (int) val;
    }

    private void reverse(char[] cArr, int idx) {
        int beg = idx, end = cArr.length - 1;
        while (beg < end) {
            char ch = cArr[beg];
            cArr[beg++] = cArr[end];
            cArr[end--] = ch;
        }
    }
}
