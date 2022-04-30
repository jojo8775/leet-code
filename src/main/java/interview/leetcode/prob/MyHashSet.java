package interview.leetcode.prob;

/**
 * Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:

void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 

Example 1:

Input
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
Output
[null, null, null, true, false, null, true, null, false]

Explanation
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // return True
myHashSet.contains(3); // return False, (not found)
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // return True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // return False, (already removed)
 

Constraints:

0 <= key <= 106
At most 104 calls will be made to add, remove, and contains.
 * @author jojo
 * Apr 21, 2022 12:24:59 AM
 */
public class MyHashSet {
    private int size;
    private Node[] array;
    
    public MyHashSet() {
        this.size = 100;
        this.array = new Node[100];
    }
    
    public void add(int key) {
        int index = key % this.size;
        if(this.array[index]==null){
            this.array[index] = new Node(key);
            return;       
        } 
        else {
            Node p = this.array[index];
            
            while(p!=null && p.val!=key){
                p = p.next;
            }
            
            if(p!=null){
                return;
            }
            
            Node newHead = new Node(key);
            newHead.next = this.array[index];
            this.array[index] = newHead;
        }
    }
    
    public void remove(int key) {
        int index = key % this.size;
        
        if(this.array[index] == null){
            return;
        } 
        else {
            Node p = this.array[index];
            
            if(p.val == key){
                this.array[index] = p.next;
                return;
            }
            
            while(p.next!=null && p.next.val !=key){
                p = p.next;
            }
            
            if(p.next == null){
                return;
            }
            
            p.next = p.next.next;
        }
    }
    
    public boolean contains(int key) {
        int index = key % this.size;
        
        if(this.array[index] == null){
            return false;
        }
        
        Node p = this.array[index];
        
        while (p!=null && p.val!=key){
            p = p.next;
        }
        
        return p!=null;
        
    }
    
    private static class Node {
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }
}
