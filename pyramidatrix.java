class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // Map each pair of bottom blocks to possible top blocks
        Map<String, List<Character>> map = new HashMap<>();
        for (String s : allowed) {
            String key = s.substring(0, 2);
            char top = s.charAt(2);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(top);
        }
        return dfs(bottom, map);
    }

    private boolean dfs(String bottom, Map<String, List<Character>> map) {
        // If we reached the top of the pyramid
        if (bottom.length() == 1) return true;

        // Generate all possible next rows
        return buildNext(bottom, 0, new StringBuilder(), map);
    }

    private boolean buildNext(String bottom, int idx, StringBuilder next,
                              Map<String, List<Character>> map) {
        if (idx == bottom.length() - 1) {
            // One full row built, recurse to next level
            return dfs(next.toString(), map);
        }

        String key = bottom.substring(idx, idx + 2);
        if (!map.containsKey(key)) return false;

        for (char c : map.get(key)) {
            next.append(c);
            if (buildNext(bottom, idx + 1, next, map)) return true;
            next.deleteCharAt(next.length() - 1); // backtrack
        }
        return false;
    }
}
