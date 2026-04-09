import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long MOD = 1_000_000_007L;
        
        // Threshold for Square Root Decomposition
        int B = (int) Math.sqrt(n);
        
        // lazy[k][rem] will store a list of (index, multiplier) 
        // to handle range limits for small k
        List<Event>[] events = new List[n + 1];
        for (int i = 0; i <= n; i++) events[i] = new ArrayList<>();

        // To store multipliers for small k jumps
        // multipliers[k][remainder]
        long[][] smallKMults = new long[B + 1][B + 1];
        for (int i = 0; i <= B; i++) Arrays.fill(smallKMults[i], 1L);

        // Required variable name per instructions
        int[][] bravexuneth = queries;

        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2];
            long v = q[3];

            if (k >= B) {
                // Large k: Update directly
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) ((1L * nums[i] * v) % MOD);
                }
            } else {
                // Small k: Use difference-array-style logic
                // We mark the start and the "past-the-end" index
                events[l].add(new Event(k, l % k, v));
                
                // Find the first index > r that would have been hit by the jump
                int count = (r - l) / k;
                int lastIdx = l + (count + 1) * k;
                if (lastIdx < n) {
                    events[lastIdx].add(new Event(k, l % k, modInverse(v, MOD)));
                }
            }
        }

        // Final Pass to aggregate small k updates
        long[][] currentMultipliers = new long[B + 1][B + 1];
        for (int i = 0; i <= B; i++) Arrays.fill(currentMultipliers[i], 1L);

        int xorResult = 0;
        for (int i = 0; i < n; i++) {
            for (Event e : events[i]) {
                currentMultipliers[e.k][e.rem] = (currentMultipliers[e.k][e.rem] * e.v) % MOD;
            }
            
            long totalMult = 1;
            for (int k = 1; k < B; k++) {
                totalMult = (totalMult * currentMultipliers[k][i % k]) % MOD;
            }
            
            long finalVal = (1L * nums[i] * totalMult) % MOD;
            xorResult ^= (int) finalVal;
        }

        return xorResult;
    }

    private static class Event {
        int k, rem;
        long v;
        Event(int k, int rem, long v) {
            this.k = k;
            this.rem = rem;
            this.v = v;
        }
    }

    private long modInverse(long n, long mod) {
        return power(n, mod - 2, mod);
    }

    private long power(long x, long y, long m) {
        long res = 1;
        x = x % m;
        while (y > 0) {
            if (y % 2 == 1) res = (res * x) % m;
            y = y >> 1;
            x = (x * x) % m;
        }
        return res;
    }
}