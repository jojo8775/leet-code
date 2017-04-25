package interview.leetcode.practice.round3.trick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeDecode {
    public String encode(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            for (char ch : str.toCharArray()) {
                sb.append(ch);
                if (ch == '#') {
                    sb.append(ch);
                }
            }

            sb.append(" # ");
        }

        return sb.append(" # ").toString();
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int beg = 0, end = str.length() - 1;
        while (beg < end) {
            char ch = str.charAt(beg);
            if (ch == '#') {
                if (beg > 0 && str.charAt(beg - 1) == str.charAt(beg + 1) && str.charAt(beg + 1) == ' ') {
                    result.add(sb.substring(0, sb.length() - 1));
                    sb = new StringBuilder();
                    beg += 2;
                } else {
                    while (beg < end && str.charAt(beg) == '#') {
                        sb.append("#");
                        beg += 2;
                    }
                }
            } else {
                sb.append(ch);
                beg++;
            }
        }

        return result;
    }
    
    public static void main(String[] args){
        EncodeDecode e = new EncodeDecode();
        String str = e.encode(Arrays.asList("as sdveh 33 # ssldfkvm ####", "   wufwe sdg #dg3##kdhs sd"));
        List<String> result = e.decode(str);
        
        for(String s : result){
            System.out.println(s);
        }
    }
}
