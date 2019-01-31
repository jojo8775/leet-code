package interview.leetcode.practice.round2;

public class NumberToEnglish {
	public String numberToWords(int num) {
		int billion = 1000000000;
		int million = 1000000;
		int thousand = 1000;
		
		StringBuilder sb = new StringBuilder();
		if(num >= billion) {
			sb.append(convert3Digit(num/billion)).append("Billion ");
			num %= billion;
		}
		
		if(num >= million) {
			sb.append(convert3Digit(num/million)).append("Million ");
			num %= million;
		}
		
		if(num >= thousand) {
			sb.append(convert3Digit(num/thousand)).append("Thousand ");
			num %= thousand;
		}
		
		sb.append(convert3Digit(num));
		
		if(sb.length() == 0) {
			return "Zero";
		}
		
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	private String convert3Digit(int num) {
		StringBuilder sb = new StringBuilder();
		
		if(num >= 100) {
			sb.append(getWord(num/100)).append("Hundred ");
			num %= 100;
		}
		
		if(num >= 10) {
			int tens = num / 10;
			if(tens == 1) {
				sb.append(getWord(num));
				return sb.toString();
			}
			else {
				sb.append(getWord(tens * 10));
			}
			
			num %= 10;
		}
		
		if(num > 0) {
			sb.append(getWord(num));
		}
		
		return sb.toString();
	}

	private String getWord(int num) {
		switch (num){
			case 1: return "One ";
			case 2: return "Two ";
			case 3: return "Three ";
			case 4: return "Four ";
			case 5: return "Five ";
			case 6: return "Six ";
			case 7: return "Seven ";
			case 8: return "Eight ";
			case 9: return "Nine ";
			case 10: return "Ten ";
			case 20: return "Twenty ";
			case 30: return "Thirty ";
			case 40: return "Forty ";
			case 50: return "Fifty ";
			case 60: return "Sixty ";
			case 70: return "Seventy ";
			case 80: return "Eighty ";
			case 90: return "Ninety ";
			case 11: return "Eleven ";
			case 12: return "Twelve ";
			case 13: return "Thirteen ";
			case 14: return "Fourteen ";
			case 15: return "Fifteen ";
			case 16: return "Sixteen ";
			case 17: return "Seventeen ";
			case 18: return "Eighteen ";
			case 19: return "Nineteen ";
			default : return "";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new NumberToEnglish().numberToWords(1981));
		System.out.println(new NumberToEnglish().numberToWords(324135912));
		System.out.println(new NumberToEnglish().numberToWords(1234567891));
	}
}
