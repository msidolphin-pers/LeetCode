package cn.neusoft.leetcode.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * 
* @Title: Solution.java 
* @Package cn.neusoft.leetcode.list 
* @Description: 链表类型
* @author msidolphin
* @date 2017年8月20日 下午7:59:43 
* @version V1.0
 */
public class Solution {
	
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	
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
	 * 19. Remove Nth Node From End of List
	 * 
	 * Given a linked list, remove the nth node from the end of list and return its head.

		For example,
		
		   Given linked list: 1->2->3->4->5, and n = 2.
		
		   After removing the second node from the end, the linked list becomes 1->2->3->5.
		Note:
		Given n will always be valid.
		Try to do this in one pass.
	 * 
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) {
            return null;
        }
        //获取链表长度
        ListNode p = head;
        int listLen = 0;
        while(p != null) {
            listLen++;
            p = p.next;
        }
        int targetPoint = 0;
        if(n >= listLen) {
            //如果要求删除的位置大于等于链表长度，那么只需要移除第一个就行了
        	//eg [3,2] 4 返回 [2] [3,2] 2 返回 [2]
            return head.next;
        }else {
            targetPoint = listLen - n;
        }
        ListNode prev = head;
        p = head;
        while(targetPoint > 0 && p != null) {
            prev = p;
            targetPoint--;
            p = p.next;
        }
        prev.next = p.next;
        return head;
    }
	
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode guard = new ListNode(-1);
        guard.next = head;
        head = guard;
        //上一个不重复的节点，初始next指向第一个节点
        ListNode prev = head;        
        //现在cur的指向是第一个节点，之后cur的指向是不重复的节点 
        ListNode  cur = prev.next;
        //不能使cur != null cur.next = null足以说明该节点是一个不重复的节点
        while(cur.next != null) {
            boolean isRepeat = false;
            //移动cur指针到不重复的节点
            while(cur.next != null && cur.val == cur.next.val) {
                isRepeat = true;
                cur = cur.next;
            }
            if(isRepeat) {
                 //使得上一个不重复的节点指向下一个不重复的节点
                prev.next = cur.next;
                //试探此节点是否重复
                cur = cur.next;
            }else {
                prev = cur;
                cur = cur.next;
            }
        }
        prev.next = cur;
        return guard.next;
    }
	
	
	
	@Test
	public void test() {
		System.out.println(-0);
		
//		System.out.println(deleteDuplicates(ListNode.createList(new int[]{1,2,3,3,4,4,5})));
		
	}
}
