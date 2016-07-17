package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
 * @author jojo
 *
 */
public class RepeatedDNASequence {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> words = new HashSet<Integer>();
        Set<Integer> repeatedWords = new HashSet<Integer>();
        char[] carrMap = new char[26];
        
        List<String> result = new ArrayList<String>();
        
        //assigning char map for A,C,G,T
        //carrMap['A'-'A'] = 0;
        carrMap['C'-'A'] = 1;
        carrMap['G'-'A'] = 2;
        carrMap['T'-'A'] = 3;
        
        for(int i=0; i<s.length() - 9; i++){
            int val = 0;
            for(int j=i; j<i+10; j++){
                //these bit manupulation is equivalent to hash code check
                val = val << 2;
                val = val | carrMap[s.charAt(j) - 'A'];
            }
            
            //if word exisits and havent been recorded as repeated
            if(!words.add(val) && repeatedWords.add(val)){
                result.add(s.substring(i, i+10));
            }
        }
        
        return result;
    }

    // to undestand this logic modify the code to take pattern length = 2
	public static void main(String[] args){
		List<String> result = new RepeatedDNASequence().findRepeatedDnaSequences("ACAC");
		System.out.println(result.size());
	}
}
