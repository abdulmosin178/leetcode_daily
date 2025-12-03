import java.util.*;

class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;

        // Map slope key -> list of segments' representative point (we'll count per-line after)
        Map<Long, List<int[]>> slopeToSegs = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;

                // normalize direction so (dx,dy) is unique
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }

                long slopeKey = (((long) dy) << 32) ^ (dx & 0xffffffffL);
                slopeToSegs.computeIfAbsent(slopeKey, k -> new ArrayList<>())
                           .add(new int[]{i, j});
            }
        }

        long result = 0L;

        // For each slope group, group segments by their line offset and apply formula
        for (Map.Entry<Long, List<int[]>> e : slopeToSegs.entrySet()) {
            long slopeKey = e.getKey();
            int dy = (int)(slopeKey >> 32);
            int dx = (int)(slopeKey & 0xffffffffL);

            List<int[]> segs = e.getValue();
            int m = segs.size();
            if (m < 2) continue;

            // Count segments per parallel line. Use value = dy * x - dx * y which is constant for points on same line.
            Map<Long, Integer> lineCount = new HashMap<>();
            for (int[] s : segs) {
                int a = s[0]; // index of one endpoint
                int x = points[a][0];
                int y = points[a][1];
                long lineId = (long) dy * x - (long) dx * y; // unique per parallel line (fits in long)
                lineCount.merge(lineId, 1, Integer::sum);
            }

            long sumSquares = 0L;
            for (int t : lineCount.values()) sumSquares += (long) t * t;

            // number of unordered pairs of segments that lie on different lines:
            long validPairs = ((long) m * (long) m - sumSquares) / 2L;
            result += validPairs;
        }

        // Problem constraints may produce answers > 2^31-1; original signature returns int.
        // We keep long internally and cast at the end (LeetCode signature expects int).
        return (int) result;
    }

    private int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }
}
