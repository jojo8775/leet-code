package interview.leetcode.datastructure.heap;

import java.util.LinkedList;
import java.util.Random;

public class Heap
{
	public static void main(String[] args){
		Heap heap = new Heap();
		
		Random random = new Random();
		int count = 0;
		while(count++ < 3){
			heap.insert(random.nextInt(70) + 7);
		}
		
		System.out.println("count: " + count);
		
		while(count-- >= 0){
			System.out.println(heap.getMax());
		}
	}
	
	private LinkedList<Integer> values = new LinkedList<Integer>();
	
	private void insert(int val){
		values.addFirst(val);
		heapify(values, 0);
	}
	
	private void heapify(LinkedList<Integer> values, int index){
		
		int twiceIndex = (2*index) + 1;
		int twiceIndexPlusOne = (2*index) + 2;
		
		int maxIndex = index;
		if(twiceIndex < values.size() && values.get(index) < values.get(twiceIndex)){
			maxIndex = twiceIndex;
		}
		
		if(twiceIndexPlusOne < values.size() && values.get(maxIndex) < values.get(twiceIndexPlusOne)){
			maxIndex = twiceIndexPlusOne;
		}
		
		if(maxIndex != index){
			int temp = values.get(index);
			values.set(index, values.get(maxIndex));
			values.set(maxIndex, temp);
			
			heapify(values, maxIndex);
		}
	}
	
	public int getMax(){
		if(values.isEmpty()){
			return Integer.MIN_VALUE;
		}
		
		int result = values.get(0);
		values.set(0, values.getLast());
		values.removeLast();
		
		heapify(values, 0);
		
		return result;
	}
}
