import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int initialZeros = 0;
        for (char c : s.toCharArray()) if (c == '0') initialZeros++;
        if (initialZeros == 0) return 0;

        // Separate sets for even and odd unvisited indices to handle the step-of-2
        TreeSet<Integer> unvisitedEven = new TreeSet<>();
        TreeSet<Integer> unvisitedOdd = new TreeSet<>();
        
        for (int i = 0; i <= n; i++) {
            if (i == initialZeros) continue;
            if (i % 2 == 0) unvisitedEven.add(i);
            else unvisitedOdd.add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(initialZeros);
        
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                // Possible nextZeros range:
                // minX = max(0, k - (n - curr)) -> leads to maxNext
                // maxX = min(curr, k) -> leads to minNext
                int minNext = curr - Math.min(curr, k) + (k - Math.min(curr, k));
                int maxNext = curr - Math.max(0, k - (n - curr)) + (k - Math.max(0, k - (n - curr)));
                
                // Which set to look in? nextZeros parity is (curr + k) % 2
                TreeSet<Integer> targetSet = ((curr + k) % 2 == 0) ? unvisitedEven : unvisitedOdd;
                
                // Get all unvisited in range [minNext, maxNext]
                NavigableSet<Integer> reachable = targetSet.subSet(minNext, true, maxNext, true);
                
                Iterator<Integer> it = reachable.iterator();
                while (it.hasNext()) {
                    int next = it.next();
                    if (next == 0) return steps;
                    queue.add(next);
                    it.remove(); // Mark as visited
                }
            }
        }

        return -1;
    }
}