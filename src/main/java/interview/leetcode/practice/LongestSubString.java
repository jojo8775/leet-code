package interview.leetcode.practice;

public class LongestSubString {
//    public int longestSubstring(String s, int k) {
//        char[] str = s.toCharArray();
//        return helper(str,0,s.length(),k);
//    }
//    private int helper(char[] str, int start, int end,  int k){
//        if(end-start<k) return 0;//substring length shorter than k.
//        int[] count = new int[26];
//        for(int i = start;i<end;i++){
//            int idx = str[i]-'a';
//            count[idx]++;
//        }
//        for(int i = 0;i<26;i++){
//            if(count[i]<k&&count[i]>0){ //count[i]=0 => i+'a' does not exist in the string, skip it.
//                for(int j = start;j<end;j++){
//                    if(str[j]==i+'a'){
//                        int left = helper(str,start,j,k);
//                        int right = helper(str,j+1,end,k);
//                        return Math.max(left,right);
//                    }
//                }
//            }
//        }
//        return end-start;
//    }
    public int longestSubstring(String s, int k) {
        // will be using divide and concur 
        return helper(s.toCharArray(), k, 0, s.length());
    }
    
    private int helper(char[] s, int k, int beg, int end){
        // if the input is not long enough ther wont be any result.
        if(end - beg < k){
            return 0;
        }
        
        // counting the frequency of all the characters of the input 
        int[] cArr = new int[26];
        for(int i=beg; i<end; i++){
            cArr[s[i] - 'a']++;
        }
        
        // 
        for(int i=0; i<26; i++){
            // ignoring all the 0 values as that represent absence of that character
            // considering only those characters which couldnot make it to k as they are breaking point
            if(cArr[i]> 0 && cArr[i] < k){
                char breakingChar = (char) (i + 'a');
                
                // looking for the character in the given input 
                for(int j=beg; j<end; j++){
                    if(s[j] == breakingChar){
                        int leftLength = helper(s, k, beg, j);
                        int rightLength = helper(s, k, j+1, end);
                        
                        return Math.max(leftLength, rightLength);
                    }
                }
            }
        }
        
        // if all the preset characters are greater than k then result should be end - start
        return end - beg;
    }
    
    public static void main(String[] args){
        System.out.println(new LongestSubString().longestSubstring("ababacb", 3));
    }
}
