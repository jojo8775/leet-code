package interview.leetcode.practice.round2;

public class Challange {
	public int shortestDistance(String[] words, String word1, String word2) {
        int min = Integer.MAX_VALUE, i = -1, j = -1;
        
        for(int n=0; n < words.length; n++){
            System.out.println("W: " + words[n]);
            if(words[n].equals(word1)){
                i = n;
                System.out.println("m1: " + words[n]);
            }
            else if(words[n].equals(word2)){
                j = n;
            }
            
            if(i != -1 && j != -1){
                min = Math.min(min, Math.abs(i-j));
            }
        }
        
        return min;
    }
	
	public static void main(String[] args) {
		int val = new Challange().shortestDistance(new String[] {"practice", "makes", "perfect", "coding", "makes"}, "practice", "coding");
		System.out.println(val);
	}
}
