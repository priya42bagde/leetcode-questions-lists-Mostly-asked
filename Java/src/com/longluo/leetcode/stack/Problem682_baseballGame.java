package com.longluo.leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 682. 棒球比赛
 * <p>
 * 你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
 * <p>
 * 比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：
 * <p>
 * 整数 x - 表示本回合新获得分数 x
 * "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
 * "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * 请你返回记录中所有得分的总和。
 * <p>
 * 示例 1：
 * 输入：ops = ["5","2","C","D","+"]
 * 输出：30
 * 解释：
 * "5" - 记录加 5 ，记录现在是 [5]
 * "2" - 记录加 2 ，记录现在是 [5, 2]
 * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].
 * "D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].
 * "+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].
 * 所有得分的总和 5 + 10 + 15 = 30
 * <p>
 * 示例 2：
 * 输入：ops = ["5","-2","4","C","D","9","+","+"]
 * 输出：27
 * 解释：
 * "5" - 记录加 5 ，记录现在是 [5]
 * "-2" - 记录加 -2 ，记录现在是 [5, -2]
 * "4" - 记录加 4 ，记录现在是 [5, -2, 4]
 * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5, -2]
 * "D" - 记录加 2 * -2 = -4 ，记录现在是 [5, -2, -4]
 * "9" - 记录加 9 ，记录现在是 [5, -2, -4, 9]
 * "+" - 记录加 -4 + 9 = 5 ，记录现在是 [5, -2, -4, 9, 5]
 * "+" - 记录加 9 + 5 = 14 ，记录现在是 [5, -2, -4, 9, 5, 14]
 * 所有得分的总和 5 + -2 + -4 + 9 + 5 + 14 = 27
 * <p>
 * 示例 3：
 * 输入：ops = ["1"]
 * 输出：1
 * <p>
 * 提示：
 * 1 <= ops.length <= 1000
 * ops[i] 为 "C"、"D"、"+"，或者一个表示整数的字符串。整数范围是 [-3 * 104, 3 * 104]
 * 对于 "+" 操作，题目数据保证记录此操作时前面总是存在两个有效的分数
 * 对于 "C" 和 "D" 操作，题目数据保证记录此操作时前面总是存在一个有效的分数
 * <p>
 * https://leetcode-cn.com/problems/baseball-game/
 */
public class Problem682_baseballGame {

    public static int calPoints(String[] ops) {
        Stack<Integer> stk = new Stack<>();
        int len = ops.length;
        for (int i = 0; i < len; i++) {
            String str = ops[i];
            if (str.equals("+")) {
                int scoreA = stk.pop();
                int scoreB = stk.pop();
                stk.push(scoreB);
                stk.push(scoreA);
                stk.push(scoreA + scoreB);
            } else if (str.equals("C")) {
                stk.pop();
            } else if (str.equals("D")) {
                int score = 2 * stk.peek();
                stk.push(score);
            } else {
                int score = Integer.parseInt(str);
                stk.push(score);
            }
        }

        int ans = 0;
        while (!stk.empty()) {
            ans += stk.pop();
        }

        return ans;
    }

    public static int calPoints_list(String[] ops) {
        List<Integer> scoreList = new ArrayList<>();
        int len = ops.length;
        for (int i = 0; i < len; i++) {
            String str = ops[i];
            if (str.equals("+")) {
                int size = scoreList.size();
                scoreList.add(scoreList.get(size - 1) + scoreList.get(size - 2));
            } else if (str.equals("C")) {
                scoreList.remove(scoreList.size() - 1);
            } else if (str.equals("D")) {
                scoreList.add(2 * scoreList.get(scoreList.size() - 1));
            } else {
                scoreList.add(Integer.parseInt(str));
            }
        }

        int ans = 0;
        for (int i = 0; i < scoreList.size(); i++) {
            ans += scoreList.get(i);
        }

        return ans;
    }

    public static int calPoints_list_opt(String[] ops) {
        List<Integer> scoreList = new ArrayList<>();
        int len = ops.length;
        int points = 0;
        for (int i = 0; i < len; i++) {
            String str = ops[i];
            switch (str) {
                case "+":
                    int size = scoreList.size();
                    int curPoint = scoreList.get(size - 1) + scoreList.get(size - 2);
                    points += curPoint;
                    scoreList.add(curPoint);
                    break;

                case "C":
                    points -= scoreList.get(scoreList.size() - 1);
                    scoreList.remove(scoreList.size() - 1);
                    break;

                case "D":
                    scoreList.add(2 * scoreList.get(scoreList.size() - 1));
                    points += scoreList.get(scoreList.size() - 1);
                    break;

                default:
                    scoreList.add(Integer.parseInt(str));
                    points += Integer.parseInt(str);
                    break;
            }
        }

        return points;
    }

    public static void main(String[] args) {

    }
}
