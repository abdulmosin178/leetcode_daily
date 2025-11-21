class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int ans = 0;

        // Store first and last occurrence of each character
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Collect first and last positions
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        // For each character as the palindrome's endpoints
        for (int c = 0; c < 26; c++) {
            if (first[c] != -1 && first[c] < last[c]) {
                boolean[] seen = new boolean[26];

                // Count distinct chars between first[c] and last[c]
                for (int i = first[c] + 1; i < last[c]; i++) {
                    seen[s.charAt(i) - 'a'] = true;
                }

                // Add count of distinct middle characters
                for (boolean b : seen) if (b) ans++;
            }
        }

        return ans;
    }
}
