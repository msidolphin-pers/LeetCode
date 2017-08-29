package cn.neusoft.leetcode.number;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
		public boolean isHappy(int n) {
	        Set<Integer> set = new HashSet<>();
	        set.add(n);
	        while(n!=1) {
	            int temp = n;
	            int sum = 0;
	            while(temp != 0) {
	                sum += (temp % 10) * (temp % 10);
	                temp /= 10;
	            }
	            n = sum;
	            if(set.contains(n)) {
	                return false;
	            }else {
	            	set.add(n);
	            }
	        }
	        return true;
	    }
	
		/*
		 * 求最长公共子序列递归算法
		 */
	 	public static int longestSubsequence(String str1, String str2) {
	        if(str1.length() == 0 || str2.length() == 0) {
	            return 0;
	         }
	         if(str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1)) {
	             return longestSubsequence(str1.substring(0,str1.length()-1), str2.substring(0,str2.length()-1)) + 1;
	             
	         }else {
	             int len1 = longestSubsequence(str1, str2.substring(0,str2.length()-1));
	             int len2 = longestSubsequence(str1.substring(0,str1.length()-1), str2);
	             return len1 > len2 ? len1 : len2;
	         }
     	}
	
		/*
		 * 求最长公共子序列迭代算法 
		 * 
		 * if(str1[i] == str2[j]) matrix[i][j] = matrix[i-1][j-1] + 1; //对应递归方式的减而治之 longestSubsequence(str1.substring(0,str1.length()-1), str2.substring(0,str2.length()-1)) + 1;
		 * else {
		 * 	matrix[i][j] = max(matrix[i-1][j], matrix[i][j-1]);	 //对应递归方式的分而治之
		 *  //int len1 = longestSubsequence(str1, str2.substring(0,str2.length()-1));
	          int len2 = longestSubsequence(str1.substring(0,str1.length()-1), str2);
	          return len1 > len2 ? len1 : len2;
		 * 
		 * }
		 * 
		 */
	 	public static int[][] longestSubsequence2(String str1, String str2) {
			 int[][] matrix = new int[str1.length()+1][str2.length()+1];
			 //init
			 for(int i = 0 ; i < matrix.length ; ++i) {
				 for(int j = 0 ; j < matrix[i].length ; ++j) {
					 matrix[i][j] = 0;
				 }
			 }
			 for(int i = 1 ; i < matrix.length ; ++i) {
				 for(int j = 1 ; j < matrix[i].length ; ++j) {
					 if(str1.charAt(i-1) == str2.charAt(j-1)) {
						 //上一个子序列的长度+1
						 matrix[i][j] = matrix[i-1][j-1] + 1;
					 }else {
						 matrix[i][j] = matrix[i-1][j] > matrix[i][j-1] ? matrix[i-1][j] : matrix[i][j-1];
					 }
				 }
			 }
			 //		 return matrix[str1.length()][str2.length()];
			 return matrix;
	 	}
	 
	 
		 /*
		  * 201. Bitwise AND of Numbers Range
		  * 
		  * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
	
			For example, given the range [5, 7], you should return 4.
		  * 
		  */
	 	public int rangeBitwiseAnd(int m, int n) {
	        //就是返回range内的所有数的相与结果
	        // return 5&6&7;
	        int result = m;
	        for(long i = m + 1 ; i <= n ; ++i) {
	            result &= i;
	            //0 & x = 0
	             if(result == 0) {
	                 return 0;
	             }
	        }
	        return (int) result;
	 	}
	 	
	 	public int rangeBitwiseAnd2(int m, int n) {
	        // 5 101
	        // 6 110
	        // 7 111
	        //可以观察出，只要求出高位最长前缀的位数，便能够很快的得出该范围内的相与结果
	        //记录右移次数 
	        int bit = 0;
	        while(m != n) {
	            //如果m=n，说明低位已经完全被移出了
	            m >>= 1;
	            n >>= 1;
	            bit++;
	        }
	        //归位
	        return m << bit; 
	    }
	 
	 	public int[] countBits(int num) {
	        int[] result = new int[num + 1];
	        for(int i = 0 ; i <= num ; ++i) {
	            result[i] = 0;
	        }
	        for(int i = 1 ; i <= num ; ++i) {
	            while(i != 0) {
	                i = i & (i - 1);
	                result[i]++;
	            } 
	        }
	        return result;
	 	}
	 	
	 	public int[] countBits2(int num) {
	        int[] result = new int[num + 1];
	        for(int i = 0 ; i <= num ; ++i) {
	            result[i] = 0;
	        }
	        for(int i = 1 ; i <= num ; ++i) {
	            //利用的是移除最右边的1的原理，无论是偶数还是奇数，都等于移除最右边的1的数值+1
	           result[i] = result[i & (i-1)] + 1;
	        }
	        return result;
	    }
	 
	  /*==========================N Queen===========================*/
	 	
	 	public List<List<String>> solveNQueens(int n) {
	        List<List<String>> result = new ArrayList<>();
	        int[] rows = new int[n+1];
	        for(int i = 0 ; i <= n ; ++i) {
	            rows[i] = 0;
	        }
	        dfs(result, 1, rows, 4);
	        return result;
	    }
	 
	    public void dfs(List<List<String>> result, int step, int[] rows, int n) {
	        if(step == n + 1) {
	            List<String> subString = new ArrayList<>();
	            for(int i = 1 ; i <= n ; ++i) {
	                String str = "";
	                for(int j = 1 ; j <= n ; ++j) {
	                    if(j == rows[i]) {
	                         str += "Q";
	                    }else {
                        	str += ".";
	                    }
	                }
	                subString.add(str);
	            }
	            result.add(subString);
	            return;
	        }
	        for(int i = 1 ; i <= n ; ++i) {
	            rows[step] = i;
	            if(isValid(rows, step)) {
	                dfs(result, step + 1, rows, n);
	            }
	        }
	    }
	    
	    public boolean isValid(int[] rows, int step) {
	        for(int i = 1 ; i < step ; ++i) {
	            if(rows[i] == rows[step]) {
	                return false;
	            }
	            // rows[step] - rows[i] / step - i = 1 or -1 45 degree
	            if((rows[step] - rows[i]) == Math.abs(step - i)) {
	                return false;
	            }
	        }
	        return true;
	    }
	    /*=========================================================*/
	    
	    public int minSubArrayLen(int s, int[] nums) {
	        int len = nums.length;
	        int min = nums.length + 1;
	        int left = 0;
	        int right = 0;
	        int sum = 0;
	        while(right < len) {
	           //首先移动指针使得left-right 的和大于等于s
	            while(right < len && sum < s) {
	                sum += nums[right++];
	            }
	            if(sum >= s) {
	                min = right - left  < min ? right - left : min;
	            }
	            //移动left指针，如果减去首元素的和仍然大于等于s，continue
	            while(left < right) {
	                sum -= nums[left++];
	                if(sum < s) {
	                    break;
	                }
	                min = right - left < min ? right-left : min;
	            }
	        }
	        //若min == nums.length + 1，说明整个数组不存在sum >= s 的子数组
	        return min == nums.length + 1 ? 0 : min;
	    }
	    
	    @Test
	    public void testMIn() {
	    	System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
	    }
}
