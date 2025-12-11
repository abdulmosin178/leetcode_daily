import java.util.*;

class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {

        // Maps to store min/max coordinates for each row and column
        Map<Integer, Integer> minRow = new HashMap<>();
        Map<Integer, Integer> maxRow = new HashMap<>();

        Map<Integer, Integer> minCol = new HashMap<>();
        Map<Integer, Integer> maxCol = new HashMap<>();

        // Preprocessing: find min/max y in each row, and min/max x in each column
        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            minRow.put(x, Math.min(minRow.getOrDefault(x, y), y));
            maxRow.put(x, Math.max(maxRow.getOrDefault(x, y), y));

            minCol.put(y, Math.min(minCol.getOrDefault(y, x), x));
            maxCol.put(y, Math.max(maxCol.getOrDefault(y, x), x));
        }

        int covered = 0;

        // Check each building
        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            boolean hasLeft  = minRow.get(x) < y;
            boolean hasRight = maxRow.get(x) > y;
            boolean hasUp    = minCol.get(y) < x;
            boolean hasDown  = maxCol.get(y) > x;

            if (hasLeft && hasRight && hasUp && hasDown) {
                covered++;
            }
        }

        return covered;
    }
}
