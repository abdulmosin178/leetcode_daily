class Solution {
    public int minimumPairRemoval(int[] nums) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        for (int n : nums) list.add(n);

        int ops = 0;

        while (!isNonDecreasing(list)) {
            int minSum = Integer.MAX_VALUE;
            int idx = 0;

            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    idx = i;
                }
            }

            int newVal = list.get(idx) + list.get(idx + 1);
            list.remove(idx + 1);
            list.set(idx, newVal);
            ops++;
        }

        return ops;
    }

    private boolean isNonDecreasing(java.util.List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) return false;
        }
        return true;
    }
}
