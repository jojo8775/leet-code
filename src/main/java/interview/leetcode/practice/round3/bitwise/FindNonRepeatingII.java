package interview.leetcode.practice.round3.bitwise;

public class FindNonRepeatingII {
    public int find(int[] arr) {
        int num = 0;
        for (int i = 0; i < 32; i++) {
            int oneCount = 0;
            for (int j = 0; j < arr.length; j++) {
                oneCount += (1 << i & arr[j]);
            }

            num |= ((oneCount % 3) << i);
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(new FindNonRepeatingII().find(new int[] { 2, 1, 3, 4, 1, 2, 3, 1, 2, 3 }));
    }
}
