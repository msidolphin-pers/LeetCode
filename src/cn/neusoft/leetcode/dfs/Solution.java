package cn.neusoft.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
* @Title: Solution.java 
* @Package cn.neusoft.leetcode.dfs 
* @Description: 深度优先算法类型
* @author msidolphin
* @date 2017年8月20日 下午7:34:40 
* @version V1.0
 */
public class Solution {

	/*
	 * 17. Letter Combinations of a Phone Number
	 * 
	 * Given a digit string, return all possible letter combinations that the number could represent.

		A mapping of digit to letters (just like on the telephone buttons) is given below.	
		
		Input:Digit string "23"
		Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
		
		Note:
		Although the above answer is in lexicographical order, your answer could be in any order you want.
	 * 
	 */
	public List<String> letterCombinations(String digits) {
		String[] digitsMap = new String[]{
			"",
			"",
			"abc",
			"def",
			"ghi",
			"jkl",
			"mno",
			"pqrs",
			"tuv",
			"wxyz"
		};
		List<String> resultList = new ArrayList<>();
		//子结果
		char[] subResult = new char[digits.length()];
		dfs4LetterCombinations(resultList, 0, subResult, digits, digitsMap);
        return resultList;
    }
	
	private void dfs4LetterCombinations(List<String> resultList, int index, char[] subResult, String digits, String[] digitsMap) {
		if(index == digits.length()) {
			resultList.add(new String(subResult));
			return;
		}
		char digit = digits.charAt(index);
		for(int i = 0 ; i < digitsMap[digit - '0'].length() ; ++i) {
			subResult[index] = digitsMap[digit - '0'].charAt(i);
			dfs4LetterCombinations(resultList, index + 1, subResult, digits, digitsMap);
		}
	}
	
	@Test
	public void testLetterCombinations() {
		System.out.println(letterCombinations("12"));
	}
	
}
