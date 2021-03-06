package interview.leetcode.prob;

/**
 * At a lemonade stand, each lemonade costs $5. 

Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).

Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.

Note that you don't have any change in hand at first.

Return true if and only if you can provide every customer with correct change.

 

Example 1:

Input: [5,5,5,10,20]
Output: true
Explanation: 
From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we give a $10 bill and a $5 bill.
Since all customers got correct change, we output true.
Example 2:

Input: [5,5,10]
Output: true
Example 3:

Input: [10,10]
Output: false
Example 4:

Input: [5,5,10,10,20]
Output: false
Explanation: 
From the first two customers in order, we collect two $5 bills.
For the next two customers in order, we collect a $10 bill and give back a $5 bill.
For the last customer, we can't give change of $15 back because we only have two $10 bills.
Since not every customer received correct change, the answer is false.
 

Note:

0 <= bills.length <= 10000
bills[i] will be either 5, 10, or 20.

 * @author jojo
 * Dec 5, 2018 7:21:53 PM
 */
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int fiveBills = 0, tenBills = 0;
        
        for(int i=0; i<bills.length; i++){
            // if bill == 5 there is no change needed.
            if(bills[i] == 5){
                fiveBills++;
            }
            // if bill == 10, need to return 5
            else if(bills[i] == 10){
                tenBills++;
                fiveBills--;
            }
            // if bill = 20, and 10$ bill count is greater then 0, then can pay back with 10$ and 5$ bill
            else if(tenBills > 0){
                tenBills--;
                fiveBills--;
            }
            // if bill = 20, and there is no 10$ bill, then can pay back with three 5$ bill.
            else{
                fiveBills -= 3;
            }
            
            // if there is a negative 5$ bill, this means no change available.
            if(fiveBills < 0){
                return false;
            }
        }
        
        return true;
    }
}
