import java.util.*;

class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k;
        boolean[] seen = new boolean[need];
        int count = 0;
        int num = 0;
        
        for (int i = 0; i < s.length(); i++) {
            num = ((num << 1) & (need - 1)) | (s.charAt(i) - '0');
            if (i >= k - 1 && !seen[num]) {
                seen[num] = true;
                count++;
                if (count == need) return true;
            }
        }
        
        return false;
    }
}