package interview.leetcode.practice.round3.string;

public class ZumaGame {
    public int findMoves(String str1, String str2){
        int[] handCount = new int[26];
        for(char ch : str2.toCharArray()){
            handCount[(int) (ch - 'A')]++;
        }

        int result = getTries(str1 + "#", handCount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int getTries(String str, int[] handCount){
        String filteredStr = removeConsecutive(str);
        if(filteredStr.equals("#")){
            return 0;
        }
        
        int count = Integer.MAX_VALUE;
        
        for(int i=0, j=0; j<filteredStr.length(); j++){
            if(filteredStr.charAt(i) == filteredStr.charAt(j)){
                continue;
            }
            
            int moves = 3 - (j-i);
            
            if(handCount[filteredStr.charAt(i) - 'A'] >= moves){
                handCount[filteredStr.charAt(i) - 'A'] -= moves;
                count = Math.min(count, moves + getTries(str.substring(0,i) + str.substring(j), handCount));
                handCount[filteredStr.charAt(i) - 'A'] += moves;
            }
            
            i = j;
        }
        
        return count;
    }

    private String removeConsecutive(String str){
        for(int i=0, j=0; j<str.length(); j++){
            if(str.charAt(i) == str.charAt(j)){
                continue;
            }
            
            if(j - i >=3){
                return removeConsecutive(str.substring(0, i) + str.substring(j));
            }
            
            i = j;
        }
        
        return str;
    }
}
