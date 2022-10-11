package interview.leetcode.prob;

import java.util.Stack;

public class MaximumNumberOfBooksYouCanTake {
    public long maximumBooks(int[] books) {
        int len = books.length;
        long[] dp = new long[len];

        // dp[i] represents max number of books that can be taken
        // between shelf 0 and shelf i (both inclusive)

        // use monotonic stack to populate dp array; for every index i,
        // find the nearest break point j < i such that books[i - j] <
        // books[i] - i + j

        // this becomes the restraining point for picking books as now
        // instead of picking (books[i] - i + j) books, we can only pick
        // books[i - j] books; so we will pick the maximum dp[j] books +
        // (books[i] + books[i] - 1 + books[i] - 2 + ... + books[i] - (i - j - 1))
        Stack<Integer> stack = new Stack<>();
        long maxBooks = 0;

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && books[stack.peek()] >= books[i] - i + stack.peek())
                stack.pop();

            // pick dp[j] books and (books[i] + books[i] - 1 + ... + books[i] -
            // (i - j - 1)) books, where j is the current stack top; the latter
            // expression can be rewritten as a difference of two n-summations
            long prevBooks = stack.isEmpty() ? 0 : dp[stack.peek()];
            
            // max book collection possible with the current number. Ex if the current bookshelf number is 5. Then max colleciton possible is 15.  
            long maxCurrentCollection = getSummation(books[i]);
            
            // max book collection possible excluding the begining of this series. Ex if current is 5 but the begin of series is 4 then max we can collect is 
            // summation of 5 - summation of 4 
            long maxCollectionExcludingFirst = getSummation(books[i] - i + (stack.isEmpty() ? -1 : stack.peek())); // -1 when stack is empty becuase index is 0 based. 
            
            dp[i] = prevBooks + maxCurrentCollection - maxCollectionExcludingFirst;

            maxBooks = Math.max(maxBooks, dp[i]);
            stack.push(i);
        }

        return maxBooks;
    }

    // this is a math function to find sum of sequence where different is 1 and the last number is known. 
    // ex: 1,2,3,4,5 = 15 
    // as per this function n = 5. 5 * (5+1)/6 = 15 
    private long getSummation(long n) {
        if (n < 0)
            return 0;

        return (n * (n + 1)) / 2;
    }
}
