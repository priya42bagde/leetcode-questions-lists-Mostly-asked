package com.longluo.leetcode.twopointers;

import java.util.Arrays;

/**
 * 189. 轮转数组
 * <p>
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 * <p>
 * 进阶：
 * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 * <p>
 * https://leetcode-cn.com/problems/rotate-array/
 */
public class Problem189_rotateArray {

    // O(n) Space
    public static void rotate_bf(int[] nums, int k) {
        int len = nums.length;
        if (k == 0 || k % len == 0) {
            return;
        }

        int steps = k % len;
        int[] array = new int[len];
        for (int i = len - steps, j = 0; i < len; i++, j++) {
            array[j] = nums[i];
        }

        for (int i = 0; i < len - steps; i++) {
            array[steps + i] = nums[i];
        }

        System.arraycopy(array, 0, nums, 0, len);
    }

    // Two Pointers
    public static void rotate_tp(int[] nums, int k) {

    }

    public static void main(String[] args) {
        rotate_bf(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    }
}
