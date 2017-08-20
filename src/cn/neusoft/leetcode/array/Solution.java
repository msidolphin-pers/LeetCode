package cn.neusoft.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.Test;

/**
 * 
* @Title: Solution.java 
* @Package cn.neusoft.leetcode.array 
* @Description: 数组题型 顺序从易到难
* @author msidolphin
* @date 2017年8月17日 下午5:16:09 
* @version V1.0
 */
public class Solution {

	/*
	 * 561. Array Partition I
	 * Given an array of 2n integers, your task is to group these integers into n pairs of integer, 
	 * say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible
	 * 
	 * Example 1:
	 *	Input: [1,4,3,2]
	 *	
	 *	Output: 4
	 *	Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
	 *	Note:
	 *	n is a positive integer, which is in the range of [1, 10000].
	 *	All the integers in the array will be in the range of [-10000, 10000].
	 *  2n个整数，将他们划分为n个2元组，使得他们中的较小者的和最大
	 *  思路：先将数组排序，将相邻的两个数中的小数相加即可
	 */
	public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0 ; i < nums.length ; i = i + 2) {
            sum += nums[i];
        }
        return sum;
    }
	
	/*
	 * 566. Reshape the Matrix
	 * 
	 * In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.
     *
	 * You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
	 *
	 * The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.
	 *
	 * If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
	 * 
	 * Input: 
			nums = 
			[[1,2],
			 [3,4]]
			r = 1, c = 4
		Output: 
			[[1,2,3,4]]
		Explanation:
		The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
	
	 * 
	 * Input: 
			nums = 
			[[1,2],
			 [3,4]]
			r = 2, c = 4
		Output: 
			[[1,2],
			 [3,4]]
		Explanation:
		There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
	 * 
	 * Note:
	 * The height and width of the given matrix is in range [1, 100].
	 * The given r and c are all positive.
	 * 
	 * 
	 * 一开始想过数组元素列数不想等的情况，看来是想多了
	 * 思路是把二维数组转化为一维数组，方便操作，如果原数组大小小于r*c则返回原数组 
	 * 技巧：一维数组和二维数组的关系                    二维数组列数  c: => 二维数组行号： index / c 二维数组列号: index % c 
	 */
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		int[][] result = new int[r][c];
		int origin_row = nums.length;
		int origin_col = nums[0].length;
		//仅当新数组个数小于等于原数组元素个数才能进行转化
		if(r*c <= origin_row*origin_col) {
			return nums;
		}
		//定义一个index，其实就是将原数组看作是一个一维数组
		int index = 0;
		//遍历原数组
		for(int i = 0 ; i < nums.length ; ++i) {
			for(int j = 0 ; j < nums[i].length ; ++j) {
				//根据一维数组和二维数组的下标关系构造新数组
				result[index / c][index % c] = nums[i][j];
				index++;
			}
		}
		return result;
    }
	
	/*
	 * 53. Maximum Subarray
	 * 最大子数组和
	 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

		For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
		the contiguous subarray [4,-1,2,1] has the largest sum = 6
	 * 
	 * 穷举
	 * 时间复杂度O(n^3)
	 * 思路：遍历每一个字符，以1-n的长度获取子串，求出最大和
	 * 超时
	 */
	public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        if(nums.length == 1) {
            return nums[0];
        }
        for(int i = 0 ; i < nums.length - 1 ; ++i) {
        	//k是子串长度 第一个元素开头的子串的长度1->n 第一个1->n-1
            for(int k = 1; k < nums.length - i ; ++k) {
                int count = 0;
                int sum = nums[i];
                for(int j = i + 1 ; j < nums.length ; ++j) {
                    sum += nums[j];
                    count++;
                    if(count == k) {
                        if(max < sum) {
                            max = sum;
                        }
                        count = 0;
                    }
                }
            }
        }
        return max;
    }
	
	/*
	 * 53. Maximum Subarray
	 * 穷举2
	 * 对穷举1的改善
	 * 超时
	 */
	public int maxSubArray2(int[] nums) {
		int max = Integer.MIN_VALUE;
        if(nums.length == 1) {
            return nums[0];
        }
        for(int i = 0 ; i < nums.length; ++i) {
            int sum = nums[i];
            if(sum > max) {
                max = sum;
            }
            for(int k = i + 1; k < nums.length ; ++k) {
                sum += nums[k];
                if(sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
	
	/*
	 * 53. Maximum Subarray
	 * Kadane算法
	 * 思路：如果加上某个数使得和小于0，那么最大和序列绝对不包含此数，从下一个位置重新获取子数组
	 */
    public int maxSubArray3(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0 ; i < nums.length ; ++i) {
            sum += nums[i];
            if(sum > max) {
                max = sum;
            }
            if(sum < 0) {
                //结果为负值，包含此值不可能出现最大和
                sum = 0; 
            }
        }
        return max;
    }
	
	
	/*
	 * 
	 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

		You may assume that each input would have exactly one solution, and you may not use the same element twice.
		
		Example:
		Given nums = [2, 7, 11, 15], target = 9,
		
		Because nums[0] + nums[1] = 2 + 7 = 9,
		return [0, 1].
		
		给定一个目标值，求出数组中nums[i] + nums[j] = target 的索引值i j
		假设有且仅有一个解
		直接用暴力算法，还可以使用HashTable，因为这里是数组的解法，所以不在此处放出代码
	 */
	public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
		for(int i = 0 ; i < numbers.length - 1 ; ++i) {
			for(int j = i + 1 ; j < numbers.length ; ++j) {
				if(numbers[i] + numbers[j] == target) {
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
		return result;
    }
	
	/*
	 * 排序双指针法 Sorting with Two Pointers
	 * 
	 * 复杂度
		O(n)空间 O(nlogn)时间

		思路
		首先将原数组复制一遍，对新数组进行排序。排序后将双指针指向头部与尾部元素，进行迭代。如果双指针指向元素之和大于目标和，则将尾部指针向前移一位，反之则将头部指针向后移一位，
		直到双指针指向元素之和等于目标和，记录这两个元素的值，然后再遍历一遍旧数组，找出这两个元素的下标。
	 */
	public int[] twoSum2(int[] numbers, int target) {
		int[] result = new int[2];
		int[] clone = numbers.clone();
		Arrays.sort(clone);
		int left = 0;
		int right = numbers.length - 1;
		Map<Integer, Integer> map = new HashMap<>();
		while(left <= right) {
			int sum = clone[left] + clone[right];
			if(sum == target) {
				//找出原来的下标
				//找出result[0]
				for(int i = 0 ; i < numbers.length ; ++i) {
					if(clone[left] == numbers[i]) {
						if(map.containsKey(i)) {
							continue;
						}
						map.put(i, i);
						result[0] = i;
						break;
					}
				}
				//找出result[1]
				for(int j = 0 ; j < numbers.length ; ++j) {
					if(clone[right] == numbers[j]) {
						if(map.containsKey(j)) {
							continue;
						}
						result[1] = j;
						break;
					}
				}
				break;
			}else {
				if(sum > target) {
					right--;
				}else {
					left++;
				}
			}
		}
		return result;
    }
	
	@Test
	public void testTwoSum() {
		int[] nums = new int[]{3,3};
		nums = twoSum2(nums, 6);
		for (int i : nums) {
			System.out.println(i);
		}
	}
	
	/*
	 * 6. ZigZag Conversion 之字形构造字符串
	 * 
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

		P   A   H   N
		A P L S I I G
		Y   I   R
		And then read line by line: "PAHNAPLSIIGYIR"
		Write the code that will take a string and make this conversion given a number of rows:
		
		string convert(string text, int nRows);
		convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	 */
	public String convert(String s, int numRows) {
		//行数小于等于1或者大于等于原字符串长度，可以直接返回该串
		if(numRows <= 1 || numRows >= s.length()) {
			return s;
		}
		StringBuilder stringBuilder = new StringBuilder();
        int len = s.length();
        char[][] nums = new char[numRows][len];
        int rows = 0;
        int cols = 0;
        for(int i = 0 ; i < len ; ++i) {
            nums[rows][cols] = s.charAt(i);
            //列数为偶数（从0开始）时，行数++，否则--
            if(cols % 2 == 0) {
                rows++;
            }else {
                rows--;
            }
            if(numRows > 2) {
            	//行数大于2的情况（一般情况）
            	if(rows == numRows || rows == 0) {
                    cols++;
                    //偶数列填满
                    if(cols % 2 == 0) {
                    	rows = 0;
                    }else {
                    	//奇数列的第一行不填充字符
                    	rows = numRows - 2;
                    }
                }
            }else if(numRows == 2) {
            	//行数为2的情况
            	if(rows >= 1|| rows <= 0) {
            		cols++;
            		if(rows >= 1) {
                        rows = 1;
                    }else {
                        rows = 0;
                    }
            	}
            }
            
        }
        //重新构造字符串
        for(int i = 0 ; i < nums.length ; ++i) {
            for(int j = 0 ; j < nums[i].length ; ++j) {
                if(nums[i][j] != 0) {
                	stringBuilder.append(nums[i][j]);
                }
            }
        }
        return stringBuilder.toString();
    }
	
	public String convert2(String s, int numRows) {
	    if(numRows<=1)return s;
	    //使用StringBuilder忽略列数的影响，无需再考虑该字符应该放在哪一行哪一列，只需考虑应该放到哪一行，上面考虑到列数导致最后写法过于复杂
	    StringBuilder[] sb=new StringBuilder[numRows];
	    for(int i=0;i<sb.length;i++){
	        sb[i]=new StringBuilder("");   //init every sb element **important step!!!!
	    }
	    //行数增量，如果是第一行，则行数自增，如果是最后一行，则需要自减
	    int incre=1;
	    int index=0;
	    for(int i=0;i<s.length();i++){
	        sb[index].append(s.charAt(i));
	        if(index==0){incre=1;}
	        if(index==numRows-1){incre=-1;}
	        index+=incre;
	    }
	    String re="";
	    for(int i=0;i<sb.length;i++){
	        re+=sb[i];
	    }
	    return re.toString();
	}
	
	
	/*
	 * 12. Integer to Roman
	 * 
	 * 	Given an integer, convert it to a roman numeral.

		Input is guaranteed to be within the range from 1 to 3999.
	 */
	 public String intToRoman(int num) {
        //关键是找出分界数
        //罗马数字共有7个，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）。
        //阿拉伯数字
        int[] nums = new int[]{1000, 900, 800, 700, 600, 500, 400, 100, 
                              90,80,70,60,50,40,10,9,8,7,6,5,4,1};
        //对应罗马数字
        String[] romanNums = new String[]{"M", "CM", "DCCC", "DCC", "DC", "D", "CD", "C",
                                          "XC", "LXXX", "LXX", "LX", "L", "XL", "X",
                                          "IX", "VIII", "VII", "VI", "V", "IV", "I"};
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < nums.length ; ++i) {
            while(num >= nums[i]) {
                num -= nums[i];
                stringBuilder.append(romanNums[i]);
            }
        }
        return stringBuilder.toString();
    }
	 
	/*
	 * 13. Roman to Integer
	 * 
	 * Given a roman numeral, convert it to an integer.

	   Input is guaranteed to be within the range from 1 to 3999.
	 */
	 public int romanToInt(String s) {
		//罗马数字共有7个，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）。
		//阿拉伯数字
        int[] nums = new int[]{1000, 500, 100, 50, 10, 5, 1};
        //对应罗马数字
        char[] romanNums = new char[]{'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int result = 0;
        int len = s.length();
        //"DCXXI" 左减右加
        for(int i = 0 ; i < len ; ) {
        	boolean flag = false;
        	for(int j = 0 ; j < romanNums.length ; ++j) {
        		char ch = s.charAt(i);
        		char right = 0;
        		if(i < len - 1){
        			right = s.charAt(i+1);
        		}
        		if(ch == romanNums[j]) {
        			//考虑减的情况  左减的数字有限制，仅限于I、X、C 左减只能有一位 左减时不可跨越一个位数
        			if(ch == 'I' && right == 'V') {
        				flag = true;
        				result += 4;
        			}else if(ch == 'I' && right == 'X') {
        				flag = true;
        				result += 9;
        			}else if(ch == 'X' && right == 'L') {
        				flag = true;
        				result += 40;
        			}else if(ch == 'X' && right == 'C') {
        				flag = true;
        				result += 90;
        			}else if(ch == 'C' && right == 'D') {
        				flag = true;
        				result += 400;
        			}else if(ch == 'C' && right == 'M' ) {
        				flag = true;
        				result += 900;
        			}else {
        				result += nums[j];
        			}
        			break;
        		} 
        	}
        	if(flag) {
        		i = i + 2;
        	}else {
        		i = i + 1;
        	}
        }
        return result;
    }
	 
	 /*
	  * 13. Roman to Integer 改进版
	  */
	 public int romanToInt2(String s) {
	    int result = 0;
	    int len = s.length();
	    int last = 1000;
	    for(int i = 0 ; i < len ; ++i) {
	    	char ch = s.charAt(i);
	    	int num = getIntByRoman(ch);
	    	//example: "MCMXCVI"
	    	//在罗马数字中，可能需要处理减法的情况，如果在右边发现比之前的数大，那么肯定是减法情形
	    	if(num > last) {
	    		//首先需要减去之前加上的数值，然后加上正确的数组 num - last
	    		result = result - last + (num - last);
	    	}else {
	    		result += num;
	    	}
	    	last = num;
	    }
	    return result;
	}
	 
	 public int getIntByRoman(char ch) {
		 switch(ch) {
		 	case 'M' : 
		 		return 1000;
		 	case 'D' :
		 		return 500;
		 	case 'C' :
		 		return 100;
		 	case 'L' :
		 		return 50;
		 	case 'X' :
		 		return 10;
		 	case 'V' :
		 		return 5;
		 	case 'I' :
		 		return 1;
		 	default :
		 		return 0;
		 }
	 }
	 
	 /*
	  * 11. Container With Most Water
	  * 
	  * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
	  * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
	  * which together with x-axis forms a container, such that the container contains the most water.

		Note: You may not slant the container and n is at least 2.
	  * 
	  * 题意：
	  * x轴上在1,2,...,n点上有许多垂直的线段，长度依次是a1, a2, ..., an。找出两条线段，使他们和x抽围成的面积最大。返回最大面积，不能倾斜容器（梯形或者长方形）
	  */
	 //解法1：暴力算法 超时
	 public int maxArea(int[] height) {
        int max = 0;
        int len = height.length;
        for(int i = 0 ; i < len - 1 ; ++i) {
            for(int j = 0 ; j < len ; ++j) {
                int h = height[i] < height[j] ? height[i] : height[j];
                int area = (j-i)*h;
                if(max < area) {
                    max = area;
                }
            }
        }
        return max;
    }
	 
	 //解法2：两点聚集
	 //面积较大的部分一般聚集在高度较高，距离较远的两点
	 public int maxArea2(int[] height) {
	    int max = 0;
        int len = height.length;
        for(int i = 0, j = len - 1 ; i <= j ;) {
            int h = height[i] < height[j] ? height[i] : height[j];
            int area = (j-i) * h;
            if(area > max) {
                max = area;
            }
            //如果左侧高度小于右侧高度，那么移动左侧指针
            //试想一下：如果移动右侧指针，那么两线距离减少，因为高度取决于高度低的一方，那么面积必然减少，不可能会出现面积更大的可能，而上面的暴力算法就做了很多无用功
            if(height[i] <= height[j]) {
                i++;
            }else {
                j--;
            }
        }
        return max;
    }
	 
	/*
	 * 15. 3Sum
	 * 
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

		Note: The solution set must not contain duplicate triplets.
		
		For example, given array S = [-1, 0, 1, 2, -1, -4],
		
		A solution set is:
		[
		  [-1, 0, 1],
		  [-1, -1, 2]
		]
	 * 
	 */
	//brute force [-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6]
    public List<List<Integer>> threeSum(int[] nums) {
    	//去重复的重要一步！
    	Arrays.sort(nums); 
        List<List<Integer>> resultList = new ArrayList<>();
        //除非重写HashSet，不然无法排重复
        HashSet<int[]> set = new HashSet<>();
        int[] sub = new int[3];
        for(int i = 0 ; i < nums.length - 2 ; ++i) {
            for(int j = i + 1 ; j < nums.length - 1; ++j) {
                for(int k = j + 1 ; k < nums.length ; ++k) {
                    //只有经过排序，才保证ijk的顺序是有序的，才能够使用标记判断是否重复过
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        sub[0] = nums[i];
                    	sub[1] = nums[j];
                    	sub[2] = nums[k];
                    	Arrays.sort(sub);
                    	set.add(sub);
                    }
                }
            }
        }
        for(int[] s : set) {
        	List<Integer> subList = new ArrayList<>();
        	for(int i : s) {
            	subList.add(i);
        	}
        	resultList.add(subList);
        }
        return resultList;
    }
    
    public int[] twoSumCommon(int[] numbers, int offset ,int target) {
    	boolean isFind = false;
		int[] result = new int[2];
		int[] clone = numbers.clone();
		Arrays.sort(clone);
		int left = offset + 1;
		int right = numbers.length - 1;
		Map<Integer, Integer> map = new HashMap<>();
		while(left <= right) {
			int sum = clone[left] + clone[right];
			if(sum == target) {
                isFind = true;
				//找出原来的下标
				//找出result[0]
				for(int i = 0 ; i < numbers.length ; ++i) {
					if(clone[left] == numbers[i]) {
						if(map.containsKey(i)) {
							continue;
						}
						map.put(i, i);
						result[0] = i;
						break;
					}
				}
				//找出result[1]
				for(int j = 0 ; j < numbers.length ; ++j) {
					if(clone[right] == numbers[j]) {
						if(map.containsKey(j)) {
							continue;
						}
                        map.put(j,j);
						result[1] = j;
						break;
					}
				}
				break;
			}else {
				if(sum > target) {
					right--;
				}else {
					left++;
				}
			}
		}
        if(isFind) {
            return result;
        }
		return null;
    }
    
    
    /*
     * 3SUM
     * 双指针法：将3SUM转化为2SUM
     */
    public List<List<Integer>> threeSum2(int[] nums) {
    	
    	Arrays.sort(nums); 
        List<List<Integer>> resultList = new ArrayList<>();
        for(int i = 0 ; i < nums.length - 2 ; ++i) {
//        	if(i > 0 && nums[i] == nums[i-1]) {
//        		continue;
//        	}
        	int target = -nums[i];
        	int[] result = twoSumCommon(nums, i, target);
        	if(result != null) {
	        	List<Integer> subResultList = new ArrayList<>();
	        	subResultList.add(nums[i]);
	        	subResultList.add(result[0]);
	        	subResultList.add(result[1]);
	        	resultList.add(subResultList);
        	}
        }
        
        return resultList;
    }
    
    
    @Test
    public void test3Sum() {
    	System.out.println(threeSum2(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
    }
    
    @Test
    public void testSet() {
    	HashSet set = new HashSet<>();
    	set.add(new Integer[]{1,2,3});
    	set.add(new Integer[]{1,2,3});
    	System.out.println(set);
    }
   
	
	@Test
	public void testRome() {
		System.out.println(romanToInt2("MCMXCVI"));
	}
	 
	
	@Test
	public void test() {
		System.out.println(convert2("PAYPALISHIRING", 3));
	}

	
	@Test
	public void testMartixReshape() {
		int[][] nums = new int[][]{{1,2}, {3,4}};
		int r = 1;
		int c = 4;
		nums = matrixReshape(nums, r, c);
		for(int i = 0 ; i < nums.length ; ++i) {
			for(int j = 0 ; j < nums[i].length ; ++j) {
				System.out.print(nums[i][j] + "	");
			}
			System.out.println();
		}
	}
				
}
