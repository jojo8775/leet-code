package interview.leetcode.practice.round3.recursion;

public class ScrambleString {
    public boolean isScrambled(String s1, String s2){
        if(s1.equals(s2)){
            return true;
        }
        
        if(s1.length() != s2.length()){
            return false;
        }
        
        // checking if both have same letters
        int[] arr = new int[256];
        for(char ch : s1.toCharArray()){
            arr[(int) ch]++;
        }
        
        for(char ch : s2.toCharArray()){
            if(--arr[(int) ch] < 0){
                return false;
            }
        }
        
        for(int i=1; i<s1.length(); i++){
            if((isScrambled(s1.substring(0, i), s2.substring(0, i)) && isScrambled(s1.substring(i), s2.substring(i)))
                || (isScrambled(s1.substring(0, i), s2.substring(s2.length() - i)) && isScrambled(s1.substring(s1.length() - i), s2.substring(0, i)))){
                return true;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args){
        System.out.println(new ScrambleString().isScrambled("great", "grtae"));
    }
}
