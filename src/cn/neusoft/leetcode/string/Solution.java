package cn.neusoft.leetcode.string;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/*
	 * 8. String to Integer (atoi)
	 * 
	 * 	Implement atoi to convert a string to an integer.

		Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
		
		Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
		
		Update (2015-02-10):
		The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
		
		Requirements for atoi:
		The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
		
		The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
		
		If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
		
		If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
	 
	 
	 * 字符串转为整数是很常用的一个函数，由于输入的是字符串，所以需要考虑的情况有很多种
	 * 
		1. 若字符串开头是空格，则跳过所有空格，到第一个非空格字符，如果没有，则返回0.
		
		2. 若第一个非空格字符是符号+/-，则标记sign的真假，这道题还有个局限性，那就是在c++里面，+-1和-+1都是认可的，都是-1，而在此题里，则会返回0.
		
		3. 若下一个字符不是数字，则返回0. 完全不考虑小数点和自然数的情况，不过这样也好，起码省事了不少。
		
		4. 如果下一个字符是数字，则转为整形存下来，若接下来再有非数字出现，则返回目前的结果。
		
		5. 还需要考虑边界问题，如果超过了整形数的范围，则用边界值替代当前值 
		
		！如果是负数，溢出，那么返回最小值，反之
		
		这题不难，但是要考虑很多情况...
	 * 
	 * 
	 */
	public int myAtoi(String str) {
        if(str == null || "".equals(str.trim())) {
            return 0;
        }
        str = str.trim();
        int len = str.length();
        if(len < 1) {
            return 0;
        }
        //结果
        long res = 0;
        //倍数
        long times = 10;
        //记录有效位数
        int count = 0;
        for(int i = 0 ; i < len ; ++i) {
        	if(i == 0 && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
        		continue;
        	}
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                //对于过分长的有效输入（数字）进行过滤，因为已经溢出了
                if(count > 10) {
                    break;
                }
                res = res*times + (ch - '0');
                count++;
            }else {
                break;
            }
        }
        char sign = str.charAt(0);
        //处理溢出
        if(res > Integer.MAX_VALUE) {
            if(sign == '-') {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
        if(res < Integer.MIN_VALUE) {
            if(sign == '-') {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
        //没有溢出的情况
        if(sign == '-') {
            return (int) -res;
        }
        return (int) res;
        // return Integer.parseInt(str);
    }
	
	
	public Map<Integer, Integer> twoSum(int[] numbers, int offset ,int target) {
    	boolean isFind = false;
		int[] result = new int[2];
		int[] clone = numbers.clone();
		//Arrays.sort(clone);
		int left = offset + 1;
		int right = numbers.length - 1;
		Map<Integer, Integer> map = new HashMap<>();
        Integer last = 10000; 
		while(left < right) {
			int sum = clone[left] + clone[right];
			if(sum == target && left != last) {
                last = left;
                isFind = true;
				//找出原来的下标
				//找出result[0]
				for(int i = offset+1 ; i < numbers.length ; ++i) {
					if(clone[left] == numbers[i]) {
						if(map.get(i) != null) {
							continue;
						}
						map.put(i, i);
						result[0] = i;
						break;
					}
				}
				//找出result[1]
				for(int j = offset+1 ; j < numbers.length ; ++j) {
					if(clone[right] == numbers[j]) {
						if(map.containsKey(j)) {
							continue;
						}
                        map.put(result[0], j);
						result[1] = j;
						break;
					}
				}
				right = numbers.length - 1;
                //去重复
                int index;
                for(index = left + 1; index <numbers.length && numbers[left] == numbers[index] ; ++index) {
                    
                }
                left = index;
			}else {
				if(sum > target) {
					right--;
				}else {
					left++;
				}
			}
		}
        if(isFind) {
            return map;
        }
		return null;
    }
    
    
    /*
     * 3SUM
     * 双指针法：将3SUM转化为2SUM
     */
    public List<List<Integer>> threeSum(int[] nums, int offset, int target) {
    	//Arrays.sort(nums); 
        List<List<Integer>> resultList = new ArrayList<>();
        for(int i = 0 ; i < nums.length - 2 ; ++i) {
            //去重复
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
        	Map<Integer, Integer> result = twoSum(nums, i, target - nums[i]);
        	if(result != null) {
                for(Map.Entry<Integer, Integer> entry : result.entrySet()) {
                   List<Integer> subResultList = new ArrayList<>(); 
                    subResultList.add(nums[i]);
                    subResultList.add(nums[entry.getKey()]);
	        	    subResultList.add(nums[entry.getValue()]);
                    resultList.add(subResultList);
                }	        	
        	}
        }
        
        return resultList;
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        for(int i = 0 ; i < nums.length - 3 ; ++i) {
            //去重复
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            List<List<Integer>> result  = threeSum(nums, i, target-nums[i]);
            for(List<Integer> list : result) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[i]);
                for(Integer integer : list) {
                    temp.add(integer);
                }
                resultList.add(temp);
            }
        }
        return resultList;
    }
    
    
    /*
     * 28. Implement strStr()
     * 
     * Implement strStr().

		Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * 
     * 暴力算法
     * 
     * 最好情况：O(m) m:模式串长度
     * 最坏情况：O(m*n) 每次都是最后一个不匹配...
     * 
     */
    
 
    public int strStr(String haystack, String needle) {
        int i = 0, j = 0;
        while(i < haystack.length() && j < needle.length()) {
            if(haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }else {
            	//修正主串索引 减去增量并移到下一个位置
                i = i - j + 1;
                j = 0;
            }
        }
        if(j >= needle.length()) {
            return i - needle.length();
        }
        return -1;
    }
    
    
    
    public int[] getNext(String pattern) {
    	if(pattern == null || "".equals(pattern.trim())) {
    		return null;
    	}
    	int[] next = new int[pattern.length()];
    	next[0] = -1;
    	for(int k = 1 ; k < pattern.length() ; ++k) {
    		// len  k - 1
    		int len = k -1;
    		int maxLen = 0;
    		while(len > 1) {
    			//求出最长的匹配串
    			len--;
    		}
    		next[k] = maxLen;
    	}
    	return null;
    }
    
    @Test
    public void testStr() {
    	strStr("mississippi", "issip");
    	System.out.println("".length());
    }
	 
    @Test
    public void test() {
    	System.out.println(fourSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}, 0));
    }

	
	@Test
	public void testLCP() {
		System.out.println(longestCommonPrefix(new String[]{"ABCD", "ABEF", "ACEF"}));
	}
}
