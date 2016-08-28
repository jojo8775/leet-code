package interview.leetcode.prob;

/**
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10
 * ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number.
 * 
 * @author jojo
 *
 */
public class UglyNumberII
{
	public int find(int n)
	{
		int[] arr = new int[n];
		arr[0] = 1;

		int _2 = 0, _3 = 0, _5 = 0, idx = 1;
		System.out.print(arr[0]);

		while (n-- > 1)
		{
			arr[idx] = Math.min(arr[_2] * 2, Math.min(arr[_3] * 3, arr[_5] * 5));

			if (arr[idx] == arr[_2] * 2)
			{
				_2++;
			}
			if (arr[idx] == arr[_3] * 3)
			{
				_3++;
			}
			if (arr[idx] == arr[_5] * 5)
			{
				_5++;
			}

			System.out.print(", " + arr[idx]);
			idx++;
		}

		return arr[--idx];
	}
	
	public int find_2(int n){
		int[] primes = {2,3,5};
		int[] index = new int[primes.length];
		int[] ugly = new int[n];
		ugly[0] = 1;
		
		for(int i=1; i<n; i++){
			ugly[i] = Integer.MAX_VALUE;
			for(int j=0; j<primes.length; j++){
				ugly[i] = Math.min(ugly[i], ugly[index[j]] * primes[j]);
			}
			
			for(int j=0; j<primes.length; j++){
				if(ugly[i] == primes[j]*ugly[index[j]]){
					index[j] += 1;
				}
			}
		}
		
		return ugly[n-1];
	}
	
	public int nthUglyNumber(int n) {
		int[] arr = new int[n];
		arr[0] = 1;
		
		int[][] primes = new int[3][2];
		primes[0][0] = 2;
		primes[1][0] = 3;
		primes[2][0] = 5;
		
		for(int i=1; i<n; i++){
		    arr[i] = Integer.MAX_VALUE;
		    // create the next ugly number so that they can be kept track
		    for(int j=0; j<primes.length; j++){
		        arr[i] = Math.min(arr[i], primes[j][0] * arr[primes[j][1]]);
		    }
		    
		    //incrementing index of prime multiple for to calculate the next ugly number
		    for(int j=0; j<primes.length; j++){
		        if(arr[i] == primes[j][0] * arr[primes[j][1]]){
		            primes[j][1] += 1;
		        }
		    }
		}
		
		return arr[n - 1];
    }

	public static void main(String[] args)
	{
		new UglyNumberII().nthUglyNumber(11);
	}
}
