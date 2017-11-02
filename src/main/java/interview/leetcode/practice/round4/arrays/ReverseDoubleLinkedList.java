package interview.leetcode.practice.round4.arrays;

public class ReverseDoubleLinkedList {
    
    public LinkedList reverse(LinkedList l){
        LinkedList prev = null, cur = l;
        
        while(cur != null){
            LinkedList temp = cur.next;
            cur.next = prev;
            cur.prev = null;
            if(prev != null){
                prev.prev = cur;
            }
            
            prev = cur;
            cur = temp;
        }
        
        return prev;
    }
    
    public static void main(String[] args){
        LinkedList l1 = create(new int[]{1,2,3,4,5});
        
        LinkedList l2 = new ReverseDoubleLinkedList().reverse(l1);
        while(l2 != null){
            System.out.print(l2.val + ", ");
            l2 = l2.next;
        }
        
        System.out.println();
    }
    
    private static LinkedList create(int[] arr){
        LinkedList ref = new LinkedList(arr[0]), prev = ref, cur = null;
        
        for(int i=1; i<arr.length; i++){
            cur = new LinkedList(arr[i]);
            prev.next = cur;
            cur.prev = prev;
            
            prev = cur;
        }
        
        return ref;
    }
    
    private static class LinkedList{
        LinkedList prev = null, next = null;
        int val;
        public LinkedList(int val){
            this.val = val;
        }
    }
}
