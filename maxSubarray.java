import java.util.*;

class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        long INF = (1L << 60);
        long[] minPref = new long[k];
        Arrays.fill(minPref, INF);
        long pref = 0;
        long ans = -INF;
        minPref[(k - 1) % k] = 0;
        for (int i = 0; i < nums.length; i++) {
            pref += nums[i];
            int r = i % k;
            ans = Math.max(ans, pref - minPref[r]);
            if (pref < minPref[r]) minPref[r] = pref;
        }
        if (ans == -INF) return 0;
        return ans;
    }
}
