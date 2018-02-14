package interview.leetcode.prob;

/**
 * Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.

However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.

Example:
Input: [1000,100,10,2]
Output: "1000/(100/10/2)"
Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
since they don't influence the operation priority. So you should return "1000/(100/10/2)". 

Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
Note:

The length of the input array is [1, 10].
Elements in the given array will be in range [2, 1000].
There is only one optimal division for each test case.

 * @author jojo
 *Feb 14, 201810:18:03 AM
 */
public class OptimalDivision {
    public String optimalDivision(int[] nums) {
        if(nums.length == 0){
            return "";
        }
            
        return getMax(nums, 0, nums.length - 1).str;
    }
    
    private Result getMax(int[] arr, int beg, int end){
        Result r = new Result(Double.MIN_VALUE, "");
        
        if(beg == end){
            r.val = (double) arr[beg];
            r.str = arr[beg] + "";
        }
        else if(beg + 1 == end){
            r.val = (double) arr[beg] / (double) arr[end];
            r.str = arr[beg] + "/" + arr[end];
        }
        else{
            for(int i=beg; i<end; i++){
                Result num = getMax(arr, beg, i);
                Result den = getMin(arr, i+1, end);
                
                if(r.val < (num.val/den.val)){
                    r.val = num.val/den.val;
                    r.str = num.str + "/" + ((end - i > 1) ? "(" + den.str + ")" : den.str);
                }
            }
        }
        
        return r;
    }
    
    private Result getMin(int[] arr, int beg, int end){
        Result r = new Result(Double.MAX_VALUE, "");
        
        if(beg == end){
            r.val = (double) arr[beg];
            r.str = arr[beg] + "";
        }
        else if(beg + 1 == end){
            r.val = (double) arr[beg] / (double) arr[end];
            r.str = arr[beg] + "/" + arr[end];
        }
        else{
            for(int i=beg; i<end; i++){
                Result num = getMin(arr, beg, i);
                Result den = getMax(arr, i+1, end);
                
                if(r.val > (num.val/den.val)){
                    r.val = num.val/den.val;
                    r.str = num.str + "/" + ((end - i > 1) ? "(" + den.str + ")" : den.str);
                }
            }
        }
        
        return r;
    }
    
    private static class Result{
        double val;
        String str;
        public Result(double val, String str){
            this.val = val;
            this.str = str;
        }
    }
}
