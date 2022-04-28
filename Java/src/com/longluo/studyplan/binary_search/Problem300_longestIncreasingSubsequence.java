package com.longluo.studyplan.binary_search;

/**
 * 300. 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7]是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 进阶：
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 * <p>
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class Problem300_longestIncreasingSubsequence {

    // DP time: O(n^2) space: O(1)
    public static int lengthOfLIS_dp(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums.length;
        }

        int len = nums.length;
        int ans = 1;

        //
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {

        }

        return ans;
    }

    public static int lengthOfLIS_bs(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }

        return maxans;
    }

    public static void main(String[] args) {
        System.out.println("4 ?= " + lengthOfLIS_dp(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println("4 ?= " + lengthOfLIS_dp(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("1 ?= " + lengthOfLIS_bs(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }
}