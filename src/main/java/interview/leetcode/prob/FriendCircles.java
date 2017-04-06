package interview.leetcode.prob;

/**
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.
Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *Apr 4, 20178:56:54 PM
 */
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        // maintains the association of transitive association of friends
        int[] arr = new int[M.length];
        
        for(int i=0; i<arr.length; i++){
            arr[i] = i;
        }
        
        int friendCircleCount = arr.length;
        for(int i=0; i<M.length; i++){
            for(int j=0; j<M[0].length; j++){
                // if there is a friendship exisits
                if(M[i][j] == 1){
                    // find parent of first friend circle
                    int parent1 = findParent(arr, i);
                    // find parent of second friend circle
                    int parent2 = findParent(arr, j);
                    
                    // if the parent of two different friend circle are separate then its time to merge them
                    if(parent1 != parent2){
                        arr[parent2] = arr[parent1];
                        friendCircleCount --;
                    }
                }
            }
        }
        
        return friendCircleCount;
    }
    
    // finds the parent of a friend circle
    private int findParent(int[] arr, int idx){
        while(arr[idx] != idx){
            idx = arr[idx];
        }
        
        return idx;
    }
}
