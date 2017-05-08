package interview.leetcode.practice.round3.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequence {
    public List<List<Integer>> findAllIncreasingSequence(int[] arr){
        Set<List<Integer>> resultSet = new HashSet<>();
        
        for(int i=0; i<arr.length; i++){
            List<Integer> entry = new ArrayList<>();
            entry.add(arr[i]);
            dfs(entry, resultSet, arr, i+1);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        for(List<Integer> entry : resultSet){
            result.add(entry);
        }
        
        return result;
    }

    private void dfs(List<Integer> entry, Set<List<Integer>> result, int[] arr, int idx){
        if(idx == arr.length){
            return;
        }
        
        result.add(new ArrayList<Integer>(entry));
            
        for(int i=idx; i<arr.length; i++){
            if(entry.get(entry.size() - 1) <= arr[i]){
                entry.add(arr[i]);
                dfs(entry, result, arr, i+1);
                
                result.add(new ArrayList<Integer>(entry));
                entry.remove(entry.size() - 1);   
            }
        }
    }
    
    public static void main(String[] args){
        List<List<Integer>> result = new IncreasingSubsequence().findAllIncreasingSequence(new int[] {4,6,7,7});
        for(List<Integer> entry : result){
            for(Integer i : entry){
                System.out.print(i + ", ");
            }
            System.out.println();
        }
    }
}
