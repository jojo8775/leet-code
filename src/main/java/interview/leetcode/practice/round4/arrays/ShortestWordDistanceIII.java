package interview.leetcode.practice.round4.arrays;

public class ShortestWordDistanceIII {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int pos1 = -1, pos2 = -1, result = Integer.MAX_VALUE;
        for(int i=0; i<words.length; i++){
            if(words[i].equals(word1)){
                if(word1.equals(word2)){
                    pos2 = pos1;
                }
                
                pos1 = i;
            }
            else if(words[i].equals(word2)){
                pos2 = i;
            }
            
            if(pos1 != -1 && pos2 != -1){
                result = Math.min(result, Math.abs(pos1 - pos2));
            }
        }
        
        return result;
    }
}
