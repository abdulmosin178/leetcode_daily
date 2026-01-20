class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int p = nums.get(i);
            int found = -1;
            
            for (int a = 0; a <= p; a++) {
                if ((a | (a + 1)) == p) {
                    found = a;
                    break;
                }
            }
            
            ans[i] = found;
        }
        
        return ans;
    }
}
