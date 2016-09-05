package interview.leetcode.prob;

public class NimGame {
	public boolean canWinNim(int n) {
		return n%4 != 0;
//        int count = 0;
//        boolean turn = true;
//        while(true){
//            if(turn){
//                count += 3;
//            }
//            else{
//                count+=1;
//            }
//            
//            if(count >= n){
//                break;
//            }
//            
//            turn = !turn;
//        }
//        
//        return turn;
    }
	
	public static void main(String[] args){
		System.out.println(new NimGame().canWinNim(1199886170));
	}
}
