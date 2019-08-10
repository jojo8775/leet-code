package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree

 



 

as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Note:

N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 * @author jojo
 * Jun 6, 2019 11:19:44 PM
 */
public class SerializeAndDeserializeNAryTree {
	// Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null){
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        
        return sb.toString();
    }
    
    private void serialize(Node node, StringBuilder sb){
        if(node == null){
            return;
        }
        
        sb.append(node.val).append(",");
        if(node.children != null){
            sb.append(node.children.size()).append(",");
            
            for(Node child : node.children){
                serialize(child, sb);
            }
        }
        else{
            sb.append("0").append(",");
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data == null || data.isEmpty()){
            return null;
        }
        
        return createTree(data, new int[]{0});
    }
    
    private Node createTree(String str, int[] idx){
        if(idx[0] == str.length()){
            return null;
        }
        
        String nodeVal = getToken(str, idx[0]);
        idx[0] += nodeVal.length() + 1;
        
        String childCount = getToken(str, idx[0]);
        idx[0] += childCount.length() + 1;
        
        int childSize = Integer.valueOf(childCount);
        
        List<Node> children = new ArrayList<>();
        for(int i=0; i<childSize; i++){
            children.add(createTree(str, idx));
        }
        
        Node node = new Node(Integer.valueOf(nodeVal), children);
        return node;
    }
    
    private String getToken(String str, int idx){
        int beg = idx;
        while(idx < str.length()){
            if(str.charAt(idx) == ','){
                break;
            }
            else{
                idx++;
            }
        }
        
        return str.substring(beg, idx);
    }
    
    private static class Node{
    	int val;
    	List<Node> children;
    	
    	public Node(int val, List<Node> children) {
    		this.val = val;
    		this.children = children;
    	}
    }
}
