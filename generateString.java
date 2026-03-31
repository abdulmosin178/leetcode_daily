class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int len = n + m - 1;
        
        char[] word = new char[len];
        boolean[] fixed = new boolean[len];
        
        // Initialize with '?'
        for (int i = 0; i < len; i++) word[i] = '?';
        
        // Apply 'T' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] == '?' || word[i + j] == str2.charAt(j)) {
                        word[i + j] = str2.charAt(j);
                        fixed[i + j] = true;
                    } else {
                        return "";
                    }
                }
            }
        }
        
        // Fill remaining with 'a'
        for (int i = 0; i < len; i++) {
            if (word[i] == '?') word[i] = 'a';
        }
        
        // Handle 'F' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                
                if (match) {
                    boolean changed = false;
                    for (int j = m - 1; j >= 0; j--) {
                        int idx = i + j;
                        if (!fixed[idx]) {
                            char c = word[idx];
                            for (char ch = 'a'; ch <= 'z'; ch++) {
                                if (ch != str2.charAt(j)) {
                                    word[idx] = ch;
                                    fixed[idx] = true;
                                    changed = true;
                                    break;
                                }
                            }
                            if (changed) break;
                        }
                    }
                    if (!changed) return "";
                }
            }
        }
        
        return new String(word);
    }
}