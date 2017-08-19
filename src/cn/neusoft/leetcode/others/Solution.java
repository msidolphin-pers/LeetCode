package cn.neusoft.leetcode.others;

/**
 * 
* @Title: Solution.java 
* @Package cn.neusoft.leetcode.others 
* @Description: 其它类型
* @author msidolphin
* @date 2017年8月19日 下午11:52:28 
* @version V1.0
 */
public class Solution {
	/*
	 * 657. Judge Route Circle
	 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

		The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.
		
		Example 1:
		Input: "UD"
		Output: true
		Example 2:
		Input: "LL"
		Output: false
	 * 
	 */
	public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        int len = moves.length();
        for(int i = 0 ; i < len ; ++i) {
            char ch = moves.charAt(i);
            switch(ch) {
                case 'U':
                    x = x + 1;
                    break;
                case 'D':
                    x = x - 1;
                    break;
                case 'L':
                    y = y - 1;
                    break;
                case 'R':
                    y = y + 1;
                    break;
            }
        }
        if(x == 0 && y == 0) {
            return true;
        }else {
            return false;
        }
    }

	/*
	 * 461. Hamming Distance
	 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

		Given two integers x and y, calculate the Hamming distance.
		
		Note:
		0 ≤ x, y < 231.
	 */
	public int hammingDistance(int x, int y) {
        int himmingDistance = 0;
        while(x != 0 || y != 0) {
            if((x & 1) != (y & 1)) {
                ++himmingDistance;
            }
            x >>= 1;
            y >>= 1;
        }
        return himmingDistance;
    }
}
