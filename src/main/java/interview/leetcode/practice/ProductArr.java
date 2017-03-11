package interview.leetcode.practice;

public class ProductArr {
    public int[] product(int[] arr){
        int[] arr1 = new int[arr.length];
        arr1[0] = 1;

        for(int i=1; i<arr.length; i++){
            arr1[i] = arr[i-1] * arr1[i-1];
        }

        int prev = 1;
        for(int i=arr.length - 1; i>=0; i--){
            int temp = arr[i];
            arr[i] = arr1[i] * prev;
            prev *= temp;
        }

        return arr;
    }
    
    public static void main(String[] args){
        int[] arr = new ProductArr().product(new int[] {1,2,3,4});
        
        for(int n : arr){
            System.out.print(n + ", ");
        }
    }
}
