import java.util.*;

class Solution {
    static class Edge {
        int to;
        long w;
        Edge(int t, long w) {
            this.to = t;
            this.w = w;
        }
    }

    public int minCost(int n, int[][] edges) {
        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];

            // normal edge
            graph[u].add(new Edge(v, w));

            // reversed edge (using switch at v)
            graph[v].add(new Edge(u, 2L * w));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long d = cur[1];
            if (d > dist[u]) continue;

            for (Edge e : graph[u]) {
                if (dist[e.to] > d + e.w) {
                    dist[e.to] = d + e.w;
                    pq.add(new long[]{e.to, dist[e.to]});
                }
            }
        }

        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}
