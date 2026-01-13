class Solution {
    public double separateSquares(int[][] squares) {
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;
        double totalArea = 0;

        // Find search range and total area
        for (int[] s : squares) {
            double y = s[1];
            double l = s[2];
            low = Math.min(low, y);
            high = Math.max(high, y + l);
            totalArea += l * l;
        }

        // Binary search
        for (int i = 0; i < 100; i++) {
            double mid = (low + high) / 2;
            double below = 0;

            for (int[] s : squares) {
                double y = s[1];
                double l = s[2];

                if (mid <= y) {
                    continue;
                } else if (mid >= y + l) {
                    below += l * l;
                } else {
                    below += (mid - y) * l;
                }
            }

            if (below * 2 < totalArea) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }
}