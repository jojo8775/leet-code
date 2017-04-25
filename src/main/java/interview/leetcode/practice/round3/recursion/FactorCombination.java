package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorCombination {
    public List<List<Integer>> factorCombination(int num){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        findFactors(num, 2, new ArrayList<Integer>(), result);

        return result;
    } 

    private void findFactors(int num, int idx, List<Integer> list, List<List<Integer>> result){
        for(int i=idx; i*i<=num; i++){
            if(num%i == 0){
                list.add(i);
                findFactors(num/i, i, list, result);
                List<Integer> resultEntry = new ArrayList<Integer>(list);
                resultEntry.add(num/i);            
                result.add(resultEntry);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public static void main(String[] args){
        List<List<Integer>> result = new FactorCombination().factorCombination(32);
        
        for(List<Integer> entry : result){
            StringBuilder sb = new StringBuilder();
            for(int n : entry){
                sb.append(n).append(" x ");
            }
            
            System.out.println(sb.substring(0, sb.length() - 3).toString());
        }
    }
}
