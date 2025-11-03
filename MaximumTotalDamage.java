import java.util.*;

class Solution {
    public long maximumTotalDamage(int[] power) {
        Map<Long, Long> freq = new HashMap<>();
        for (int p : power) freq.put((long)p, freq.getOrDefault((long)p, 0L) + p);
        List<Long> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys);

        int n = keys.size();
        long[] dp = new long[n];
        dp[0] = freq.get(keys.get(0));

        for (int i = 1; i < n; i++) {
            long curKey = keys.get(i);
            long curVal = freq.get(curKey);
            int j = i - 1;
            while (j >= 0 && curKey - keys.get(j) <= 2) j--;
            long include = curVal + (j >= 0 ? dp[j] : 0);
            long exclude = dp[i - 1];
            dp[i] = Math.max(include, exclude);
        }
        return dp[n - 1];
    }//maximum damage
}
