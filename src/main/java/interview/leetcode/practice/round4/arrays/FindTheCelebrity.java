package interview.leetcode.practice.round4.arrays;

public class FindTheCelebrity {
    public int findCelebrity(int n) {
        int assumedCelebrity = 0;

        for (int i = 1; i < n; i++) {
            if (!knows(i, assumedCelebrity)) {
                assumedCelebrity = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if ((i != assumedCelebrity) && (!knows(i, assumedCelebrity) || knows(assumedCelebrity, i))) {
                return -1;
            }
        }

        return assumedCelebrity;
    }

    // this a mock function from the problem statement
    private boolean knows(int a, int b) {
        return false;
    }
}
