package interview.leetcode.prob;

/**
 * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"
 * @author jojo
 *Feb 14, 20183:47:22 PM
 */
public class SolveTheEquation {
    public String solveEquation_adv(String equation) {
        String[] equations = equation.split("=");
        
        int[] left = calculate(equations[0]), right = calculate(equations[1]);
        
        left[0] -= right[0];
        left[1] = right[1] - left[1];
        
        if(left[0] == 0 && left[1] == 0) {
        	return "Infinite solutions";
        }
        else if(left[0] == 0) {
        	return "No solution";
        }
        else {
        	return "x=" + left[1]/left[0];
        }
    }
	
	private int[] calculate(String str) {
		String[] tokens = str.split("(?=[-+])"); 
		int[] result =  new int[2];
		
		for (String token : tokens) {
			if (token.equals("+x") || token.equals("x")) {
				result[0]++;
			}
			else if (token.equals("-x")) {
				result[0]--;
			}
			else if (token.contains("x")) {
				result[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
			}
			else {
				result[1] += Integer.parseInt(token);
			}
		}
		return result;
	}
	
    public String solveEquation(String equation) {
        // for ease of calculation
        equation += "+";
        int sign = 1, i = 0, j = 0, var = 0, coeff = 0;
        boolean equalFound = false;

        while (i < equation.length()) {
            char ch = equation.charAt(i);

            if (ch == '+' || ch == '-' || ch == '=') {
                // if '=' is followed by '-' we need to ignore computing
                if (i > 0 && equation.charAt(i - 1) != '=') {
                    int[] t = getNumber(equation, j, i - 1, sign);

                    // if left of '=' add else subtract values of 'x'
                    coeff = equalFound ? coeff - t[1] : coeff + t[1];

                    // if left of '=' subtract else add values of constant
                    var = equalFound ? var + t[0] : var - t[0];

                    // record occurrence of '='
                    if (ch == '=') {
                        equalFound = true;
                        sign = 1;
                    }
                }

                j = i + 1;

                sign = (ch == '=' || ch == '+') ? 1 : -1;
            }

            i++;
        }

        if (var == 0 && coeff == 0) {
            return "Infinite solutions";
        }

        if (coeff == 0) {
            return "No solution";
        }

        return "x=" + (var / coeff);
    }

    private int[] getNumber(String str, int i, int j, int sign) {
        int num = 0;
        boolean coeff = false;
        if (str.charAt(j) == 'x') {
            j--;
            coeff = true;
        }

        if (i > j) {
            num = 1;
        }

        while (i <= j) {
            num *= 10;
            num += (str.charAt(i++) - '0');
        }

        num *= sign;

        return coeff ? new int[] { 0, num } : new int[] { num, 0 };
    }

    public static void main(String[] args) {
        System.out.println(new SolveTheEquation().solveEquation("x+5-3+x=6+x-2"));
        System.out.println(new SolveTheEquation().solveEquation("x=x"));
        System.out.println(new SolveTheEquation().solveEquation("-x=-1"));
    }
}
