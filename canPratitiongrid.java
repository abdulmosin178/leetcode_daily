class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long total = 0;

        for (int[] row : grid) {
            for (int val : row) {
                total += val;
            }
        }

        // If total sum is odd, cannot split equally
        if (total % 2 != 0) return false;

        long half = total / 2;

        // Check horizontal cuts
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum == half) return true;
        }

        // Check vertical cuts
        long[] colPrefix = new long[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                colPrefix[j] += grid[i][j];
            }
        }

        long colSum = 0;
        for (int j = 0; j < n - 1; j++) {
            colSum += colPrefix[j];
            if (colSum == half) return true;
        }

        return false;
    }
}