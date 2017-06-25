package interview.leetcode.practice.round4.arrays;

public class LetterCombinationOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> list1 = new ArrayList<>();
        if(digits.isEmpty()){
            return list1;
        }
        
        list1.add("");
        
        for(int i=0; i<digits.length(); i++){
            String str = getString((int) (digits.charAt(i) - '0'));
            List<String> list2 = new ArrayList<>();
            for(int j=0; j<list1.size(); j++){
                String entry = list1.get(j);
                for(char ch : str.toCharArray()){
                    list2.add(entry + ch);
                }
            }
            
            list1 = list2;
        }
        
        return list1;
    }
    
    private String getString(int num){
        if(num == 2){
            return "abc";
        }
        else if(num == 3){
            return "def";
        }
        else if(num == 4){
            return "ghi";
        }
        else if(num == 5){
            return "jkl";
        }
        else if(num == 6){
            return "mno";
        }
        else if(num == 7){
            return "pqrs";
        }
        else if(num == 8){
            return "tuv";
        }
        else if(num == 9){
            return "wxyz";
        }
        else{
            return "";
        }
    }
}
