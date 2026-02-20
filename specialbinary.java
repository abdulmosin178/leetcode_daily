import java.util.*;

class Solution {
    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) return s;

        List<String> list = new ArrayList<>();
        int count = 0, start = 0;

        // Split into primitive special substrings
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') count++;
            else count--;

            // Found a balanced special substring
            if (count == 0) {
                // Recursively make inner part largest
                String inner = makeLargestSpecial(s.substring(start + 1, i));
                list.add("1" + inner + "0");
                start = i + 1;
            }
        }

        // Sort in descending order to get lexicographically largest
        Collections.sort(list, Collections.reverseOrder());

        StringBuilder result = new StringBuilder();
        for (String str : list) {
            result.append(str);
        }

        return result.toString();
    }
}