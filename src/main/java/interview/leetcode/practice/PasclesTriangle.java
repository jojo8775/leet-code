package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class PasclesTriangle {
    public List<List<Integer>> createTriange(int n){
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for(int i=0; i<n; i++){
            List<Integer> list = new ArrayList<Integer>();
            list.add(1);

            for(int j=1; j<i; j++){
                List<Integer> prevList = result.get(result.size() - 1);
                list.add(prevList.get(j-1) + prevList.get(j));
            }
            
            if(i > 0){
                list.add(1);
            }

            result.add(list);
        }

        return result;
    }
    
    public static void main(String[] args){
        new PasclesTriangle().createTriange(5);
    }
}
