class Solution {
    public int minimumOperations(int[] nums) {
        int c=0;
        for(int x:nums){
            int r=x%3;
            if(r==1)c++;
            else if(r==2)c++;
        }
        return c;
    }
}
