package interview.leetcode.prob;

/**
 * You are given a string, message, and a positive integer, limit.

You must split message into one or more parts based on limit. Each resulting part should have the suffix "<a/b>", where "b" is to be replaced with the total number of parts and "a" is to be replaced with the index of the part, starting from 1 and going up to b. Additionally, the length of each resulting part (including its suffix) should be equal to limit, except for the last part whose length can be at most limit.

The resulting parts should be formed such that when their suffixes are removed and they are all concatenated in order, they should be equal to message. Also, the result should contain as few parts as possible.

Return the parts message would be split into as an array of strings. If it is impossible to split message as required, return an empty array.

 

Example 1:

Input: message = "this is really a very awesome message", limit = 9
Output: ["thi<1/14>","s i<2/14>","s r<3/14>","eal<4/14>","ly <5/14>","a v<6/14>","ery<7/14>"," aw<8/14>","eso<9/14>","me<10/14>"," m<11/14>","es<12/14>","sa<13/14>","ge<14/14>"]
Explanation:
The first 9 parts take 3 characters each from the beginning of message.
The next 5 parts take 2 characters each to finish splitting message. 
In this example, each part, including the last, has length 9. 
It can be shown it is not possible to split message into less than 14 parts.
Example 2:

Input: message = "short message", limit = 15
Output: ["short mess<1/2>","age<2/2>"]
Explanation:
Under the given constraints, the string can be split into two parts: 
- The first part comprises of the first 10 characters, and has a length 15.
- The next part comprises of the last 3 characters, and has a length 8.
 

Constraints:

1 <= message.length <= 104
message consists only of lowercase English letters and ' '.
1 <= limit <= 104
Accepted
14,461
Submissions
33,185
 * 
 * Nov 24, 2024 - 5:41:34 PM
 * Jojo 
 */
public class SplitMessageBasedOnLimits {
	public String[] splitMessage(String message, int limit) {
        int left = 1, right = message.length();
        
        int segmentCount = Integer.MAX_VALUE;
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            
            int status = canSegmentCountFit(mid, limit, message);
            //System.out.println("status: " + status + "  mid: " + mid + "  l: " + left + "   r: " + right);
            
            if(status == 0){
                segmentCount = Math.min(segmentCount, mid);
                
                // resetting the scope to find a better result;
                left = 1;
                right = mid - 1;
            }
            else if(status == -1){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        
        if(segmentCount == Integer.MAX_VALUE){
            return new String[]{};
        }
        
        return formatOutput(segmentCount, message, limit);
    }
    
    private int canSegmentCountFit(int segmentCount, int limit, String message){
        int charSofarCount = 0;
        
        for(int i=1; i<= segmentCount; i++){
            int formatLen = 3 + String.valueOf(i).length() + String.valueOf(segmentCount).length();
            int remainingLen = limit - formatLen;
            
            if(remainingLen <= 0){
                // need to reduce the format len by reducing the segment count
                return -1;
            }
            
            if(charSofarCount >= message.length()){
                // have taken too many segments need to reduce the count 
                return -1;
            }
            
            charSofarCount += remainingLen;
        }
        
        if(charSofarCount < message.length()){
            // increase the count of segments 
            return 1;
        }
        
        // charSofarCount == message.length --- potential ans.
        return 0; 
    }
    
    private String[] formatOutput(int segmentCount, String message, int limit){
        String[] result = new String[segmentCount];
        
        for(int i=1, idx = 0; i<=segmentCount; i++){
            String format = "<" + i + "/" + segmentCount + ">";
            
            int len = limit - format.length();
            
            result[i-1] = message.substring(idx, Math.min(idx + len, message.length())) + format;
            idx += len;
        }
        
        return result;
    }
    
    /*
   // returns the number of characters in an integer
    int charLen(int value) {
        int len = 0;
        while(value!=0) {
            len++;
            value /=10;
        }
        return len;
    }
    // segments = k
    int checkValidity(String message, int limit, int segments) {
        int charLenSoFar = 0;
        int msgLen = message.length();
        for(int i=1; i<=segments; i++) {
            // if total character length we have collected so far
            // increases the message length, then we have taken too many segments
            // We need to look on the left, return -1
            if(charLenSoFar >= msgLen) {
                return -1;
            }
            int formatLen = 3 + charLen(i) + charLen(segments); // "<" + i + "/" + segments + ">"
            int remLen = limit - formatLen;
            // if the remaining length of a chunk is less than or eq 0,
            // then we won't be able to add any character to it
            if(remLen <= 0) {
                return -1;
            }
            // we can take remLen (remaining length) characters from the message string
            charLenSoFar += remLen;
        }

        // if characters we were able to collect so far is less than the message length,
        // we need to increase our segments, so, look on the right side, return 1
        if(charLenSoFar < msgLen) {
            return 1;
        }

        // we were able to confine all the characters of message
        // using this segments. So, this is a happy case and possible answer.
        return 0;
    }

    public String[] splitMessage(String message, int limit) {
        int left = 1;
        int right = message.length();
        int segments = Integer.MAX_VALUE;

        while(left <= right) {
            int mid = left + (right-left)/2;

            int segmentValidity = checkValidity(message, limit, mid);
            if(segmentValidity == 0) {
                segments = Math.min(segments, mid);
                right = mid - 1;
                left = 1;
            }
            // we need to look on the left side
            else if(segmentValidity == -1) {
                right = mid - 1;
            }
            // we need to look on the right side
            else {
                left = mid + 1;
            }
        }
        // if we never found any happy case, there's no answer
        if(segments == Integer.MAX_VALUE) {
            return new String[0];
        }
        else {
            // construct the string with minimum segments we were able to determine
            return getFormattedStrings(message, limit, segments);
        }
    }

    // segments = k
    String[] getFormattedStrings(String message, int limit, int segments) {
        String[] res = new String[segments];
        int msgLen = message.length();
        int charLenSoFar = 0;

        for(int i=1; i<=segments; i++) {
            String format = "<" + i + "/" + segments + ">";
            int remaining = limit - format.length();

            int startIdx = charLenSoFar;
            int endIdx = charLenSoFar + remaining;
            // we don't want it to go out of bound..
            endIdx = Math.min(msgLen, endIdx);

            res[i-1] = message.substring(startIdx, endIdx) + format;

            // update charLenSoFar (characters so far seen) value 
            // as we were able to acquire remaining size of characters
            charLenSoFar += remaining;
        }

        return res;
    }
    */
}
