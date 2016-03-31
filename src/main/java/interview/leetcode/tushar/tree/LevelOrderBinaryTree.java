package interview.leetcode.tushar.tree;

import java.util.ArrayList;
import java.util.List;

public class LevelOrderBinaryTree
{
	public static void main(String[] args){
		TreeNode node = TreeUtil.createTree(16);
		parseLevel(node);
	}
	
	private static void parseLevel(TreeNode node){
		
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		nodeList.add(node);
		
		while(!nodeList.isEmpty()){
			List<TreeNode> tempNodes = new ArrayList<TreeNode>();
			for(TreeNode singleNode : nodeList){
				System.out.print(singleNode.val + ", ");
				if(singleNode.leftChild != null){
					tempNodes.add(singleNode.leftChild);
				}
				if(singleNode.rightChild != null){
					tempNodes.add(singleNode.rightChild);
				}
			}
			
			System.out.println();
			
			nodeList = tempNodes;
		}
	}
}
