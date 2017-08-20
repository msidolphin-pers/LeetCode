package cn.neusoft.leetcode.number;

import org.junit.Test;

/**
 * 
* @Title: Solution.java 
* @Package cn.neusoft.leetcode.number 
* @Description: 数字类型
* @author msidolphin
* @date 2017年8月20日 下午12:22:52 
* @version V1.0
 */
public class Solution {

	/*
	 * 7. Reverse Integer
	 * Reverse digits of an integer.

		Example1: x = 123, return 321
		Example2: x = -123, return -321
		
		click to show spoilers.
		
		Have you thought about this?
		Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
		
		If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
		
		Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
		
		For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
		
		Note:
		The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
		
		写算法不能过度依赖api
		
		123 和 321的关系
		(123 % 10) * 100 = 300
		(12 % 10) * 10 = 20;
		(1 % 10) * 1 = 1
		所以右边相加就等于倒置的原整数，我们需要获取原整数的位数（不准确的说法）即可
	 */
	public int reverseInteger(int x) {
		//-2147483648  2147483647
		if(x == Integer.MIN_VALUE) {
			return 0;
		}
		boolean isPositive = true;
		if(x < 0) {
			isPositive = false;
			x = -x;
		}
		//bit
		long bit = (long) Math.log10(x);
		//times : 10^bit
		long times = 0;
		long reverseNum = 0;
		while(x!=0) {
			times = (long) Math.pow(10, bit);
			reverseNum += (x % 10) * times;
			bit--;
			x /= 10;
		}
		reverseNum = isPositive ? reverseNum : -reverseNum;
		if(reverseNum > Integer.MAX_VALUE || reverseNum < Integer.MIN_VALUE) {
			reverseNum = 0;
		}
        return (int) reverseNum;
    }
	
	
	@Test
	public void test() {
		System.out.println(reverseInteger(-123));
	}
}
