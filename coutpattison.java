class Solution {
    public int countPartitions(int[] nums) {
        int total = 0;
        for (int v : nums) total += v;
        return (total % 2 == 0) ? nums.length - 1 : 0;
    }
}
