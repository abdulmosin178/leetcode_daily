class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 1, right = (long)1e18;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (can(mid, mountainHeight, workerTimes)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean can(long time, int height, int[] workers) {
        long total = 0;

        for (int w : workers) {
            long l = 0, r = height;

            while (l <= r) {
                long m = (l + r) / 2;
                long cost = (long) w * m * (m + 1) / 2;

                if (cost <= time) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            total += r;
            if (total >= height) return true;
        }

        return false;
    }
}