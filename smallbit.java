class Solution {
    public int smallestNumber(int n) {
        int x = 1;
        // Keep generating numbers like 1, 3, 7, 15, 31, ...
        // until we find one >= n
        while (x < n) {
            x = (x << 1) | 1; // shift left and add 1 to make all bits set
        }
        return x;
    }
}
