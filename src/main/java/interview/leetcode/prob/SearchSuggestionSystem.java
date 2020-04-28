package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord is typed. 

 

Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Example 3:

Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
Example 4:

Input: products = ["havana"], searchWord = "tatiana"
Output: [[],[],[],[],[],[],[]]
 

Constraints:

1 <= products.length <= 1000
There are no repeated elements in products.
1 <= Σ products[i].length <= 2 * 10^4
All characters of products[i] are lower-case English letters.
1 <= searchWord.length <= 1000
All characters of searchWord are lower-case English letters.
 * @author jojo
 * Apr 27, 2020  9:56:32 PM
 */
public class SearchSuggestionSystem {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		Node root = new Node('\0');

		for (String product : products) {
			root = populate(root, product);
		}

		List<List<String>> result = new ArrayList<>();

		for (char ch : searchWord.toCharArray()) {
			List<String> entries = new ArrayList<>();

			if (root != null) {
				root = root.children[ch - 'a'];

				if (root != null) {
					while (!root.products.isEmpty()) {

						entries.add(0, root.products.poll());
					}
				}
			}

			result.add(entries);
		}

		return result;
	}

	private Node populate(Node root, String product) {
		Node current = root;

		for (char ch : product.toCharArray()) {
			if (current.children[ch - 'a'] == null) {
				current.children[ch - 'a'] = new Node(ch);
			}

			current = current.children[ch - 'a'];
			current.offer(product);
		}

		return root;
	}

	private static class Node {
		char ch;
		Node[] children = new Node[26];
		PriorityQueue<String> products = new PriorityQueue<>((a, b) -> b.compareTo(a));

		public Node(char ch) {
			this.ch = ch;
		}

		public void offer(String product) {
			products.offer(product);
			if (products.size() > 3) {
				products.poll();
			}
		}
	}
}
