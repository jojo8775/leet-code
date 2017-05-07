package interview.leetcode.practice.round3.recursion;

public class MatchStickSquare {
    public boolean findSquare(int[] arr){
        int sum = 0;
        for(int n : arr){
            sum += n;
        }
        
        if(sum % 4 != 0){
            return false;
        }
        
        return dfs(new int[4], 0, arr, sum/4);
    }

    private boolean dfs(int[] sides, int idx, int[] arr, int target){
        if(sides[0] == sides[1] && sides[1] == sides[2] && sides[2] == sides[3] && idx == arr.length){
            return true;
        }
        
        if(idx == arr.length){
            return false;
        }
        
        
        for(int i=0; i<4; i++){
            if(sides[i] + arr[idx] > target){
                continue;
            }
            
            sides[i] += arr[idx];
            if(dfs(sides, idx + 1, arr, target)){
                return true;
            }
            sides[i] -= arr[idx];
        }
        
        return false;
    }
    
    public static void main(String[] args){
        System.out.println(new MatchStickSquare().findSquare(new int[]{1,1,3,2,2}));
        System.out.println(new MatchStickSquare().findSquare(new int[]{1,1,2,2,2}));
    }
}
