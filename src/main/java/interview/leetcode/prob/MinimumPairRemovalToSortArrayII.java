package interview.leetcode.prob;

import java.util.PriorityQueue;

/**
 * Given an array nums, you can perform the following operation any number of times:

Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
Replace the pair with their sum.
Return the minimum number of operations needed to make the array non-decreasing.

An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).

 

Example 1:

Input: nums = [5,2,3,1]

Output: 2

Explanation:

The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
The array nums became non-decreasing in two operations.

Example 2:

Input: nums = [1,2,2]

Output: 0

Explanation:

The array nums is already sorted.

 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 * 
 * chiranjeebnandy
 * Mar 21, 2026  2026  10:41:17 PM
 */
public class MinimumPairRemovalToSortArrayII {
    //  // ---- Node: one element in our shrinking linked list ----
    // static class Node {
    //     long val;
    //     int originalIndex;  // position in the original array (for tiebreaking)
    //     Node prev;
    //     Node next;
    //     boolean alive;      // becomes false once merged away
 
    //     Node(long val, int originalIndex) {
    //         this.val = val;
    //         this.originalIndex = originalIndex;
    //         this.alive = true;
    //     }
    // }
 
    // // ---- Pair: snapshot of an adjacent pair (left, right) ----
    // static class Pair {
    //     long sum;
    //     Node left;
    //     Node right;
 
    //     Pair(Node left, Node right) {
    //         this.left = left;
    //         this.right = right;
    //         this.sum = left.val + right.val;
    //     }
 
    //     // A pair is stale if:
    //     //   - either node was merged away
    //     //   - they're no longer neighbors (something between them was removed/changed)
    //     //   - their values changed, making the stored sum outdated
    //     boolean isValid() {
    //         return left.alive
    //             && right.alive
    //             && left.next == right
    //             && sum == left.val + right.val;
    //     }
    // }
 
    // public int minimumPairRemoval(int[] nums) {
    //     int n = nums.length;
    //     if (n <= 1) return 0;
 
    //     // 1. Build doubly-linked list
    //     Node[] nodes = new Node[n];
    //     for (int i = 0; i < n; i++) {
    //         nodes[i] = new Node(nums[i], i);
    //     }
    //     for (int i = 0; i < n - 1; i++) {
    //         nodes[i].next = nodes[i + 1];
    //         nodes[i + 1].prev = nodes[i];
    //     }
 
    //     // 2. Min-heap: smallest sum first, leftmost original index breaks ties
    //     PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
    //         int c = Long.compare(a.sum, b.sum);
    //         return c != 0 ? c : Integer.compare(a.left.originalIndex, b.left.originalIndex);
    //     });
 
    //     // 3. badCount = number of adjacent pairs where left > right
    //     //    When badCount hits 0, the list is non-decreasing and we stop.
    //     int badCount = 0;
 
    //     for (int i = 0; i < n - 1; i++) {
    //         pq.offer(new Pair(nodes[i], nodes[i + 1]));
    //         if (nodes[i].val > nodes[i + 1].val) badCount++;
    //     }
 
    //     int ops = 0;
 
    //     while (badCount > 0) {
 
    //         // ---- Lazy deletion: skip stale pairs ----
    //         Pair best = pq.poll();
    //         while (!best.isValid()) {
    //             best = pq.poll();
    //         }
 
    //         Node left  = best.left;
    //         Node right = best.right;
    //         Node p = left.prev;    // neighbor before left  (may be null)
    //         Node k = right.next;   // neighbor after right  (may be null)
 
    //         // ---- Subtract old bad-pair contributions ----
    //         if (left.val > right.val)            badCount--;
    //         if (p != null && p.val > left.val)   badCount--;
    //         if (k != null && right.val > k.val)  badCount--;
 
    //         // ---- Merge: absorb right into left ----
    //         left.val += right.val;
    //         right.alive = false;       // mark right as dead
 
    //         left.next = k;             // skip over dead node
    //         if (k != null) k.prev = left;
 
    //         // ---- Add new bad-pair contributions and push new pairs ----
    //         if (p != null) {
    //             if (p.val > left.val) badCount++;
    //             pq.offer(new Pair(p, left));
    //         }
    //         if (k != null) {
    //             if (left.val > k.val) badCount++;
    //             pq.offer(new Pair(left, k));
    //         }
 
    //         ops++;
    //     }
 
    //     return ops;
    // }

    public int minimumPairRemoval(int[] nums) {
        int len = nums.length;
        Node[] nodes = new Node[nums.length];
        for(int i=0; i<len; i++){
            nodes[i] = new Node(nums[i], i);
        }

        for(int i=0; i<len - 1; i++){
            nodes[i].next = nodes[i+1];
            nodes[i + 1].prev = nodes[i];
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if(a.sum == b.sum){
                return Integer.compare(a.left.idx, b.left.idx);
            }

            return Long.compare(a.sum, b.sum);
        });

        int badPairs = 0;
        for(int i=0; i<len-1; i++){
            if(nodes[i].val > nodes[i+1].val){
                badPairs++;
            }

            pq.offer(new Pair(nodes[i], nodes[i+1]));
        }

        int operationCount = 0;
        while(badPairs > 0 && !pq.isEmpty()){
            Pair top = pq.poll();

            if(!top.isValid()){
                continue;
            }

            Node left = top.left;
            Node right = top.right;
            Node k = left.prev;
            Node l = right.next;

            if(left.val > right.val){
                badPairs--;
            }

            if(k != null && k.val > left.val){
                badPairs--;
            }

            if(l != null && right.val > l.val){
                badPairs--;
            }

            left.val += right.val;
            right.isValid = false;

            left.next = l;

            if(l!=null){
                l.prev = left;
            }

            if(k != null){
                if(k.val > left.val){
                    badPairs++;
                }
                pq.offer(new Pair(k, left));
            }

            if(l != null){
                if(left.val > l.val){
                    badPairs++;
                }
                pq.offer(new Pair(left, l));
            }

            operationCount++;
        }

        return operationCount;
    }

    private static class Node{
        Node prev = null, next = null;
        int idx;
        long val;
        boolean isValid = true;

        public Node(int val, int idx){
            this.val = (long)val;
            this.idx = idx;
        }
    }

    private static class Pair{
        Node left, right;
        long sum;

        public Pair(Node left, Node right){
            this.left = left;
            this.right = right;
            sum = left.val + right.val;
        }

        public boolean isValid(){
            return left.isValid && right.isValid && left.next == right && sum == left.val + right.val;
        }
    }
}
