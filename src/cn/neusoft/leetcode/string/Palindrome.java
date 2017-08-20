package cn.neusoft.leetcode.string;

/**
 * 
* @Title: Palindrome.java 
* @Package cn.neusoft.leetcode.string 
* @Description: 回文类型
* @author msidolphin
* @date 2017年8月19日 下午11:51:41 
* @version V1.0
 */
public class Palindrome {

	/*
	 * 9. Palindrome Number 回文数
	 * 
		Some hints:
		Could negative integers be palindromes? (ie, -1)
		
		If you are thinking of converting the integer to string, note the restriction of using extra space.
		
		You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
		
		There is a more generic way of solving this problem.
	 * 
	 * 判断一个整数是否为回文数
	 */
	public boolean isPalindrome(int x) {
        if(x < 0) {
            //经过验证，负整数不可以是回文
            return false;
        }
        String str = "" + x;
        for(int i = 0 , j = str.length() - 1 ; i <= j ; ++i, --j) {
            if(str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
	
	/*
	 * 5. Longest Palindromic Substring
	 * 
	 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

		Example:
		
		Input: "babad"
		
		Output: "bab"
		
		Note: "aba" is also a valid answer.
		Example:
		
		Input: "cbbd"
		
		Output: "bb"
	 * 
	 * 最长回文子串
	 * 时间复杂度：O(n^3)
	 * 思路：获取每一个子串，然后判断是否为回文，求出长度...
	 * 超时
	 */
	public static String longestPalindrome(String s) {
        int max = 0;
        //初值为第一个字符，只有一个字符是边界情况
        String str = s.substring(0,1);
        for(int i = 0 ; i < s.length() ; ++i) {
            int start = i;
            for(int j = i + 1 ; j < s.length() ; ++j) {
                //判断是否为回文
                int end = j;
                boolean isPalindromic = true;
                for(int k = i, l = j; k < l ;) {
                	char c1 = s.charAt(k);
                	char c2 = s.charAt(l);
                    if(c1 != c2) {
                        isPalindromic = false;
                        break;
                    }
                    k++;
                    l--;
                }
                if(isPalindromic) {
                    int len = end - start + 1;
                    if(len > max) {
                        max = len;
                        //获取字串
                        str = s.substring(start, end + 1);
                    }
                }
            }
        }
        return str;
    }
	
	/*
	 * 中心扩展法
	 * 时间复杂度：O(n^2)
	 * 思路：无需获取每一个子串， 以每一个字符为中心(要分别考虑奇数情况和偶数情况)，分别向两边扩展，获取以此字符为中心的最长回文子串，判断是否为最长回文子串
	 */
	//获取最长回文串
	public static String getLongestPalindromeByCenterPoint(String s, int c1, int c2) {
		int len = s.length();
		int start = c1;
		int end = c2;
		while(start >= 0 && end < len && s.charAt(start) == s.charAt(end)) {
			start--;
			end++;
		}
		//由于上面的循环 ++ 了，所有需要重新定位开始点 
		start = start + 1;
		//substring中 第二个参数end + 1，所以无需调整
		return s.substring(start, end);
	}
		
	//中心扩展
	public static String longestPalindrome2(String s) {
		String longest = s.substring(0, 1); //初始化为字符串的第一个字符
		int maxLen = 0;
		int len = s.length();
		for(int i = 0 ; i < len - 1 ; ++i) {
			//奇数 就是以一个为中心点
			String s1 = getLongestPalindromeByCenterPoint(s, i, i);
			if(s1.length() > maxLen) {
				longest = s1;
			}
			//偶数 以双字符为中心点
			String s2 = getLongestPalindromeByCenterPoint(s, i, i+1);
			if(s2.length() > maxLen) {
				longest = s2;
			}
			//最大长度
			maxLen = longest.length();
		}
		return longest;
	}
	
}
