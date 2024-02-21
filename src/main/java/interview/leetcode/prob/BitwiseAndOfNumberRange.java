package interview.leetcode.prob;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND
 * of all numbers in this range, inclusive.
 * 
 * For example, given the range [5, 7], you should return 4.
 * 
 * 
 * @author jojo
 *
 */
public class BitwiseAndOfNumberRange {
	/**
	 * 1. each number odd number has last bit equal to 1 and even as 0
	 * 2. if m == 4 and n = 14 then there has to be a number between 4 and 14 which has
	 * last bit equal to 1 
	 * 3. this is the reason we can right shift both m and n at each iteration of while loop as long as m != n;
	 */
	public int rangeBitwiseAnd_1(int m, int n) {
		int leftShift = 0;

		while (m != 0 && m != n) {
			m = m >> 1;
			n = n >> 1;
			leftShift++;
		}

		if (m == 0) {
			return m;
		}

		return m << leftShift;
	}
	
	public int rangeBitwiseAnd(int left, int right) {
        int beg = left, end = right;
        
        // beg <= end will go to an infinite  loop for inputs like 0, 0,
        while(beg < end){
            // since end & end - 1 will always be less than end we dont need end--
            // further more it will make the hops efficient
            end = (end & (end - 1));            
        }
        
        return beg & end;
    }

	public static void main(String[] args) {
		System.out.println(new BitwiseAndOfNumberRange().rangeBitwiseAnd(600000000, 2147483645));
	}
}
