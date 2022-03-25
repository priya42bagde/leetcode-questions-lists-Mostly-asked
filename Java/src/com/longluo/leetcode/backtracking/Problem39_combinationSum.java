package com.longluo.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 */
public class Problem39_combinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || target == 0) {
            return ans;
        }

        backtrack(ans, new ArrayList<>(), candidates, target);
        return ans;
    }

    public static void backtrack(List<List<Integer>> res, List<Integer> list, int[] candidates, int remain) {
        if (remain < 0) {
            return;
        }

        if (remain == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        int len = candidates.length;
        for (int i = 0; i < len; i++) {
            int num = candidates[i];
            list.add(num);
            backtrack(res, list, candidates, remain - num);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {

    }
}