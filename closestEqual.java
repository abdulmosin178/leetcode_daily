import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        // store indices of each value
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> res = new ArrayList<>();

        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);

            if (list.size() == 1) {
                res.add(-1);
                continue;
            }

            int idx = Collections.binarySearch(list, q);
            int m = list.size();

            int left = list.get((idx - 1 + m) % m);
            int right = list.get((idx + 1) % m);

            int distLeft = Math.min(Math.abs(q - left), n - Math.abs(q - left));
            int distRight = Math.min(Math.abs(q - right), n - Math.abs(q - right));

            res.add(Math.min(distLeft, distRight));
        }

        return res;
    }
}