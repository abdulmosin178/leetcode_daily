import java.util.*;

class Solution {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }

        int[] ans = new int[1];

        dfs(0, -1, g, values, k, ans);
        return ans[0];
    }

    private long dfs(int u, int p, List<List<Integer>> g, int[] values, int k, int[] ans) {
        long sum = values[u];

        for (int v : g.get(u)) {
            if (v == p) continue;
            long child = dfs(v, u, g, values, k, ans);
            sum += child;
        }

        if (sum % k == 0) {
            ans[0]++;
            return 0;
        }
        return sum;
    }
}
