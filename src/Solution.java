import java.util.HashMap;
import java.util.Map;

/**
 * 
* @Title: Solution.java 
* @Package  
* @Description: TODO
* @author msidolphin
* @date 2017年8月16日 下午10:33:13 
* @version V1.0
 */
public class Solution {
    
	/*
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 * 
	 * 
	 * 	You are given two non-empty linked lists representing two non-negative integers. 
	 * 	The digits are stored in reverse order and each of their nodes contain a single digit. 
	 * 	Add the two numbers and return it as a linked list.
	 * 
	 *	You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *		
     *	其实就是模拟大数相加，342 + 564 = 807 注意进位！
     *
     *	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 *	Output: 7 -> 0 -> 8
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(0);
        //新链表头结点
        ListNode head = p;
        //进位
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            ListNode node = new ListNode(0);
            int a = (l1 == null) ? 0 : l1.val;
            int b = (l2 == null) ? 0 : l2.val;
            node.val = (a + b + carry) % 10;
            carry = (a + b + carry) / 10;
            p.next = node;
            p = p.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        return head.next;
    }
	
	
	static class ListNode {
		
		int val;
		ListNode next;
		
		public ListNode(int val) {
			super();
			this.val = val;
		}
		
		public static ListNode createList(int[] arr) {
			ListNode head = new ListNode(0);
			ListNode pre = null;
			pre = head;
			for(int i = 0 ; i < arr.length ; ++i) {
				ListNode node = new ListNode(0);
				node.val = arr[i];
				pre.next = node;
				pre = node;
			}
			return head.next;
		}
		
		public static void PrintList(ListNode list) {
			while(list != null) {
				System.out.println(list.val);
				list = list.next;
			}
		}
	}
	
	
	
	
	/*
	 *
	 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     *
	 *	You may assume that each input would have exactly one solution, and you may not use the same element twice.
	 *	
	 *	Example:
	 *	Given nums = [2, 7, 11, 15], target = 9,
	 *	
	 *	Because nums[0] + nums[1] = 2 + 7 = 9,
	 *	return [0, 1].
	 *
	 * 注意：假设有且仅有一个解
	 */
	public int[] twoSum(int[] numbers, int target) {
	    int[] result = new int[2];
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for (int i = 0; i < numbers.length; i++) {
	        if (map.containsKey(target - numbers[i])) {
	            result[1] = i;
	            result[0] = map.get(target - numbers[i]);
	            return result;
	        }
	        map.put(numbers[i], i);
	    }
	    return result;
	}
	
	/**
	 * 1 Two Sum
	 * @Title: twoSumByBrute
	 * @Description: Two Sum 暴力算法  
	 * 时间复杂度：O(n(n+1)/2)  空间复杂度:O(1)
	 * @param @param numbers
	 * @param @param target
	 * @param @return
	 * @return int[]
	 * @throws
	 */
	public int[] twoSumByBrute(int[] numbers, int target) {
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
    
    
    public static void main(String[] args) {
		Solution solution = new Solution();
		
//		
//		int[] numbers = new int[]{2, 15, 11, 7};
//		int target = 9;
//		int[] result = solution.twoSumByBrute(numbers, target);
//		for (int i : result) {
//			System.out.println(i);
//		}
//		
		int[] arr1 = new int[]{1};
		int[] arr2 = new int[]{9, 9};
		ListNode l1 = ListNode.createList(arr1);
		ListNode l2 = ListNode.createList(arr2);
		ListNode l3 = solution.addTwoNumbers(l1, l2);
		ListNode.PrintList(l3);
		
		
	}
}