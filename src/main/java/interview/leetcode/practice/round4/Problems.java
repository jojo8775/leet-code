package interview.leetcode.practice.round4;

public class Problems {
	public String optimalDivision(int[] nums) {
		return getMax(nums, 0, nums.length - 1).str;
	}

	private Result getMax(int[] nums, int beg, int end) {
		Result r = new Result("", Double.MIN_VALUE);

		if (beg == end) {
			r.val = (double) nums[beg];
			r.str = nums[beg] + "";
		} else if (beg + 1 == end) {
			r.val = (double) nums[beg] / (double) nums[end];
			r.str = nums[beg] + "/" + nums[end];
		} else {
			for (int i = beg; i < end; i++) {
				Result numerator = getMax(nums, beg, i);
				Result denominator = getMin(nums, i + 1, end);

				double val = numerator.val / denominator.val;

				if (val > r.val) {
					r.val = val;
					r.str = numerator.str + "/" + ((end - i > 1 ) ? "(" + denominator.str + ")" : denominator.str);
				}
			}
		}

		return r;
	}

	private Result getMin(int[] nums, int beg, int end) {
		Result r = new Result("", Double.MAX_VALUE);

		if (beg == end) {
			r.val = (double) nums[beg];
			r.str = nums[beg] + "";
		} else if (beg + 1 == end) {
			r.val = (double) nums[beg] / (double) nums[end];
			r.str = nums[beg] + "/" + nums[end];
		} else {
			for (int i = beg + 1; i < end; i++) {
				Result numerator = getMin(nums, beg, i);
				Result denominator = getMax(nums, i + 1, end);

				double val = numerator.val / denominator.val;

				if (val < r.val) {
					r.val = val;
					r.str = numerator.str + "/" + ((end - i > 1) ? "(" + denominator.str + ")" : denominator.str);
				}
			}
		}

		return r;
	}

	private static class Result {
		double val;
		String str;

		public Result(String str, double val) {
			this.str = str;
			this.val = val;
		}
	}
	
	public int longestRepeatingSubstring(String S) {
        int result = 0, len = S.length(); 
        
        int[] dp = new int[len + 1];
        
        for(int i=1; i<=len; i++){
            for(int j=i-1; j > 0; j--){
                if(S.charAt(i-1) == S.charAt(j-1)){
                    dp[j] = dp[j - 1] + 1;
                }
                else{
                    dp[j] = 0;
                }
                
                result = Math.max(result, dp[j]);
            }
        }
        
        return result;
    }
	
	public static void main(String[] args) {
//		String val = new Problems().optimalDivision(new int[] {6,2,3,4,5});
//		System.out.println(val);
		
		int r = new Problems().longestRepeatingSubstring("abacb");
		System.out.println(r);
	}
}
