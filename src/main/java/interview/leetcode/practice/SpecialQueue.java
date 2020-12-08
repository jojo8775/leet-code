package interview.leetcode.practice;

import java.util.Deque;
import java.util.LinkedList;

public class SpecialQueue {
	public static class FrontMiddleBackQueue {
		public Deque<Integer> head = new LinkedList<Integer>(), tail = new LinkedList<Integer>();

		public FrontMiddleBackQueue() {
		}

		public void pushFront(int val) {
			head.offerFirst(val);
			forwardMove();
		}

		public void pushMiddle(int val) {
			head.offerLast(val);
			forwardMove();
		}

		public void pushBack(int val) {
			tail.offerLast(val);
			backMove();
		}

		public int popFront() {
			int ans = -1;
			if (head.size() > 0) {
				ans = head.pollFirst();
				backMove();
			} else {
				if (tail.size() == 0)
					return ans;
				ans = tail.pollFirst();
			}
			return ans;
		}

		public int popMiddle() {
			if (tail.size() == 0 && head.size() == 0)
				return -1;
			if (tail.size() > head.size())
				return tail.pollFirst();
			return head.pollLast();
		}

		public int popBack() {
			if (tail.size() == 0)
				return -1;
			int ans = tail.pollLast();
			forwardMove();
			return ans;
		}

		private void backMove() {
			while (tail.size() - head.size() > 1)
				head.offerLast(tail.pollFirst());
		}

		private void forwardMove() {
			while (tail.size() < head.size())
				tail.offerFirst(head.pollLast());
		}

		public static void main(String[] args) {
//			FrontMiddleBackQueue qq = new FrontMiddleBackQueue();
//			qq.pushMiddle(1);
//			qq.pushMiddle(2);
//			qq.pushMiddle(3);
//			qq.pushMiddle(4);
//			
//			System.out.println("pop middle: " + qq.popMiddle());
//			System.out.println("pop middle: " + qq.popMiddle());
//			System.out.println("pop middle: " + qq.popMiddle());
//			System.out.println("pop middle: " + qq.popMiddle());
//			System.out.println("pop middle: " + qq.popMiddle());

//			FrontMiddleBackQueue qq = new FrontMiddleBackQueue();
//			qq.pushFront(1);
//			qq.pushFront(2);
//			qq.pushFront(3);
//			qq.pushFront(4);
//			
//			System.out.println("pop back: " + qq.popBack());
//			System.out.println("pop back: " + qq.popBack());
//			System.out.println("pop back: " + qq.popBack());
//			System.out.println("pop back: " + qq.popBack());
//			System.out.println("pop back: " + qq.popBack());

//			FrontMiddleBackQueue qq = new FrontMiddleBackQueue();
//			qq.pushFront(1);
//			qq.pushBack(2);
//			qq.pushMiddle(3);
//			qq.pushMiddle(4);
//			
//			System.out.println("pop front: " + qq.popFront());
//			System.out.println("pop middle: " + qq.popMiddle());
//			System.out.println("pop middle: " + qq.popMiddle());
//			System.out.println("pop back: " + qq.popBack());
//			System.out.println("pop front: " + qq.popFront());
		}
	}

	/**
	 * Your FrontMiddleBackQueue object will be instantiated and called as such:
	 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue(); obj.pushFront(val);
	 * obj.pushMiddle(val); obj.pushBack(val); int param_4 = obj.popFront(); int
	 * param_5 = obj.popMiddle(); int param_6 = obj.popBack();
	 */
}
