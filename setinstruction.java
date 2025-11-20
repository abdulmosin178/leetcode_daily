import java.util.*;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> 
            a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]
        );

        int a = -1, b = -1; 
        int ans = 0;

        for (int[] in : intervals) {
            int s = in[0], e = in[1];

            boolean c1 = (s <= a);
            boolean c2 = (s <= b);

            if (c1 && c2) {
                continue;
            } else if (c2) {
                ans += 1;
                a = b;
                b = e;
            } else {
                ans += 2;
                a = e - 1;
                b = e;
            }
        }
        return ans;
    }
}
