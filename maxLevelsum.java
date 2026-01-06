import java.util.*;

class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int level = 1;
        int ansLevel = 1;
        long maxSum = Long.MIN_VALUE;

        while (!q.isEmpty()) {
            int size = q.size();
            long sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sum += node.val;

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            if (sum > maxSum) {
                maxSum = sum;
                ansLevel = level;
            }
            level++;
        }
        return ansLevel;
    }
}
