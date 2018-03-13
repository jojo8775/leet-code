package interview.leetcode.practice;

import java.util.Stack;

public class Pract3 {
    public boolean isBalanced(String s) {
        Stack<Integer> obIdxStack = new Stack<>(), starIdxStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                starIdxStack.push(i);
            } else if (ch == '(') {
                obIdxStack.push(i);
            } else {
                if (obIdxStack.size() > 0) {
                    obIdxStack.pop();
                } else {
                    if (starIdxStack.isEmpty()) {
                        return false;
                    }
                    starIdxStack.pop();
                }
            }
        }

        while (!obIdxStack.isEmpty()) {
            int top1 = obIdxStack.pop();
            if (starIdxStack.isEmpty() || starIdxStack.peek() < top1) {
                return false;
            }
            starIdxStack.pop();
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced(""));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("()"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("(*)"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("(*)(*)"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("*"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("**"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("*)"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("*(((()())()))())"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("****()))))"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("****())))"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("*(((()())())*())"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("(*)(*)(**"));
        System.out.println("Expected : true   Actual : " + new Pract3().isBalanced("*(((()())())())"));

        System.out.println("Expected : false   Actual : " + new Pract3().isBalanced("*()("));
        System.out.println("Expected : false   Actual : " + new Pract3().isBalanced("**()("));
        System.out.println("Expected : false   Actual : " + new Pract3().isBalanced("**(**)*("));
        System.out.println("Expected : false   Actual : " + new Pract3().isBalanced(")**"));
        System.out.println("Expected : false   Actual : " + new Pract3().isBalanced("****())))))"));
        System.out.println("Expected : false   Actual : " + new Pract3().isBalanced("***********************(((((()"));
        System.out.println("Expected : false   Actual : " + new Pract3().isBalanced("(((()())()))())"));

    }

    // @Test
    // void isBalanced() {
    // assertTrue(Main.isBalanced(""));
    // assertTrue(Main.isBalanced("()"));
    // assertTrue(Main.isBalanced("(*)"));
    // assertTrue(Main.isBalanced("(*)(*)"));
    //
    // assertTrue(Main.isBalanced("*"));
    // assertTrue(Main.isBalanced("**"));
    // assertTrue(Main.isBalanced("*)"));
    //
    // assertTrue(Main.isBalanced("*(((()())()))())"));
    //
    // assertFalse(Main.isBalanced("*()("));
    // assertFalse(Main.isBalanced("**()("));
    // assertFalse(Main.isBalanced("**(**)*("));
    // assertFalse(Main.isBalanced(")**"));
    // assertTrue(Main.isBalanced("****()))))"));
    // assertTrue(Main.isBalanced("****())))"));
    // assertFalse(Main.isBalanced("****())))))"));
    // assertFalse(Main.isBalanced("***********************(((((()"));
    //
    //
    // assertFalse(Main.isBalanced("(((()())()))())"));
    // assertTrue(Main.isBalanced("*(((()())())*())"));
    // }
    //
    // @Test
    // void isBalanced5() {
    // assertTrue(Main.isBalanced("(*)(*)(**"));
    // }
    //
    //
    // @Test
    // void isBalanced10() {
    // assertTrue(Main.isBalanced("*(((()())())())"));
    // }
}
