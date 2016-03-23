package interview.leetcode.tushar.dynamicprograming;

public class JobScheduling
{
	public static void main(String[] args){
		Job[] jobs = new Job[6];
		jobs[0] = new Job(1,3,5);
		jobs[1] = new Job(2,5,6);
		jobs[2] = new Job(4,6,5);
		jobs[3] = new Job(6,7,4);
		jobs[4] = new Job(5,8,11);
		jobs[5] = new Job(7,9,2);
		
		findMaxJob(jobs);
	}
	
	private static void findMaxJob(Job[] jobs){
		int[] profit = new int[jobs.length];
		
		profit[0] = jobs[0].profit;
		
		for(int i=1; i<jobs.length; i++){
			profit[i] = jobs[i].profit;
			for(int j=0; j<i; j++){
				if(jobs[j].endTime <= jobs[i].startTime){
					profit[i] = Math.max(profit[j] + jobs[i].profit, profit[i]);
				}
				else{
					profit[i] = Math.max(profit[j], profit[i]);
				}
			}
		}
		
		System.out.println(profit[profit.length - 1]);
	}
	
	private static class Job{
		public int startTime;
		public int endTime;
		public int profit;
		
		public Job(int startTime, int endTime, int profit){
			this.startTime = startTime;
			this.endTime = endTime;
			this.profit = profit;
		}
	}
}
