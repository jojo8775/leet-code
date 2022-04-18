package interview.leetcode.prob;

/**
 * You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.

Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip. Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.

You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. Return the minimum time required for all buses to complete at least totalTrips trips.

 

Example 1:

Input: time = [1,2,3], totalTrips = 5
Output: 3
Explanation:
- At time t = 1, the number of trips completed by each bus are [1,0,0]. 
  The total number of trips completed is 1 + 0 + 0 = 1.
- At time t = 2, the number of trips completed by each bus are [2,1,0]. 
  The total number of trips completed is 2 + 1 + 0 = 3.
- At time t = 3, the number of trips completed by each bus are [3,1,1]. 
  The total number of trips completed is 3 + 1 + 1 = 5.
So the minimum time needed for all buses to complete at least 5 trips is 3.
Example 2:

Input: time = [2], totalTrips = 1
Output: 2
Explanation:
There is only one bus, and it will complete its first trip at t = 2.
So the minimum time needed to complete 1 trip is 2.
 

Constraints:

1 <= time.length <= 105
1 <= time[i], totalTrips <= 107
Accepted
8,257
Submissions
35,155
 * @author jojo
 * Feb 27, 2022 1:51:12 AM
 */
public class MinimumTimeToCompleteTrips {
	public long minimumTime(int[] time, int totalTrips) {
        // taking max as 10^14 because the mid will 10^7 
        long min = 0, max = (long)1e14;
        
        while(min <= max){
            long mid = min + (max - min)/2;
    
            // instead of looking for results linearly, the idea is to assume an answer and then check if the 
            // total trips possible in that assumed answer is close to the given target. This reduces run time to log (n)
            long val = findTotalTrips(time, mid);
            
            if(val < totalTrips){
                min = mid + 1;
            }
            else{
                max = mid - 1;
            }
        }
        
        return min;
    }
    
    private long findTotalTrips(int[] time, long givenTime){
        long totalTrip = 0;
        
        for(int t : time){
            totalTrip += (givenTime / t);
        }
        
        return totalTrip;
    }
    
    
    public long minimumTime_linear(int[] time, int totalTrips) {
        int len = time.length;
        
        int[] reserver = new int[len];
        
        int i = 1, count = 0;
        
        while(count < totalTrips){
            for(int j=0; j<len; j++){
                if(reserver[j] + time[j] == i){
                    reserver[j] += time[j];
                    count++;
                    // System.out.println("res:" + reserver[j] + "  time:" + time[j] + "   count:" + count);
                }
            }
            
            if(count < totalTrips){
                i++;
            }
            //System.out.println("c:" + count + "   r:" + i);
            //System.out.println("-------------");
        }
        
        return i;
    }
}
