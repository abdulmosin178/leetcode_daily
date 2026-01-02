class Solution {
    public int repeatedNTimes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < Math.min(nums.length, i + 4); j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }
}
