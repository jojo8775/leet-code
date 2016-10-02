package interview.leetcode.prob.paid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
Show Company Tags
Show Tags
Show Similar Problems

 * @author jojo
 *
 */
public class EncodeAndDecodeStrings {
	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			int i = 0;
			while (i < s.length()) {
				char ch = s.charAt(i);
				if (ch == '#') {
					// making '#' double for encoding
					i = encodeHash(sb, i, s);
				} else {
					sb.append(s.charAt(i));
					i++;
				}
			}

			sb.append(" # ");
		}

		return sb.toString();
	}

	private int encodeHash(StringBuilder sb, int i, String str) {
		int hashCount = 0;
		for (; i < str.length(); i++) {
			if (str.charAt(i) == '#') {
				sb.append('#');
				hashCount++;
			} else {
				break;
			}
		}

		while (hashCount > 0) {
			sb.append('#');
			hashCount--;
		}

		return i;
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> result = new ArrayList<String>();
		int i = 1, len = s.length();
		StringBuilder sb = new StringBuilder();
		char[] cArr = s.toCharArray();
		while (i < len - 1) {
			// creating a window
			char ch1 = cArr[i - 1];
			char ch2 = cArr[i];
			char ch3 = cArr[i + 1];

			// looking for ' # ' for string break
			if (ch1 == ' ' && ch2 == '#' && ch3 == ' ') {
				result.add(sb.toString());
				sb = new StringBuilder();
				i += 3;
			}
			// skipping duplicate '#'
			else {
				if (ch1 == '#' && ch2 == '#') {
					i++;
				}
				sb.append(ch1);
				i++;
			}
		}

		return result;
	}
	// Your Codec object will be instantiated and called as such:
	// Codec codec = new Codec();
	// codec.decode(codec.encode(strs));
	
	
	public static void main(String[] args) {
		EncodeAndDecodeStrings e = new EncodeAndDecodeStrings();
		// String es = e.encode(Arrays.asList("my a", "a as", " as # #"));
		String es = e.encode(Arrays.asList("", ""));
		List<String> ds = e.decode(es);
		for (String s : ds) {
			System.out.println(s + ", 1");
		}
	}
}
