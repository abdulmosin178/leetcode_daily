class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                if (isValid(nums, i, 1)) count++;  // move right
                if (isValid(nums, i, -1)) count++; // move left
            }
        }

        return count;
    }

    private boolean isValid(int[] nums, int start, int dir) {
        int n = nums.length;
        int[] arr = nums.clone();
        int curr = start;
        int d = dir;

        while (curr >= 0 && curr < n) {
            if (arr[curr] == 0) {
                curr += d;  // move in current direction
            } else {
                arr[curr]--; // decrease by 1
                d = -d;      // reverse direction
                curr += d;   // step in new direction
            }
        }

        // check if all elements are zero
        for (int x : arr) {
            if (x != 0) return false;
        }
        return true;
    }
}
