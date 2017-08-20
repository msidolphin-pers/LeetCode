package cn.neusoft.leetcode.string;

import org.junit.Test;

public class Solution {

	/*
	 * 14. Longest Common Prefix LCP最长公共前缀
	 * 
	 * Write a function to find the longest common prefix string amongst an array of strings.
	 */
	public String longestCommonPrefix(String[] strs) {
		if(strs.length <= 0) {
			return "";
		}
		//这道题如果用穷举肯定超时，首先需要两两找出最长前缀，然后要对这些最长前缀作出分析
        //其中最长前缀的共同点就是其中每一个字符在所有字符串中都会在同一个位置出现，那么没必要分析每一个前缀
        //以其中最短的字符串为基准 首先判断第一个字符是否相同，如果相等，则加入到sb中，否则break
		StringBuffer longestPrefix = new StringBuffer();
		//得到最短的长度
		int minLen = Integer.MAX_VALUE;
		for(int i = 0 ; i < strs.length ; ++i) {
			int strLen = strs[i].length();
			if(strLen < minLen ) {
				minLen = strs[i].length();
			}
		}
		int k = 0;
		//从第二个字符串开始
		for(int i = 0 ; i < minLen ; ++i) {
			boolean isCommon = true;	
			//取第一个字符串的字符作为基准
			char ref = strs[0].charAt(k);
			for(int j = 1 ; j < strs.length ; ++j) {
				if(strs[j].charAt(i) != ref) {
					isCommon = false;
					break;
				}
			}
			if(!isCommon) {
				break;
			}
			longestPrefix.append(ref);
			k++;
		}
		return longestPrefix.toString();
	}
	
	
	
	@Test
	public void testLCP() {
		System.out.println(longestCommonPrefix(new String[]{"ABCD", "ABEF", "ACEF"}));
	}
}
