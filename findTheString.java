class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] res = new char[n];
        for (int i = 0; i < n; i++) res[i] = 0;

        char ch = 'a';

        for (int i = 0; i < n; i++) {
            if (res[i] != 0) continue;
            if (ch > 'z') return "";
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    res[j] = ch;
                }
            }
            ch++;
        }

        for (int i = 0; i < n; i++) {
            if (res[i] == 0) return "";
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expected = 0;
                if (res[i] == res[j]) {
                    if (i == n - 1 || j == n - 1) expected = 1;
                    else expected = 1 + lcp[i + 1][j + 1];
                }
                if (lcp[i][j] != expected) return "";
            }
        }

        return new String(res);
    }
}