package interview.leetcode.prob;

/**
 * Implement pow(x, n).
 * @author jojo
 *
 */
public class Pow {
	//this is an ideal sum for nlogn runtime
    public double myPow(double x, int n) {
        if(n == 0){
            return 1;
        }
        
        double result = myPow(x, n/2);
        if(n%2 == 0){
            return result * result;
        }
        
        else{
            return result * result * x;
        }
    }
}
