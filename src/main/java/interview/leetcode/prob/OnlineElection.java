package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.  

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.

 

Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation: 
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.
 

Note:

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times is a strictly increasing array with all elements in [0, 10^9].
TopVotedCandidate.q is called at most 10000 times per test case.
TopVotedCandidate.q(int t) is always called with t >= times[0].
Accepted
29,760
Submissions
57,870
 * @author jojo
 * Mar 23, 2021  11:18:25 PM
 */
public class OnlineElection {
	public static class TopVotedCandidate {
	    private int[] personVote = new int[5000];
	    private List<int[]> timeLine = new ArrayList<>();
	    private int maxCount = 0, pIdx = 0;
	    
	    public TopVotedCandidate(int[] persons, int[] times) {
	        for(int i=0; i<persons.length; i++){
	            int p = persons[i];
	            personVote[p]++;
	            
	            if(personVote[p] >= maxCount){
	                maxCount = personVote[p];
	                pIdx = p;
	            }
	            
	            timeLine.add(new int[]{times[i], pIdx});
	        }
	    }
	    
	    public int q(int t) {
	        int idx = binarySearch(t);
	        return timeLine.get(idx)[1];
	    }
	    
	    private int binarySearch(int time){
	        int beg = 0, end = timeLine.size();
	        
	        while(beg < end){
	            int mid = beg + (end - beg)/2;
	            
	            int val = timeLine.get(mid)[0];
	            
	            if(val < time){
	                beg = mid + 1;
	            }
	            else{
	                end = mid;
	            }
	        }
	        
	        // taking into account of the usecase when the given time is not present and we need to find the index which is < target.
	        if(beg == timeLine.size() || (beg > 0 && timeLine.get(beg)[0] != time)){
	            beg--;
	        }
	        
	        return beg;
	    }
	}

	
	public static void main(String[] args) {
		TopVotedCandidate p = new TopVotedCandidate(new int[] {0,1,1,0,0,1,0}, new int[] {0,5,10,15,20,25,30});
		
		System.out.println(p.q(3));
	}
}
