package interview.leetcode.prob;

public class MaximumScoreWordsFormedByLetters {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        if(words.length == 0 || letters.length == 0 || score.length == 0){
            return 0;
        }
        
        int[] count = new int[26];
        
        for(char l : letters){
            count[l - 'a']++;
        }
        
        return backtrack(words, count, score, 0);
    }
    
    private int backtrack(String[] words, int[] count, int[] score, int index){
        int res = 0, max = 0;
        
        for(int i=index; i<words.length; i++){
            boolean isValid = true;
            
            for(char ch : words[i].toCharArray()){
                count[ch - 'a']--;
                res += score[ch - 'a'];
                
                if(count[ch - 'a'] < 0){
                    isValid = false;
                }
            }
            
            if(isValid){
                int val = backtrack(words, count, score, i + 1);
                res += val;
                max = Math.max(res, max);
                res -= val;
            }
            
            for(char ch : words[i].toCharArray()){
                count[ch - 'a']++;
                res -= score[ch - 'a'];
            }
        }
        
        return max;
    }
}
