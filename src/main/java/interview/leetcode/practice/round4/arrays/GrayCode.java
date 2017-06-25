package interview.leetcode.practice.round4.arrays;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        if(n == 0){
            return result;
        }
        
        
        for(int i=0; i<n; i++){
            int len = result.size()-1;
            for(int j=len; j>=0; j--){
                result.add(result.get(j) | (1 << i));
            }
        }
        
        return result;
    }
}
