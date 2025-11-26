class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, mod = 1000000007;
        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    int val = dp[i][j][r];
                    if (val == 0) continue;
                    if (i + 1 < m) {
                        int nr = (r + grid[i + 1][j]) % k;
                        dp[i + 1][j][nr] = (dp[i + 1][j][nr] + val) % mod;
                    }
                    if (j + 1 < n) {
                        int nr = (r + grid[i][j + 1]) % k;
                        dp[i][j + 1][nr] = (dp[i][j + 1][nr] + val) % mod;
                    }
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
}
