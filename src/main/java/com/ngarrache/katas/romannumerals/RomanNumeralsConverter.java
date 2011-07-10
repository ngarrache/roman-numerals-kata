package com.ngarrache.katas.romannumerals;

/**
 * @author Nizar Garrache
 * 
 * @since 10 juil. 2011
 */
public class RomanNumeralsConverter {

	private enum Symbol {
		M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40), X(10), IX(
				9), V(5), IV(4), I(1);

		private int arabic;
		private String roman;

		private Symbol(int arabic) {
			this.arabic = arabic;
			roman = toString();
		}
	}

	public String arabicToRoman(int arabic) {
		if (arabic < 0) {
			throw new IllegalArgumentException(
					"Can't convert negative number [" + arabic + "] ");
		}
		String result = "";
		for (Symbol symbol : Symbol.values()) {
			while (arabic >= symbol.arabic) {
				result += symbol.roman;
				arabic -= symbol.arabic;
			}
		}
		return result;
	}

	private int getArabicNumber(String roman) {
		int result = 0;
		for (Symbol symbol : Symbol.values()) {
			if (roman.equals(symbol.toString())) {
				return symbol.arabic;
			}
		}
		return result;
	}

	public int romanToArabic(String roman) {
		int result = 0;

		while (!roman.isEmpty()) {
			if (roman.length() > 1) {
				int arabic = getArabicNumber(roman.substring(0, 2));
				if (arabic != 0) {
					result += arabic;
					roman = roman.substring(2);
				} else {
					result += getArabicNumber(roman.substring(0, 1));
					roman = roman.substring(1);
				}
			} else {
				result += getArabicNumber(roman.substring(0, 1));
				roman = roman.substring(1);
			}
		}

		return result;
	}
}
