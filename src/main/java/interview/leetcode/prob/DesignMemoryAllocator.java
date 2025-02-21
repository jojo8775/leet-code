package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an integer n representing the size of a 0-indexed memory array. All memory units are initially free.

You have a memory allocator with the following functionalities:

Allocate a block of size consecutive free memory units and assign it the id mID.
Free all memory units with the given id mID.
Note that:

Multiple blocks can be allocated to the same mID.
You should free all the memory units with mID, even if they were allocated in different blocks.
Implement the Allocator class:

Allocator(int n) Initializes an Allocator object with a memory array of size n.
int allocate(int size, int mID) Find the leftmost block of size consecutive free memory units and allocate it with the id mID. Return the block's first index. If such a block does not exist, return -1.
int freeMemory(int mID) Free all memory units with the id mID. Return the number of memory units you have freed.
 

Example 1:

Input
["Allocator", "allocate", "allocate", "allocate", "freeMemory", "allocate", "allocate", "allocate", "freeMemory", "allocate", "freeMemory"]
[[10], [1, 1], [1, 2], [1, 3], [2], [3, 4], [1, 1], [1, 1], [1], [10, 2], [7]]
Output
[null, 0, 1, 2, 1, 3, 1, 6, 3, -1, 0]

Explanation
Allocator loc = new Allocator(10); // Initialize a memory array of size 10. All memory units are initially free.
loc.allocate(1, 1); // The leftmost block's first index is 0. The memory array becomes [1,_,_,_,_,_,_,_,_,_]. We return 0.
loc.allocate(1, 2); // The leftmost block's first index is 1. The memory array becomes [1,2,_,_,_,_,_,_,_,_]. We return 1.
loc.allocate(1, 3); // The leftmost block's first index is 2. The memory array becomes [1,2,3,_,_,_,_,_,_,_]. We return 2.
loc.freeMemory(2); // Free all memory units with mID 2. The memory array becomes [1,_, 3,_,_,_,_,_,_,_]. We return 1 since there is only 1 unit with mID 2.
loc.allocate(3, 4); // The leftmost block's first index is 3. The memory array becomes [1,_,3,4,4,4,_,_,_,_]. We return 3.
loc.allocate(1, 1); // The leftmost block's first index is 1. The memory array becomes [1,1,3,4,4,4,_,_,_,_]. We return 1.
loc.allocate(1, 1); // The leftmost block's first index is 6. The memory array becomes [1,1,3,4,4,4,1,_,_,_]. We return 6.
loc.freeMemory(1); // Free all memory units with mID 1. The memory array becomes [_,_,3,4,4,4,_,_,_,_]. We return 3 since there are 3 units with mID 1.
loc.allocate(10, 2); // We can not find any free block with 10 consecutive free memory units, so we return -1.
loc.freeMemory(7); // Free all memory units with mID 7. The memory array remains the same since there is no memory unit with mID 7. We return 0.
 

Constraints:

1 <= n, size, mID <= 1000
At most 1000 calls will be made to allocate and freeMemory.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
21.3K
Submissions
39.8K
Acceptance Rate
53.5%
 * 
 * Jan 20, 2025 - 12:01:36 PM
 * Jojo 
 */
public class DesignMemoryAllocator {
	public class Allocator {
	    boolean[] arr;
	    Map<Integer, List<int[]>> map = new HashMap<>();

	    public Allocator(int n) {
	        this.arr = new boolean[n];
	    }
	    
	    public int allocate(int size, int mID) {
	        if(size > arr.length){
	            return -1;
	        }

	        int occupied = 0;
	        for(int i=0; i<size-1; i++){
	            if(arr[i] == true){
	                occupied++;
	            }
	        }

	        int result = -1;
	        for(int i=size-1, j=0; i<arr.length; i++, j++){
	            if(arr[i] == true){
	                occupied++;
	            }

	            if(occupied == 0){
	                result = j;

	                for(int k=j; k<=i; k++){
	                    arr[k] = true;
	                }

	                map.computeIfAbsent(mID, v -> new ArrayList<>()).add(new int[]{j, i});
	                break;
	            }

	            if(arr[j] == true){
	                occupied--;
	            }
	        }

	        /*
	        System.out.println("Allocate ---  s: " + size + "   id: " + mID);
	        System.out.println(print(arr));
	        System.out.println("--- end ---");
	        */

	        return result;    
	    }
	    
	    public int freeMemory(int mID) {
	        List<int[]>  val = map.get(mID);

	        if(val == null){
	            //System.out.println("Free ---  id: " + mID + "  key not found");
	            return 0;
	        }

	        int count = 0;

	        for(int[] e : val){
	            for(int i=e[0]; i<=e[1]; i++){
	                arr[i] = false;
	                count++;
	            }
	        }

	        /*
	        System.out.println("Free ---  id: " + mID + "   count: " + count);
	        System.out.println(print(arr));
	        System.out.println("--- end ---");
	        */

	        map.remove(mID);

	        return count;
	    }

	    private String print(boolean[] arr){
	        StringBuilder sb = new StringBuilder();
	        for(boolean n : arr){
	            sb.append(n).append(",");
	        }

	        //System.out.println(sb.toString());
	        return sb.toString();
	    }
	}
}
