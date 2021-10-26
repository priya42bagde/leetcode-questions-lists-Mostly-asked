package com.longluo.leetcode.binarysearch;

/**
 * 74. 搜索二维矩阵
 * <p>
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 * <p>
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class Problem74_searchA2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) {
            return false;
        }

        for (int i = 0; i < row; i++) {
            if (target >= matrix[i][0] && target <= matrix[i][col - 1]) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean searchMatrix_1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) {
            return false;
        }

        for (int i = 0; i < row; i++) {
            if (target >= matrix[i][0] && target <= matrix[i][col - 1]) {
                if (binarySearch(matrix[i], target) != -1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean searchMatrix_2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) {
            return false;
        }

        int low = 0;
        int high = row - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (matrix[mid][0] > target) {
                high = mid - 1;
            } else if (matrix[mid][col - 1] < target) {
                low = mid + 1;
            } else if (matrix[mid][0] <= target && matrix[mid][col - 1] >= target) {
                low = mid;
                break;
            }
        }

        if (binarySearch(matrix[low], target) != -1) {
            return true;
        }

        return false;
    }

    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static boolean searchMatrix_3(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        if (matrix[0][0] > target || matrix[row - 1][col - 1] < target) {
            return false;
        }

        int i = 0;
        int j = col - 1;

        while (i < row && j >= 0) {
            if (target > matrix[i][j]) {
                i++;
            } else if (target < matrix[i][j]) {
                j--;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("true ?= " + searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        System.out.println("true ?= " + searchMatrix_1(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        System.out.println("false ?= " + searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        System.out.println("false ?= " + searchMatrix_1(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        System.out.println("false ?= " + searchMatrix_2(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        System.out.println("true ?= " + searchMatrix_2(new int[][]{{0, 0, 1, 3, 4, 6, 8, 8}, {11, 12, 14, 16, 18, 18, 19, 19}, {68, 69, 71, 72, 72, 72, 74, 76}}, 76));
        System.out.println("true ?= " + searchMatrix_2(new int[][]{{-8, -8, -7, -7, -6, -5, -3, -2}, {0, 0, 1, 3, 4, 6, 8, 8}, {11, 12, 14, 16, 18, 18, 19, 19}, {22, 23, 25, 27, 28, 30, 30, 31}, {34, 35, 37, 39, 40, 42, 43, 45}, {48, 50, 51, 51, 53, 54, 55, 57}, {58, 60, 62, 62, 62, 63, 63, 65}, {68, 69, 71, 72, 72, 72, 74, 76}}, 76));
        System.out.println("true ?= " + searchMatrix(new int[][]{{1}}, 1));
        System.out.println("true ?= " + searchMatrix_1(new int[][]{{1}}, 1));
        System.out.println("true ?= " + searchMatrix_3(new int[][]{{1}}, 1));
    }
}
