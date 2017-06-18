package interview.leetcode.practice.round3.string;

public class ReverseWords {
    public String reverseWords(String s) {
        s = filterString(s);
        
        if(s.isEmpty()){
            return "";
        }
        
        char[] cArr = s.toCharArray();
        
        reverse(cArr, 0, cArr.length - 1);
        
        int beg = 0;
        for(int i=0; i<cArr.length; i++){
            if(cArr[i] == ' '){
                reverse(cArr, beg, i-1);
                beg = i+1;
            }
        }
        
        reverse(cArr, beg, cArr.length - 1);
        
        return String.valueOf(cArr);
    }
    
    private String filterString(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == ' ' && sb.length() == 0){
                continue;
            }
            else if(i > 0 && str.charAt(i) == ' ' && str.charAt(i-1) == ' '){
                continue;
            }
            else{
                sb.append(str.charAt(i));
            }
        }
        
        if(sb.length() > 0 && sb.charAt(sb.length() - 1) == ' '){
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    private void reverse(char[] cArr, int beg, int end){
        while(beg < end){
            char temp = cArr[beg];
            cArr[beg++] = cArr[end];
            cArr[end--] = temp;
        }
    }
    
    public static void main(String[] args){
        String result = new ReverseWords().reverseWords(" aefsd     b   ew   s    frsde");
        System.out.println(result.isEmpty());
        System.out.println(result);
    }
}
