package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

public class OperationsOnTreeV2 {
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
}
