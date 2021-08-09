package com.longluo.leetcode.memoization;

/**
 * 1137. 第 N 个泰波那契数
 * <p>
 * 泰波那契序列 Tn 定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * <p>
 * 示例 2：
 * 输入：n = 25
 * 输出：1389537
 * <p>
 * 提示：
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 * <p>
 * https://leetcode-cn.com/problems/n-th-tribonacci-number/
 */
public class Problem1137_nThTribonacciNumber {

    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }

        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = res[2] = 1;

        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 3] + res[i - 2] + res[i - 1];
        }

        return res[n];
    }

    public static int tribonacci_2(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }

        int i_3 = 0;
        int i_2 = 1;
        int i_1 = 1;
        int ans = 0;

        for (int i = 3; i <= n; i++) {
            ans = i_3 + i_2 + i_1;
            i_3 = i_2;
            i_2 = i_1;
            i_1 = ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("0 ?= " + tribonacci(0));
        System.out.println("0 ?= " + tribonacci_2(0));
        System.out.println("1 ?= " + tribonacci(1));
        System.out.println("1 ?= " + tribonacci_2(1));
        System.out.println("1 ?= " + tribonacci(2));
        System.out.println("1 ?= " + tribonacci_2(2));
        System.out.println("2 ?= " + tribonacci(3));
        System.out.println("2 ?= " + tribonacci_2(3));
        System.out.println("4 ?= " + tribonacci(4));
        System.out.println("4 ?= " + tribonacci_2(4));
        System.out.println("1389537 ?= " + tribonacci(25));
        System.out.println("1389537 ?= " + tribonacci_2(25));
    }
}