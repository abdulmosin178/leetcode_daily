class Solution {
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
    
    public int minOperations(int[] nums) {
        int n = nums.length;
        int countOnes = 0;
        for (int v : nums) {
            if (v == 1) countOnes++;
        }
        // If we already have ones, each non-one takes one operation with a neighboring 1
        if (countOnes > 0) {
            return n - countOnes;
        }
        
        // No ones: find shortest subarray with gcd 1
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int g = 0;
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break; // no need to extend this start i further
                }
            }
        }
        
        if (minLen == Integer.MAX_VALUE) return -1;
        // (minLen - 1) ops to create one '1' + (n - 1) ops to convert remaining elements
        return (minLen - 1) + (n - 1);
    }
}
