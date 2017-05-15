package interview.leetcode.prob.paid;

/**
 * Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases:

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.
And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:
Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", 
where '-' represents the looped status. 
The answer string came from the fourth looped one, 
where you could cut from the middle character 'a' and get "zyxcba".
 * @author jojo
 *
 */
public class SplitLoopedWord {
    public String splitLoopedString(String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            String rev = new StringBuilder(strs[i]).reverse().toString();
            if (strs[i].compareTo(rev) < 0) {
                strs[i] = rev;
            }
        }

        String rev = "";
        for (int i = 0; i < strs.length; i++) {
            String revWord = new StringBuilder(strs[i]).reverse().toString();
            for (String s : new String[] { revWord, strs[i] }) {
                for (int j = 0; j <= s.length(); j++) {
                    StringBuilder sb = new StringBuilder(s.substring(j));
                    for (int k = i + 1; k < strs.length; k++) {
                        sb.append(strs[k]);
                    }

                    for (int k = 0; k < i; k++) {
                        sb.append(strs[k]);
                    }

                    sb.append(s.substring(0, j));

                    String key = sb.toString();
                    if (rev.equals("") || rev.compareTo(key) < 0) {
                        rev = key;
                    }
                }
            }
        }

        return rev;
    }
}
