class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long MOD = 1_000_000_007;

        // dpMax[i][j] = max product to reach (i,j)
        // dpMin[i][j] = min product to reach (i,j)
        long[][] dpMax = new long[m][n];
        long[][] dpMin = new long[m][n];

        dpMax[0][0] = grid[0][0];
        dpMin[0][0] = grid[0][0];

        // First column
        for (int i = 1; i < m; i++) {
            dpMax[i][0] = dpMax[i - 1][0] * grid[i][0];
            dpMin[i][0] = dpMax[i][0];
        }

        // First row
        for (int j = 1; j < n; j++) {
            dpMax[0][j] = dpMax[0][j - 1] * grid[0][j];
            dpMin[0][j] = dpMax[0][j];
        }

        // Fill DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];

                long max1 = dpMax[i - 1][j] * val;
                long max2 = dpMax[i][j - 1] * val;
                long min1 = dpMin[i - 1][j] * val;
                long min2 = dpMin[i][j - 1] * val;

                dpMax[i][j] = Math.max(Math.max(max1, max2), Math.max(min1, min2));
                dpMin[i][j] = Math.min(Math.min(max1, max2), Math.min(min1, min2));
            }
        }

        long result = dpMax[m - 1][n - 1];

        return result < 0 ? -1 : (int)(result % MOD);
    }
}