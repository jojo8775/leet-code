package interview.leetcode.practice.round2;

public class AmazonRejectionInterview {
    public int findCount(int a, int b, int c, int d){
        int[] arr = new int[]{a, b, c, d};
        int[] result = {0};
        
        dfs(arr, result, 0, 0);
        
        return result[0];
    }
    
    private void dfs(int[] arr, int[] result, int idx, int sum){
        if(sum > 15 || (sum != 15 && idx == arr.length)){
            return;
        }
        
        if(sum == 15){
            result[0]++;
            return;
        }
        
        for(int i=idx; i<arr.length; i++){
            dfs(arr, result, i + 1, sum + arr[i]);
        }
    }
    
    public static void main(String[] args){
        System.out.println(new AmazonRejectionInterview().findCount(5, 3, 2, 5));
    }
}
