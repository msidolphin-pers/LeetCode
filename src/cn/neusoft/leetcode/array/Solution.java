package cn.neusoft.leetcode.array;

import java.util.Arrays;

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
		直接用暴力算法，难度比上面的还低，可是不明白ac率才30多，还可以使用HashTable，因为这里是数组的解法，所以不在此处放出代码
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
