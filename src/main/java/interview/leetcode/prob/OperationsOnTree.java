package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


/**
 * You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of the ith node. The root of the tree is node 0, so parent[0] = -1 since it has no parent. You want to design a data structure that allows users to lock, unlock, and upgrade nodes in the tree.

The data structure should support the following functions:

Lock: Locks the given node for the given user and prevents other users from locking the same node. You may only lock a node using this function if the node is unlocked.
Unlock: Unlocks the given node for the given user. You may only unlock a node using this function if it is currently locked by the same user.
Upgrade: Locks the given node for the given user and unlocks all of its descendants regardless of who locked it. You may only upgrade a node if all 3 conditions are true:
The node is unlocked,
It has at least one locked descendant (by any user), and
It does not have any locked ancestors.
Implement the LockingTree class:

LockingTree(int[] parent) initializes the data structure with the parent array.
lock(int num, int user) returns true if it is possible for the user with id user to lock the node num, or false otherwise. If it is possible, the node num will become locked by the user with id user.
unlock(int num, int user) returns true if it is possible for the user with id user to unlock the node num, or false otherwise. If it is possible, the node num will become unlocked.
upgrade(int num, int user) returns true if it is possible for the user with id user to upgrade the node num, or false otherwise. If it is possible, the node num will be upgraded.
 

Example 1:


Input
["LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock"]
[[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
Output
[null, true, false, true, true, true, false]

Explanation
LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
lockingTree.lock(2, 2);    // return true because node 2 is unlocked.
                           // Node 2 will now be locked by user 2.
lockingTree.unlock(2, 3);  // return false because user 3 cannot unlock a node locked by user 2.
lockingTree.unlock(2, 2);  // return true because node 2 was previously locked by user 2.
                           // Node 2 will now be unlocked.
lockingTree.lock(4, 5);    // return true because node 4 is unlocked.
                           // Node 4 will now be locked by user 5.
lockingTree.upgrade(0, 1); // return true because node 0 is unlocked and has at least one locked descendant (node 4).
                           // Node 0 will now be locked by user 1 and node 4 will now be unlocked.
lockingTree.lock(0, 1);    // return false because node 0 is already locked.
 

Constraints:

n == parent.length
2 <= n <= 2000
0 <= parent[i] <= n - 1 for i != 0
parent[0] == -1
0 <= num <= n - 1
1 <= user <= 104
parent represents a valid tree.
At most 2000 calls in total will be made to lock, unlock, and upgrade.
Accepted
5,324
Submissions
13,233
 * @author jojo
 * Dec 27, 2021 10:51:27 PM
 */
public class OperationsOnTree {
	public static class LockingTree {
	    private Node[] nodes;

	    public LockingTree(int[] parent) {
	        int N = parent.length;
	        nodes = new Node[N];
	        for(int i=0; i<N; i++) nodes[i] = new Node();

	        // Tree Construction
	        for(int i=0; i<N; i++) {
	            int p = parent[i];
	            
	            if(p == -1) {
	            	continue;
	            }
	            
	            nodes[i].parent = nodes[p];
	            nodes[p].children.add(nodes[i]);
	        }
	    }
	    
	    public boolean lock(int num, int user) {
	        if(nodes[num].locked == false) {
	            nodes[num].user = user;
	            nodes[num].locked = true;
	            return true;
	        }
	        
	        return false;
	    }
	    
	    public boolean unlock(int num, int user) {
	        if(nodes[num].locked && nodes[num].user == user) {
	            nodes[num].locked = false;
	            return true;
	        }
	        return false;
	    }
	    
	    public boolean upgrade(int num, int user) {
	        Node cur = nodes[num];
	        while(cur != null) {
	            if(cur.locked) return false;
	            cur = cur.parent;
	        }

	        if(isChildLocked(nodes[num])) {
	            nodes[num].locked = true;
	            nodes[num].user = user;
	            return true;
	        } 

	        return false;
	    }

	    public boolean isChildLocked(Node curNode) {
	        boolean lockChildFound = false;

	        for(Node child : curNode.children) {
	            if(child.locked){
	                child.locked = false;
                    child.user = -1;
	                lockChildFound = true;
	            } 
                
	            if(isChildLocked(child)) {
	            	lockChildFound = true;
	            }
	        }

	        return lockChildFound;
	    }
	    
	    private static class Node {
	        Node parent = null;
	        List<Node> children = new ArrayList<>();
	        boolean locked = false;
	        int user = -1;
	    }
	}
	
	
	// --- below code is very inefficient.
	class LockingTree_old {
	    private int[] parent;
	    private Map<Integer, Integer> lockedMap = new HashMap<>();
	    private Map<Integer, List<Integer>> childMap = new HashMap<>();
	    
	    public LockingTree_old(int[] parent) {
	        this.parent = parent;
	        
	        for(int i=0; i<parent.length; i++){
	            List<Integer> val = childMap.get(parent[i]);
	            if(val == null){
	                val = new ArrayList<>();
	                childMap.put(parent[i], val);
	            }
	            
	            val.add(i);
	        }
	    }
	    
	    public boolean lock(int num, int user) {
	        int lockedUser = lockedMap.getOrDefault(num, -1);
	        if(lockedUser != -1){
	            return false;
	        }
	        
	        lockedMap.put(num, user);
	        print("lock");
	        
	        return true;
	    }
	    
	    public boolean unlock(int num, int user) {
	        int lockedUser = lockedMap.getOrDefault(num, -1);
	        if(lockedUser == -1 || lockedUser != user){
	            return false;
	        }
	        
	        lockedMap.remove(num);
	        print("unlock");
	        return true;
	    }
	    
	    public boolean upgrade(int num, int user) {
	        // lock 
	        if(!lock(num, user)){
	            return false;
	        }

	        // ancestor and decendent
	        if(!checkAncestorUnlocked(num) || !checkChildrenLocked(num)){
	            unlock(num, user);
	            return false;
	        }
	        
	        print("upgrade");
	        
	        return true;
	    }
	    
	    private boolean checkAncestorUnlocked(int num){
	        while(parent[num] != -1){
	            num = parent[num];
	            if(lockedMap.containsKey(num)){
	                return false;
	            }
	        }
	        
	        return true;
	    }
	    
	    private boolean checkChildrenLocked(int num){
	        List<Integer> decendent = new ArrayList<>();
	        Stack<Integer> stack = new Stack<>();
	        
	        boolean isLocked = false;
	        
	        stack.push(num);
	        
	        while(!stack.isEmpty()){
	            int top = stack.pop();
	            
	            for(int child : childMap.getOrDefault(top, new ArrayList<Integer>())){
	                stack.push(child);
	                decendent.add(child);
	                if(!isLocked){
	                    isLocked = lockedMap.containsKey(child);
	                }
	            }
	        }
	        
	        if(!isLocked){
	            return false;
	        }
	        
	        for(int n : decendent){
	            lockedMap.remove(n);
	        }
	        
	        return true;
	    }
	    
	    private void print(String action){
//	         List<Integer> keys = new ArrayList<>(lockedMap.keySet());
//	         Collections.sort(keys);
	        
//	         System.out.println("------ " + action + " ------");
	        
//	         for(int n : keys){
//	             System.out.print(n + ", ");
//	         }
	        
//	         System.out.println("\n-------------------");
	    }
	}

	/**
	 * Your LockingTree object will be instantiated and called as such:
	 * LockingTree obj = new LockingTree(parent);
	 * boolean param_1 = obj.lock(num,user);
	 * boolean param_2 = obj.unlock(num,user);
	 * boolean param_3 = obj.upgrade(num,user);
	 */
}
