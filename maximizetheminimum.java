class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;

        // Initial power at each city via prefix sums in O(n)
        long[] power = new long[n];
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + stations[i];
        for (int i = 0; i < n; i++) {
            int L = Math.max(0, i - r);
            int R = Math.min(n - 1, i + r);
            power[i] = pref[R + 1] - pref[L];
        }

        long lo = 0, hi = (long) 1e18, ans = 0;
        while (lo <= hi) {
            long mid = (lo + hi) >>> 1;
            if (canAchieve(power, r, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean canAchieve(long[] power, int r, long k, long target) {
        int n = power.length;
        long[] added = new long[n];  // stations we place at each index
        long inWindow = 0;           // sum of added stations whose centers are within [i - r, i + r]

        for (int i = 0; i < n; i++) {
            // Expire placements whose centers are left of the current window:
            int expireIdx = i - r - 1;
            if (expireIdx >= 0) inWindow -= added[expireIdx];

            long have = power[i] + inWindow;
            if (have < target) {
                long need = target - have;
                if (need > k) return false;
                k -= need;

                int placeAt = Math.min(n - 1, i + r); // rightmost center that still covers city i
                added[placeAt] += need;
                inWindow += need; // newly placed centers are inside current window
            }
        }
        return true;
    }
}
