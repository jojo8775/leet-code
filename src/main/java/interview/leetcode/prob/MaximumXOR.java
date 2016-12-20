package interview.leetcode.prob;

/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

 * @author jojo
 *
 */
public class MaximumXOR {
    public int findMaximumXOR_Trivial(int[] nums) {
        int maxXor = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int xor = nums[i] ^ nums[j];
                maxXor = Math.max(maxXor, xor);
            }
        }

        return maxXor;
    }

    // below solution is an efficient one
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();

        for (int n : nums) {
            trie.insert(n);
        }

        int result = 0;

        for (int n : nums) {
            result = Math.max(result, trie.queryXor(n));
        }

        return result;
    }

    private static class TrieNode {
        int val;
        TrieNode[] nodes = new TrieNode[2];
    }

    public static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(int prefix) {
            TrieNode currentNode = root;

            for (int i = 31; i >= 0; i--) {
                int bit = (prefix >> i) & 1;

                if (currentNode.nodes[bit] == null) {
                    currentNode.nodes[bit] = new TrieNode();
                }

                currentNode = currentNode.nodes[bit];
            }

            currentNode.val = prefix;
        }

        public int queryXor(int prefix) {
            TrieNode currentNode = root;

            for (int i = 31; i >= 0; i--) {
                int bit = (prefix >> i) & 1;

                if (currentNode.nodes[1 - bit] != null) {
                    currentNode = currentNode.nodes[1 - bit];
                } else if (currentNode.nodes[bit] != null) {
                    currentNode = currentNode.nodes[bit];
                }
            }

            return prefix ^ currentNode.val;
        }
    }

    public static void main(String[] args) {
        int result = new MaximumXOR().findMaximumXOR(new int[] { 3, 10, 5, 25, 2, 8 });
        System.out.println(result);
    }
}
