import java.util.*;

class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            int mod = ((n % value) + value) % value;
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        int mex = 0;
        while (true) {
            int mod = mex % value;
            if (!map.containsKey(mod) || map.get(mod) == 0) break;
            map.put(mod, map.get(mod) - 1);
            mex++;
        }
        return mex;
    }
}
