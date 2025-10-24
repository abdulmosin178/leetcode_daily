import java.util.*;

class Solution {
    public int nextBeautifulNumber(int n) {
        // We only need numbers up to length 7 (because n <= 1e6 -> 7 digits ceiling).
        // Generate all numerically balanced numbers with total digit count <= 7.
        TreeSet<Integer> results = new TreeSet<>();
        generateBalancedNumbers(1, 0, new StringBuilder(), results, 7);
        
        // find smallest number strictly greater than n
        Integer ans = results.higher(n);
        return ans == null ? -1 : ans; // according to constraints this shouldn't be null
    }
    
    // Try choose or skip each digit d from 1..9. If chosen, append digit d exactly d times.
    private void generateBalancedNumbers(int d, int currentLen, StringBuilder sb, TreeSet<Integer> results, int maxLen) {
        if (currentLen > maxLen) return;
        if (d == 10) {
            if (currentLen >= 1) {
                // produce all unique permutations of sb and add to results
                addAllPermutations(sb.toString(), results);
            }
            return;
        }
        
        // Option 1: skip digit d
        generateBalancedNumbers(d + 1, currentLen, sb, results, maxLen);
        
        // Option 2: include digit d, which contributes d copies of char '0'+d
        if (currentLen + d <= maxLen) {
            int startLen = sb.length();
            for (int i = 0; i < d; ++i) sb.append((char)('0' + d));
            generateBalancedNumbers(d + 1, currentLen + d, sb, results, maxLen);
            // backtrack
            sb.setLength(startLen);
        }
    }
    
    // Add all unique integer permutations of the given multiset-string to results.
    private void addAllPermutations(String s, TreeSet<Integer> results) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        // use next_permutation style to iterate unique permutations
        do {
            // parse integer (no zeros at front because digits are 1..9)
            int val = 0;
            for (char c : arr) {
                val = val * 10 + (c - '0');
            }
            results.add(val);
        } while (nextPermutation(arr));
    }
    
    // Classic next permutation on char array; returns false when no next permutation.
    private boolean nextPermutation(char[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) i--;
        if (i < 0) return false;
        int j = a.length - 1;
        while (a[j] <= a[i]) j--;
        swap(a, i, j);
        reverse(a, i + 1, a.length - 1);
        return true;
    }
    
    private void swap(char[] a, int i, int j) {
        char t = a[i]; a[i] = a[j]; a[j] = t;
    }
    
    private void reverse(char[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }
}
