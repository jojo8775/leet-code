package interview.leetcode.prob;

/**
 * Alice and Bob are playing a game. Initially, Alice has a string word = "a".

You are given a positive integer k. You are also given an integer array operations, where operations[i] represents the type of the ith operation.

Now Bob will ask Alice to perform all operations in sequence:

If operations[i] == 0, append a copy of word to itself.
If operations[i] == 1, generate a new string by changing each character in word to its next character in the English alphabet, and append it to the original word. For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".
Return the value of the kth character in word after performing all the operations.

Note that the character 'z' can be changed to 'a' in the second type of operation.

 

Example 1:

Input: k = 5, operations = [0,0,0]

Output: "a"

Explanation:

Initially, word == "a". Alice performs the three operations as follows:

Appends "a" to "a", word becomes "aa".
Appends "aa" to "aa", word becomes "aaaa".
Appends "aaaa" to "aaaa", word becomes "aaaaaaaa".
Example 2:

Input: k = 10, operations = [0,1,0,1]

Output: "b"

Explanation:

Initially, word == "a". Alice performs the four operations as follows:

Appends "a" to "a", word becomes "aa".
Appends "bb" to "aa", word becomes "aabb".
Appends "aabb" to "aabb", word becomes "aabbaabb".
Appends "bbccbbcc" to "aabbaabb", word becomes "aabbaabbbbccbbcc".
 

Constraints:

1 <= k <= 1014
1 <= operations.length <= 100
operations[i] is either 0 or 1.
The input is generated such that word has at least k characters after all operations.
Accepted
4,449
Submissions
18,493
 * 
 * Sep 30, 2024 - 8:30:52 AM
 * Jojo 
 */
public class FindTheKthCharacterInStringGameII {
	public char kthCharacter(long k, int[] operations) {
        // since every operation is making the array size 2x the before size,
        // need to find the highest pow of 2 which represents k
        // int i = (int) (Math.ceil(Math.log(k) / Math.log(2))) - 1;
        int i = (int) (Math.floor(Math.log(k) / Math.log(2)));

        int count = 0;

        // since k is 1 based
        while (k > 1) {
            // if initial arr = arr1 then in the next operation it will be arr = arr1 + arr2
            // here checking if k falls on the second half (arr2) 
            if (k > (1L << i)) {

                // if the operations in 1 then increment the count because if the char was 'a' in arr1
                // it will be 'b' in arr2
                if (operations[i] == 1) {
                    count++;
                }

                // removing the arr1 length from k since it lies on arr2
                k -= (1L << i);
            }

            // scoping down to the next bit
            i--;
        }

        return (char) ('a' + (count % 26));
    }
}
