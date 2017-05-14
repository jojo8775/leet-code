package interview.leetcode.prob;

/**
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False
 * @author jojo
 *May 13, 201710:11:31 PM
 */
public class StidentsAttendenceRecord {
    public boolean checkRecord(String s) {
        boolean absentFound = false;
        int lateCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == 'A') {
                if (absentFound) {
                    return false;
                }

                absentFound = true;
                lateCount = 0;
            }

            else if (ch == 'L') {
                lateCount++;
            } else {
                lateCount = 0;
            }

            if (lateCount > 2) {
                return false;
            }
        }

        return true;
    }
}
