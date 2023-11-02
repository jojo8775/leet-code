package interview.leetcode.prob;

/**
 * You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].

The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions. If you skip question i, you get to make the decision on the next question.

For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
Return the maximum points you can earn for the exam.

 

Example 1:

Input: questions = [[3,2],[4,3],[4,4],[2,5]]
Output: 5
Explanation: The maximum points can be earned by solving questions 0 and 3.
- Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
- Unable to solve questions 1 and 2
- Solve question 3: Earn 2 points
Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.
Example 2:

Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
Output: 7
Explanation: The maximum points can be earned by solving questions 1 and 4.
- Skip question 0
- Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
- Unable to solve questions 2 and 3
- Solve question 4: Earn 5 points
Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.
 

Constraints:

1 <= questions.length <= 105
questions[i].length == 2
1 <= pointsi, brainpoweri <= 105
Accepted
6,706
Submissions
18,197
 * @author jojo
 * Jan 16, 2022 12:56:26 AM
 */
public class SolvingQuestionsWithBrainPower {
	public long mostPoints(int[][] questions) {
	     //return topdown(questions);
	     return bottomup(questions);
	 }
	 
	 private long bottomup(int[][] questions){
	     // states
	     long[] dp = new long[questions.length];
	     
	     for(int i=questions.length-1; i>=0; i--){
	         // base case
	         if(i == questions.length-1){
	             dp[i] = questions[i][0];
	         }
	         else{
	             // relation:
	             long taking = questions[i][0] + ((i + questions[i][1] + 1 < dp.length) ? dp[i + questions[i][1] + 1] : 0L);
	             long skipping = dp[i+1];
	             
	             dp[i] = Math.max(taking, skipping);
	         }
	     }
	     
	     return dp[0];
	 }
	 
	 private long topdown(int[][] questions){
	     return dp(questions, 0, new Long[questions.length]);
	 }
	
	 private long dp(int[][] questions, int idx, Long[] memo){
	     // base case
	     if(idx >= questions.length){
	         return 0L;
	     }
	
	     if(memo[idx] != null){
	         return memo[idx];
	     }
	
	     // relation:
	     long taking = questions[idx][0] + dp(questions, idx + questions[idx][1] + 1, memo);
	     long skipping = dp(questions, idx + 1, memo);
	
	     memo[idx] = Math.max(taking, skipping);
	
	     return memo[idx];
	 }
}
