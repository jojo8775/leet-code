package interview.leetcode.concepts;

public class BinaryIndexTree
{
	public static void main(String[] args){
		int[] nums = {3,2,-1,6,5,4,-3,3,7,2,3};
		int[] indexBinaryTree = createBinarIndexTree(nums);
		
		System.out.println(findPrefixSum(5, indexBinaryTree));
		System.out.println(findPrefixSum(6, indexBinaryTree));
	}
	
	private static int[] createBinarIndexTree(int[] arr){
		int[] indexTree = new int[arr.length + 1];
		
		for(int i=1; i<indexTree.length; i++){
			indexTree[i] += arr[i-1];
			
			int nextNode = i;
			while((nextNode = getNextNode(nextNode)) < indexTree.length){
				indexTree[nextNode] += arr[i-1];
			}
		}
		
		return indexTree;
	}
	
	private static int getNextNode(int index){
		int twosComplement = ~index;
		twosComplement += 1;
		twosComplement &= index;
		return twosComplement + index;
	}
	
	private static int findPrefixSum(int index, int[] indexTree){
		index += 1;
		
		int sum = 0;
		while(index != 0){
			sum += indexTree[index];
			index = getParent(index);
		}
		
		return sum;
	}
	
	private static int getParent(int index){
		int twosComplement = ~index;
		twosComplement += 1;
		twosComplement &= index;
		
		return index - twosComplement;
	}
}
