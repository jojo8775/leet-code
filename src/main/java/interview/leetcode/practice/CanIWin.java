package interview.leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
    public boolean canIWin(int maxChoice, int totalTarget){
        if(maxChoice < 1){
            return false;
        }
        
        if(totalTarget == 0){
            return true;
        }
        
        boolean[] choices = new boolean[maxChoice + 1];
        return canIWin(choices, totalTarget, new HashMap<Integer, Boolean>());
    }    

    private boolean canIWin(boolean[] choices, int totalTarget, Map<Integer, Boolean> cache){
        if(totalTarget < 0){
            return false;
        }
           
        int selection = getSelections(choices);
        if(cache.containsKey(selection)){
            return cache.get(selection);
        }
        
        for(int i=1; i<choices.length; i++){
            if(!choices[i]){
                choices[i] = true;
                if(!canIWin(choices, totalTarget - i, cache)){
                    cache.put(getSelections(choices), true);
                    choices[i] = false;
                    return true;
                }
                
                choices[i] = false;
            }
        }
        
        cache.put(selection, false);
        return false;
    }

    private int getSelections(boolean[] choices){
        int num = 0;
        for(int i=0; i<choices.length; i++){
            if(choices[i]){
                num |= (1 << i);
            }
        }
        
        return num;
    }
    
    public static void main(String[] args){
        System.out.println(new CanIWin().canIWin(5, 11));
    }
}
