package interview.leetcode.practice.round3.binarysearch;

public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        if (num == 0) {
            return false;
        }

        int beg = 0, end = num / 4;
        while (beg < end) {
            int mid = beg + (end - beg) / 2;
            int val = mid * mid * mid * mid;
            if (val < num) {
                beg = mid + 1;
            } else {
                end = mid;
            }
        }

        return beg * beg * beg * beg == num;
    }
    
    public boolean isPowerOfFour_seconVersion(int num){
        if(num != 1 && num < 4){
            return false;
        }
        
        while(num >= 4){
            if(num%4 != 0){
                return false;
            }
            num /= 4;
        }
        
        return num == 1;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 30; i++) {
            System.out.println(i + " is power of four " + new PowerOfFour().isPowerOfFour_seconVersion(i));
        }
    }
}
