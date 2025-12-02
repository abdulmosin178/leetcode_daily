import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007;

    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>();   // y -> count of points

        // Count number of points at each y-level
        for (int[] p : points) {
            map.put(p[1], map.getOrDefault(p[1], 0) + 1);
        }

        // Compute horizontal segments for each y-level
        List<Long> seg = new ArrayList<>();
        for (int c : map.values()) {
            if (c >= 2) {
                long s = (long) c * (c - 1) / 2; // C(c, 2)
                seg.add(s);
            }
        }

        // Now count pairs of levels: seg[i] * seg[j]
        long ans = 0;
        long sum = 0;

        for (long s : seg) {
            ans = (ans + (s * sum) % MOD) % MOD;
            sum = (sum + s) % MOD;
        }

        return (int) ans;
    }
}
