package interview.leetcode.prob;

/**
 * There are two types of persons:

The good person: The person who always tells the truth.
The bad person: The person who might tell the truth and might lie.
You are given a 0-indexed 2D integer array statements of size n x n that represents the statements made by n people about each other. More specifically, statements[i][j] could be one of the following:

0 which represents a statement made by person i that person j is a bad person.
1 which represents a statement made by person i that person j is a good person.
2 represents that no statement is made by person i about person j.
Additionally, no person ever makes a statement about themselves. Formally, we have that statements[i][i] = 2 for all 0 <= i < n.

Return the maximum number of people who can be good based on the statements made by the n people.

 

Example 1:


Input: statements = [[2,1,2],[1,2,2],[2,0,2]]
Output: 2
Explanation: Each person makes a single statement.
- Person 0 states that person 1 is good.
- Person 1 states that person 0 is good.
- Person 2 states that person 1 is bad.
Let's take person 2 as the key.
- Assuming that person 2 is a good person:
    - Based on the statement made by person 2, person 1 is a bad person.
    - Now we know for sure that person 1 is bad and person 2 is good.
    - Based on the statement made by person 1, and since person 1 is bad, they could be:
        - telling the truth. There will be a contradiction in this case and this assumption is invalid.
        - lying. In this case, person 0 is also a bad person and lied in their statement.
    - Following that person 2 is a good person, there will be only one good person in the group.
- Assuming that person 2 is a bad person:
    - Based on the statement made by person 2, and since person 2 is bad, they could be:
        - telling the truth. Following this scenario, person 0 and 1 are both bad as explained before.
            - Following that person 2 is bad but told the truth, there will be no good persons in the group.
        - lying. In this case person 1 is a good person.
            - Since person 1 is a good person, person 0 is also a good person.
            - Following that person 2 is bad and lied, there will be two good persons in the group.
We can see that at most 2 persons are good in the best case, so we return 2.
Note that there is more than one way to arrive at this conclusion.
Example 2:


Input: statements = [[2,0],[0,2]]
Output: 1
Explanation: Each person makes a single statement.
- Person 0 states that person 1 is bad.
- Person 1 states that person 0 is bad.
Let's take person 0 as the key.
- Assuming that person 0 is a good person:
    - Based on the statement made by person 0, person 1 is a bad person and was lying.
    - Following that person 0 is a good person, there will be only one good person in the group.
- Assuming that person 0 is a bad person:
    - Based on the statement made by person 0, and since person 0 is bad, they could be:
        - telling the truth. Following this scenario, person 0 and 1 are both bad.
            - Following that person 0 is bad but told the truth, there will be no good persons in the group.
        - lying. In this case person 1 is a good person.
            - Following that person 0 is bad and lied, there will be only one good person in the group.
We can see that at most, one person is good in the best case, so we return 1.
Note that there is more than one way to arrive at this conclusion.
 

Constraints:

n == statements.length == statements[i].length
2 <= n <= 15
statements[i][j] is either 0, 1, or 2.
statements[i][i] == 2
Accepted
5,804
Submissions
13,568
 * @author jojo
 * Jan 25, 2022 10:44:06 PM
 */
public class MaximumGoodPeopleBasedOnStatements {
    public int maximumGood(int[][] statements) {
        int len = statements.length;
        
        int maxGoodPeople = 0;
        
        // if the number of people are 3 
        // the binary representation is 1000 to 1111 (igonring the left most bit)
        // we will iterate all the possible combinations of 1 and 0 
        for(int i = 1 << len; i < (1 << (len + 1)); i++){
            
            String prediction = Integer.toBinaryString(i).substring(1); // ignoring the left most charecter
            if(isPredictionCorrect(prediction, statements, len)){
                int curGoodPeople = Integer.bitCount(Integer.parseInt(prediction, 2));
                maxGoodPeople = Math.max(maxGoodPeople, curGoodPeople);
            }
        }
        
        return maxGoodPeople;
    }
    
    private boolean isPredictionCorrect(String prediction, int[][] statements, int len){
        for(int i=0; i<len; i++){
            // if the prediction considers person i to be bad there is nothing to validate for contradiciton.
            if(prediction.charAt(i) != '1'){
                continue;
            }
            
            // if the current person i is considered good then we need to validate for contradition. 
            // if the statement of the person i is same as prediction then there is no contradiction. 
            for(int j=0; j<len; j++){
                if(statements[i][j] == 2){
                    continue;
                }
                
                if((statements[i][j] == 1 && prediction.charAt(j) == '0') 
                   || (statements[i][j] == 0 && prediction.charAt(j) == '1')){
                    return false;
                }
            }
        }
        
        return true;
    }
}
