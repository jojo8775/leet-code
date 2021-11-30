package interview.leetcode.prob;

public class RemoveMinimumAndMaximumFromArray {
    public int minimumDeletions(int[] nums) {
        int maxIdx = -1, minIdx = -1, len = nums.length;
        
        for(int i=0; i<len; i++){
            if(maxIdx == -1 || nums[maxIdx] < nums[i]){
                maxIdx = i;
            }
            
            if(minIdx == -1 || nums[minIdx] > nums[i]){
                minIdx = i;
            }
        }
        
        // System.out.println("id:" + minIdx + " val:" + nums[minIdx] + ", id:" + maxIdx + " val:" + nums[maxIdx] + ", len:" + len);
        
        int[] minCost = findCost(nums, minIdx, 0 , len);
        int[] maxCost = findCost(nums, maxIdx, 0 , len);
        
        if(minCost[0] == 0 || maxCost[0] == 0){
            return Math.max(minCost[1], maxCost[1]);
        }
        
        int cost = 0;
        
        if(maxCost[1] < minCost[1]){
            cost = maxCost[1];
            // System.out.println("max idx cost:" + cost);
            // System.out.println("new begidx:" + (maxCost[0] == -1 ? maxCost[1] : 0) + " newEnd idx:" + (maxCost[0] == -1 ? len : len - maxCost[1]));
            
             int[] aa = findCost(nums, minIdx, maxCost[0] == -1 ? maxCost[1] : 0, maxCost[0] == -1 ? len : len - maxCost[1]);
             // System.out.println("min idx cost" + aa[1]);
            
            cost += aa[1];
        }
        else{
            cost = minCost[1];
            // System.out.println("min idx cost:" + cost);
            cost += findCost(nums, maxIdx, minCost[0] == -1 ? minCost[1] : 0, minCost[0] == -1 ? len : len - minCost[1])[1];
        }
        
        return cost;        
    }
    
    private int[] findCost(int[] nums, int idx, int beg, int end){
        if(idx - beg + 1 > end - idx){
            return new int[]{1, end - idx};
        }
        else if(idx - beg + 1 == end - idx){
            return new int[]{0, idx - beg + 1};
        }
        
        return new int[]{-1, idx - beg + 1};
    }
}
