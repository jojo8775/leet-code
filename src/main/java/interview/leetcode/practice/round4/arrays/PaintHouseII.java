package interview.leetcode.practice.round4.arrays;

public class PaintHouseII {
    public int minCostII(int[][] costs) {
        int prevCol = -1, prevMin1 = 0, prevMin2 = 0;
        
        for(int i=0; i<costs.length; i++){
            int curCol = -1, curMin1 = -1, curMin2 = -1;
            
            for(int j=0; j<costs[i].length; j++){
                int cost = costs[i][j] + (j != prevCol ? prevMin1 : prevMin2);
                if(curMin1 < 0 || curMin1 > cost){
                    curCol = j;
                    curMin2 = curMin1;
                    curMin1 = cost;
                }
                else if(curMin2 < 0 || curMin2 > cost){
                    curMin2 = cost;
                }
            }
            
            prevMin1 = curMin1;
            prevMin2 = curMin2;
            prevCol = curCol;
        }
        
        return prevMin1;
    }
}
