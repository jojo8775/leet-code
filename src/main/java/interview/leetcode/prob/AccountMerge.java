package interview.leetcode.prob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
Accepted
86,041
Submissions
176,970
 * @author jojo
 * Aug 5, 2020  12:13:18 AM
 */
public class AccountMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToAccountMap = new HashMap<>();
        Map<String, String> emailParentMap = new HashMap<>();
        Map<String, TreeSet<String>> union = new HashMap<>();
        
        for(List<String> account : accounts){
            for(int i=1; i<account.size(); i++){
                emailParentMap.put(account.get(i), account.get(i));
                emailToAccountMap.put(account.get(i), account.get(0));
            }
        }
        
        for(List<String> account : accounts){
            String parentEmail = account.get(1);
            for(int i=2; i<account.size(); i++){
                emailParentMap.put(findParent(emailParentMap, account.get(i)), parentEmail);
            }
        }
        
        for(List<String> account : accounts){
            for(int i=1; i<account.size(); i++){
                String parent = findParent(emailParentMap, account.get(i));
                TreeSet<String> emailSet = union.get(parent);
                
                if(emailSet == null){
                    emailSet = new TreeSet<String>();
                    union.put(parent, emailSet);
                }
                
                emailSet.add(account.get(i));
            }
        }
        
        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<String, TreeSet<String>> entry : union.entrySet()){
            List<String> resultEntry = new ArrayList<>();
            resultEntry.addAll(entry.getValue());
            resultEntry.add(0, emailToAccountMap.get(entry.getKey()));
            
            result.add(resultEntry);
        }
        
        return result;
    }
    
    private String findParent(Map<String, String> emailParentMap, String email){
        while(!emailParentMap.get(email).equals(email)){
            String parent = emailParentMap.get(email);
            emailParentMap.put(email, emailParentMap.get(parent));
            email = parent;
        }
        
        return email;
    }
}
