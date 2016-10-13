package interview.leetcode.prob.paid;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?
Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *
 */
public class BestMeetingPoint {
	public int minTotalDistance(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        
        int[] col = new int[grid[0].length], row = new int[grid.length];
        
        // finding sum of each columns and rows 
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j] == 1){
                    col[j] += 1;
                    row[i] += 1;
                }
            }
        }
        
        return findMinimum(col) + findMinimum(row);
    }
    
    private int findMinimum(int[] arr){
        // sum of total moves taken if meeting point is at -1
        int sum = 0, numberOfRightCandidates = 0, numberOfLeftCandidates = 0;
        for(int i=0; i<arr.length; i++){
            sum += (i+1)*arr[i];
            numberOfRightCandidates += arr[i];
        }
        
        int min = sum;
        
        for(int i=0; i<arr.length; i++){
            // for each right move all right candidates needs to move one less step and left candidates needs to move one more step
            sum = sum - numberOfRightCandidates + numberOfLeftCandidates;
            if(sum <= min){
                min = sum;
            }
            else{
                break;
            }
            
            numberOfRightCandidates -= arr[i];
            numberOfLeftCandidates += arr[i];
        }
        
        return min;
    }
    
    public static void main(String[] args){
    	int[][] grid = {{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};
    	System.out.println(new BestMeetingPoint().minTotalDistance(grid));
    }
}
