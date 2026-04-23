package interview.leetcode.prob;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * You have the four functions:

printFizz that prints the word "fizz" to the console,
printBuzz that prints the word "buzz" to the console,
printFizzBuzz that prints the word "fizzbuzz" to the console, and
printNumber that prints a given integer to the console.
You are given an instance of the class FizzBuzz that has four functions: fizz, buzz, fizzbuzz and number. The same instance of FizzBuzz will be passed to four different threads:

Thread A: calls fizz() that should output the word "fizz".
Thread B: calls buzz() that should output the word "buzz".
Thread C: calls fizzbuzz() that should output the word "fizzbuzz".
Thread D: calls number() that should only output the integers.
Modify the given class to output the series [1, 2, "fizz", 4, "buzz", ...] where the ith token (1-indexed) of the series is:

"fizzbuzz" if i is divisible by 3 and 5,
"fizz" if i is divisible by 3 and not 5,
"buzz" if i is divisible by 5 and not 3, or
i if i is not divisible by 3 or 5.
Implement the FizzBuzz class:

FizzBuzz(int n) Initializes the object with the number n that represents the length of the sequence that should be printed.
void fizz(printFizz) Calls printFizz to output "fizz".
void buzz(printBuzz) Calls printBuzz to output "buzz".
void fizzbuzz(printFizzBuzz) Calls printFizzBuzz to output "fizzbuzz".
void number(printNumber) Calls printnumber to output the numbers.
 

Example 1:

Input: n = 15
Output: [1,2,"fizz",4,"buzz","fizz",7,8,"fizz","buzz",11,"fizz",13,14,"fizzbuzz"]
Example 2:

Input: n = 5
Output: [1,2,"fizz",4,"buzz"]
 

Constraints:

1 <= n <= 50
 
Seen this question in a real interview before?
1/6
Yes
No
Accepted
76,955/103K
Acceptance Rate
74.7%
 * 
 * chiranjeebnandy
 * Apr 22, 2026  2026  9:42:09 PM
 */
public class FizzBizzMultithreaded {
	class FizzBuzz {
	    private int n;

	    Semaphore nSem = new Semaphore(1);
	    Semaphore fSem = new Semaphore(0);
	    Semaphore bSem = new Semaphore(0);
	    Semaphore fbSem = new Semaphore(0);
	    int idx = 1;

	    public FizzBuzz(int n) {
	        this.n = n;
	    }

	    // printFizz.run() outputs "fizz".
	    public void fizz(Runnable printFizz) throws InterruptedException {
	        while(true){
	            fSem.acquire();
	            if(idx > n){
	                return;
	            }
	            
	            printFizz.run();
	            // idx++;

	            nextSignal();
	        }
	    }

	    // printBuzz.run() outputs "buzz".
	    public void buzz(Runnable printBuzz) throws InterruptedException {
	        while(true){
	            bSem.acquire();
	            if(idx > n){
	                return;
	            }
	            
	            printBuzz.run();
	            // idx++;
	            nextSignal();
	        }
	    }

	    // printFizzBuzz.run() outputs "fizzbuzz".
	    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
	        while(true){
	            fbSem.acquire();
	            if(idx > n){
	                return;
	            }
	            
	            printFizzBuzz.run();
	            // idx++;
	            nextSignal();
	        }
	    }

	    // printNumber.accept(x) outputs "x", where x is an integer.
	    public void number(IntConsumer printNumber) throws InterruptedException {
	        while(true){
	            nSem.acquire();
	            if(idx > n){
	                return;
	            }

	            printNumber.accept(idx);
	            // idx++;
	            nextSignal();
	        }
	    }

	    private void nextSignal(){
	        idx++;
	        if(idx > n){
	            // release all 
	            nSem.release();
	            fSem.release();
	            bSem.release();
	            fbSem.release();
	            return;
	        }

	        if(idx % 3 == 0 && idx % 5 == 0){
	            fbSem.release();
	        }
	        else if(idx % 3 == 0){
	            fSem.release();
	        }
	        else if(idx % 5 == 0){
	            bSem.release();
	        }
	        else{
	            nSem.release();
	        }
	    }
	}

	// class FizzBuzz {
//	     private int n;
//	     private int idx = 1;

//	     private ReentrantLock lock = new ReentrantLock();
//	     private Condition fizzCond     = lock.newCondition();
//	     private Condition bizzCond     = lock.newCondition();
//	     private Condition fizzBizzCond = lock.newCondition();
//	     private Condition intCond      = lock.newCondition();

//	     public FizzBuzz(int n) { this.n = n; }

//	     public void fizz(Runnable printFizz) throws InterruptedException {
//	         while (true) {
//	             lock.lock();
//	             try {
//	                 while (idx <= n && !(idx % 3 == 0 && idx % 5 != 0)) fizzCond.await();
//	                 if (idx > n) return;
//	                 printFizz.run();
//	                 idx++;
//	                 bizzCond.signal(); fizzBizzCond.signal(); intCond.signal();
//	             } finally { lock.unlock(); }
//	         }
//	     }

//	     public void buzz(Runnable printBuzz) throws InterruptedException {
//	         while (true) {
//	             lock.lock();
//	             try {
//	                 while (idx <= n && !(idx % 5 == 0 && idx % 3 != 0)) bizzCond.await();
//	                 if (idx > n) return;
//	                 printBuzz.run();
//	                 idx++;
//	                 fizzCond.signal(); fizzBizzCond.signal(); intCond.signal();
//	             } finally { lock.unlock(); }
//	         }
//	     }

//	     public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
//	         while (true) {
//	             lock.lock();
//	             try {
//	                 while (idx <= n && idx % 15 != 0) fizzBizzCond.await();
//	                 if (idx > n) return;
//	                 printFizzBuzz.run();
//	                 idx++;
//	                 fizzCond.signal(); bizzCond.signal(); intCond.signal();
//	             } finally { lock.unlock(); }
//	         }
//	     }

//	     public void number(IntConsumer printNumber) throws InterruptedException {
//	         while (true) {
//	             lock.lock();
//	             try {
//	                 while (idx <= n && (idx % 3 == 0 || idx % 5 == 0)) intCond.await();
//	                 if (idx > n) return;
//	                 printNumber.accept(idx);
//	                 idx++;
//	                 fizzCond.signal(); 
//	                 bizzCond.signal(); 
//	                 fizzBizzCond.signal();
//	             } finally {
//	                  lock.unlock(); 
//	             }
//	         }
//	     }
	// }
}
