package interview.leetcode.prob;

/**
 * Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '
 * @author jojo
 *Feb 3, 201810:50:36 PM
 */
public class DesignCompressedStringIterator {
    public static class StringIterator{
        private int idx = 0, end;
        private String str;
        private Tuple tuple;
        
        public StringIterator(String compressedString) {
            this.str = compressedString;
            this.end = compressedString.length();
            this.tuple = getTuple();
        }
        
        public char next() {
            if(tuple == null){
                return ' ';
            }
            
            char result = tuple.ch;
            
            if(--tuple.count == 0){
                tuple = getTuple();
            }
            
            return result;
        }
        
        public boolean hasNext() {
            return tuple != null; 
        }
        
        private Tuple getTuple(){
            if(idx >= end){
                return null;
            }
            
            int i = idx + 1, count = 0;
            char letter = str.charAt(idx);
            
            while(i < end){
                char ch = str.charAt(i);
                
                if(ch >= '0' && ch <= '9'){
                    count *= 10;
                    count += (ch - '0');
                }
                else {
                    break;
                }
                
                i++;
            }
            
            idx = i;
            return new Tuple(count, letter);
        }
        
        private static class Tuple{
            char ch;
            int count;
            public Tuple(int count, char ch){
                this.ch = ch;
                this.count = count;
            }
        }
    }
}
