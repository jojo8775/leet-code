package interview.leetcode.prob;

/**
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.

 

Example 1:


Input: hour = 12, minutes = 30
Output: 165
Example 2:


Input: hour = 3, minutes = 30
Output: 75
Example 3:


Input: hour = 3, minutes = 15
Output: 7.5
 

Constraints:

1 <= hour <= 12
0 <= minutes <= 59
Accepted
100,712
Submissions
158,828
 * @author jojo
 * Nov 6, 2022 12:53:06 AM
 */
public class AngleBetweenHandsOfAClock {
    public double angleClock(int hour, int minutes) {
        // hour hand: degree of each hours = 360/12 = 30
        // hour hand: degree of each min = (1 hr angle)/60 = 30/60 = 0.5
        // minute hand: degree per min = 360/60 = 6
        
        double hourAngle = (hour * 30) + (minutes * 0.5);
        
        double minAngle = minutes * 6;
        
        double angle = Math.abs(hourAngle - minAngle);
        
        if(angle > 180){
            return 360.0 - angle;
        }
        
        return angle;
    }
}
