package interview.leetcode.practice.round3.linkedlist;

public class InsertionSortLinkedList {
    public ListNode insertionSort(ListNode node){
        if(node == null || node.next == null){
            return node;
        }
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE), cur = node;
        
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = null;
            
            sort(dummy, cur);
            cur = temp;
        }
        
        return dummy.next;
    }
    
    private void sort(ListNode n1, ListNode n2){
        while(n1.next != null){
            if(n1.next.val < n2.val){
                n1 = n1.next;
            }
            else{
                break;
            }
        }
        
        if(n1.next == null){
            n1.next = n2;
        }
        else{
            ListNode temp = n1.next;
            n1.next = n2;
            n2.next = temp;
        }
    }
    
    private static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
    
    public static void main(String[] args){
//        int[] arr = createArr(2,10,5);
        int[] arr = {7,4,2,4,2,5,3,12};
        ListNode head = new ListNode(10), cur = head;
        for(int n : arr){
            cur.next = new ListNode(n);
            cur = cur.next;
        }
        
        ListNode result = new InsertionSortLinkedList().insertionSort(head.next);
        while(result != null){
            System.out.print(result.val + ", ");
            result = result.next;
        }
    }
    
    private static int[] createArr(int beg, int end, int size){
        int[] arr = new int[size];
        for(int i=0; i<size; i++){
            arr[i] = beg + (int) (Math.random() * (end - beg));
        }
        
        return arr;
    }
}
