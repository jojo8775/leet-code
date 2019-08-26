package interview.leetcode.prob;

/**
 * Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 * @author jojo
 * Aug 26, 2019 12:03:25 AM
 */
public class ImplementMagicDictionary {
	class MagicDictionary {
		/** Initialize your data structure here. */
		TrieNode root;

		public MagicDictionary() {
			root = new TrieNode();
		}

		/** Build a dictionary through a list of words */
		public void buildDict(String[] dict) {
			for (String word : dict) {
				TrieNode cur = root;
				for (int i = 0; i < word.length(); i++) {
					char ch = word.charAt(i);
					
					if (cur.children[ch - 'a'] == null) {
						cur.children[ch - 'a'] = new TrieNode();
					}
					
					cur = cur.children[ch - 'a'];
				}
				
				cur.isWord = true;
			}
		}

		/**
		 * Returns if there is any word in the trie that equals to the given word after
		 * modifying exactly one character
		 */
		public boolean search(String word) {
			TrieNode cur = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				for (int j = 0; j < 26; j++) {
					if ((char) (j + 'a') == c || cur.children[j] == null) {
						continue;
					}
					
					if (helper(cur.children[j], word, i + 1)) {
						return true;
					}
				}
				if (cur.children[c - 'a'] == null) {
					return false;
				}
				
				cur = cur.children[c - 'a'];
			}
			
			return false;
		}

		public boolean helper(TrieNode cur, String word, int index) {
			for (int i = index; i < word.length(); i++) {
				char ch = word.charAt(i);
				
				if (cur.children[ch - 'a'] == null) {
					return false;
				}
				
				cur = cur.children[ch - 'a'];
			}
			return cur.isWord;
		}

		public class TrieNode {
			boolean isWord;
			TrieNode[] children;

			public TrieNode() {
				isWord = false;
				children = new TrieNode[26];
			}
		}
	}
}
