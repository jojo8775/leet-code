package interview.leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class Pract2 {
    public List<String> create(int num){
        List<String> result = new ArrayList<>();
        create(result, num, num, "");
        return result;
    }
    
    private void create(List<String> result, int num1, int num2, String str){
        if(num1 > num2){
            return;
        }
        
        if(num1 == 0 && num2 == 0){
            result.add(str);
            return;
        }
        
        if(num1 > 0){
            create(result, num1 - 1, num2, str + "(");
        }
        
        if(num2 > 0){
            create(result, num1, num2 - 1, str + ")");
        }
    }
    
    public static void main(String[] args){
        List<String> r = new Pract2().create(4);
        
        for(String s : r){
            System.out.println(s);
        }
    }
}
