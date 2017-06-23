package interview.leetcode.practice.round3.array;

import java.util.ArrayList;
import java.util.List;

import interview.leetcode.prob.GreyCode;

public class GeryCode {
    public List<Integer> getGreyCode(int digitCount){
        List<Integer> result = new ArrayList<>();
        result.add(0);
        
        for(int i=0; i<digitCount; i++){
            int len = result.size() - 1;
            for(int j=len; j>=0; j--){
                result.add(result.get(j) | (1 << i));
            }
        }
        
        return result;
    }
    
    public static void main(String[] args){
        List<Integer> result = new GreyCode().grayCode(6);
        
        for(int n : result){
            StringBuilder sb = new StringBuilder();
            while(n > 0){
                sb.append(n%2);
                n = n/2;
            }
            
            System.out.println(sb.reverse().toString());
        }
    }
}
