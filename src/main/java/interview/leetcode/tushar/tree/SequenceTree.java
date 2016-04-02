package interview.leetcode.tushar.tree;

public class SequenceTree
{
	public static void main(String[] args){
		int[] arr = {-1,2,4,0};
		
		int seqArrLength = 0, pow = 0;
		while(arr.length > Math.pow(2,pow)){
			pow++;
		}
		
		seqArrLength = (int) Math.pow(2,pow);
		seqArrLength = (seqArrLength * 2) - 1;
		
		int[] seqArr = new int[seqArrLength];
		while(seqArrLength > 0){
			seqArr[--seqArrLength] = Integer.MAX_VALUE;
		}
		
		createSeqTree(arr, seqArr, 0, arr.length - 1, 0);
		print(seqArr);
		
		System.out.println(findMin(seqArr, 0, 2, 0, arr.length-1, 0));
	}
	
	private static void createSeqTree(int[] input, int[] seqArr, int low, int high, int pos){
		if(low == high){
			seqArr[pos] = input[low];
			print(seqArr);
			return;
		}
		
		int mid = (low + high)/2;
		createSeqTree(input, seqArr, low, mid, 2*pos + 1);
		createSeqTree(input, seqArr, mid + 1, high, 2*pos + 2);
		
		seqArr[pos] = Math.min(seqArr[2*pos + 1], seqArr[2*pos + 2]);
	}
	
	private static int findMin(int[] seqArr, int qLow, int qHigh, int low, int high, int pos){
		
		//total overlap
		if(qLow <= low && qHigh >=high){
			return seqArr[pos];
		}
		
		//no overlap
		if((qHigh < low) || (qLow > high)){
			return Integer.MAX_VALUE;
		}
		
		int mid = (low + high)/2;
		return Math.min(findMin(seqArr, qLow, qHigh, low, mid, 2*pos + 1), findMin(seqArr, qLow, qHigh, mid+1, high, 2*pos + 2));
	}
	
	private static void print(int[] arr){
		for(int i : arr){
			System.out.print(i + ", ");
		}
		System.out.println();
		try
		{
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
