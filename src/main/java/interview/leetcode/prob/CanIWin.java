package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
    Map<Integer, Boolean> map;
    boolean[] used;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal)
            return false;
        if (desiredTotal <= 0)
            return true;

        map = new HashMap();
        used = new boolean[maxChoosableInteger + 1];
        return helper(desiredTotal, new int[]{2, 0});
    }
    
    public boolean helper(int desiredTotal, int[] player) {
        if(player[0] == 1 && player[1] == 1){
            return true;
        }
        
        if(desiredTotal <= 0){
            return false;
        }
        
        int key = format(used);
        if(player[0] == 2){
            player[0] = 1;
        }
        else{
            player[0] = 2;
        }
        
        if (!map.containsKey(key)) {
            // try every unchosen number as next step
            for (int i = 1; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    // check whether this lead to a win (i.e. the other player
                    // lose)
                    if (!helper(desiredTotal - i, player)) {
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key);
    }

//    public boolean helper(int desiredTotal) {
//        if (desiredTotal <= 0)
//            return false;
//        int key = format(used);
//        if (!map.containsKey(key)) {
//            // try every unchosen number as next step
//            for (int i = 1; i < used.length; i++) {
//                if (!used[i]) {
//                    used[i] = true;
//                    // check whether this lead to a win (i.e. the other player
//                    // lose)
//                    if (!helper(desiredTotal - i)) {
//                        map.put(key, true);
//                        used[i] = false;
//                        return true;
//                    }
//                    used[i] = false;
//                }
//            }
//            map.put(key, false);
//        }
//        return map.get(key);
//    }

    public int format(boolean[] used) {
        int num = 0;
        for (boolean b : used) {
            num <<= 1;
            if (b)
                num |= 1;
        }
        return num;
    }
    
    public static void main(String[] args){
        System.out.println(new CanIWin().canIWin(3, 4));
    }
}
