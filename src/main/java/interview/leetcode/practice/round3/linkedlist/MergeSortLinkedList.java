package interview.leetcode.practice.round3.linkedlist;

public class MergeSortLinkedList {
    public ListNode mergeSort(ListNode node){
        if(node == null || node.next == null){
            return node;
        }
        
        ListNode fast = node, slow = node;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        fast = slow.next;
        slow.next = null;
        slow = node;
        
        slow = mergeSort(slow);
        fast = mergeSort(fast);
        
        return merge(fast, slow);
    }

    private ListNode merge(ListNode n1, ListNode n2){
        ListNode n3 = new ListNode(0), cur = n3;
        while(n1 != null || n2 != null){
            if(n1==null){
                cur.next = n2;
                break;
            }
            else if(n2 == null){
                cur.next = n1;
                break; 
            }
            else{
                if(n1.val < n2.val){
                    cur.next = n1;
                    n1 = n1.next;
                }
                else{
                    cur.next = n2;
                    n2 = n2.next;
                }
                
                cur = cur.next;
            }
        }
        
        return n3.next;
    }
    
    private static class ListNode{
        int val;
        ListNode next = null;
        public ListNode(int val){
            this.val = val;
        }
    }
    
    public static void main(String[] args){
        int[] arr = createArr(2, 50, 50);
        
        ListNode head = new ListNode(0), cur = head;
        for(int n : arr){
            cur.next = new ListNode(n);
            cur = cur.next;
        }
        
        ListNode result = new MergeSortLinkedList().mergeSort(head.next);
        while(result != null){
            System.out.print(result.val + ", ");
            result = result.next;
        }
    }
    
    private static int[] createArr(int beg, int end, int size){
        int[] arr = new int[size];
        for(int i=0; i<size; i++){
            arr[i] = beg + (int)(Math.random() * (end - beg));
        }
        
        return arr;
    }
}
