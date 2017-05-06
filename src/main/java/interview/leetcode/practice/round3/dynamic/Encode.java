package interview.leetcode.practice.round3.dynamic;

public class Encode {
    public String encode(String str){
        String[][] dp = new String[str.length()][str.length()];
        
        for(int span=0; span<str.length(); span++){
            for(int start = 0; start + span < str.length(); start++){
                int end = start + span;
                String substring = str.substring(start, end + 1);
                
                dp[start][end] = substring;
                if(span < 4){
                    continue;
                }
                
                for(int i=start; i<end; i++){
                    if(dp[start][i].length() + dp[i+1][end].length() < dp[start][end].length()){
                        dp[start][end] = dp[start][i] + dp[i+1][end];
                    }
                }
          
                String pattern = findPattern(substring);
                if(pattern.length() == substring.length()){
                    continue;
                }
                
                String encode = substring.length() / pattern.length() + "[" + dp[start][start + pattern.length() - 1] + "]"; 
                
                if(encode.length() <= dp[start][end].length()){
                    dp[start][end] = encode;
                }
            }
        }
        
        return dp[0][str.length() - 1];
    }

    private String findPattern(String str){
        int[] arr = new int[str.length()];
        
        int beg = 1, end = arr.length - 1, j = 0;
        while(beg <= end){
            if(str.charAt(beg) == str.charAt(j)){
                arr[beg++] = ++j;
            }
            else if(j > 0){
                j = arr[j-1];
            }
            else{
                beg++;
            }
        }
        
        int patternLength = str.length() - arr[end];
        
        if(arr[end] == 0 || str.length() % patternLength != 0){
            return str;
        }
        
        return str.substring(0, patternLength);
    }

    public static void main(String[] args){
        System.out.println(new Encode().encode("abcabcabcabceabcabcabcabce"));
    }
}
